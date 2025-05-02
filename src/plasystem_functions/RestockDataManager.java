package plasystem_functions;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Manages restocking operations in the PlaSystem database, including creating, retrieving,
 * and deleting restock events. Handles single and multiple product restocks, updates product
 * quantities, and ensures database consistency through transactions and comprehensive error
 * checking based on schema constraints.
 */
public class RestockDataManager {
    /** SQL query to insert a new restock event into the Restock table. */
    private static final String INSERT_RESTOCK_QUERY = 
        "INSERT INTO Restock (RESTOCK_DATE_YEAR, RESTOCK_DATE_MONTH, RESTOCK_DATE_DAY, RESTOCK_DATE_TIME) " +
        "VALUES (?, ?, ?, ?)";
    
    /** SQL query to insert a restock item into the RestockItems table. */
    private static final String INSERT_RESTOCK_ITEM_QUERY = 
        "INSERT INTO RestockItems (RI_RESTOCK_ID, RI_PROD_ID, RI_PROD_NAME, RI_PROD_BRAND, " +
        "RI_PROD_SIZE, RI_PROD_TYPE, RI_PROD_PRICE, RI_RESTOCKED_QUANTITY) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    /** SQL query to update the quantity of a product in the Product table. */
    private static final String UPDATE_PRODUCT_QUANTITY_QUERY = 
        "UPDATE Product SET PROD_QUANTITY = PROD_QUANTITY + ? WHERE PROD_ID = ?";
    
    /** SQL query to delete a restock event from the Restock table. */
    private static final String DELETE_RESTOCK_QUERY = 
        "DELETE FROM Restock WHERE RESTOCK_ID = ?";
    
    /** SQL query to select all restock events from the Restock table. */
    private static final String SELECT_RESTOCK_QUERY = 
        "SELECT * FROM Restock";
    
    /** SQL query to select restock items for a specific restock event from the RestockItems table. */
    private static final String SELECT_RESTOCK_ITEMS_QUERY = 
        "SELECT * FROM RestockItems WHERE RI_RESTOCK_ID = ?";

    /** Manager for product data operations, used to refresh product quantities after restocking. */
    private final ProductDataManager productDataManager;
    
    /** In-memory list of restock events, synchronized with the database. */
    private final LinkedList<RestockData> restockList;

    /**
     * Constructs a RestockDataManager with a dependency on ProductDataManager and initializes
     * the restock list by loading all restock events from the database.
     *
     * @param productDataManager The manager for product data operations. Must not be null.
     * @throws NullPointerException if productDataManager is null.
     */
    public RestockDataManager(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
        this.restockList = new LinkedList<>();
        loadRestocks();
    }

    /**
     * Retrieves the list of restock events.
     *
     * @return An unmodifiable view of the list of RestockData objects.
     */
    public List<RestockData> getRestockList() {
        return restockList;
    }

    /**
     * Loads all restock events and their associated items from the database into the in-memory
     * restock list. Clears the existing list before loading to ensure synchronization with the
     * database. Displays an error message if a database error occurs.
     */
    private void loadRestocks() {
        restockList.clear();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement restockStmt = conn.prepareStatement(SELECT_RESTOCK_QUERY);
             ResultSet restockRs = restockStmt.executeQuery()) {

            while (restockRs.next()) {
                int restockId = restockRs.getInt("RESTOCK_ID");
                String year = restockRs.getString("RESTOCK_DATE_YEAR");
                String month = restockRs.getString("RESTOCK_DATE_MONTH");
                String day = restockRs.getString("RESTOCK_DATE_DAY");
                String time = restockRs.getString("RESTOCK_DATE_TIME");

                // Fetch associated restock items
                List<RestockItemData> items = new ArrayList<>();
                try (PreparedStatement itemStmt = conn.prepareStatement(SELECT_RESTOCK_ITEMS_QUERY)) {
                    itemStmt.setInt(1, restockId);
                    try (ResultSet itemRs = itemStmt.executeQuery()) {
                        while (itemRs.next()) {
                            RestockItemData item = new RestockItemData(
                                itemRs.getInt("RI_ITEM_ID"),
                                itemRs.getInt("RI_RESTOCK_ID"),
                                itemRs.getInt("RI_PROD_ID"),
                                itemRs.getString("RI_PROD_NAME"),
                                itemRs.getString("RI_PROD_BRAND"),
                                itemRs.getString("RI_PROD_SIZE"),
                                itemRs.getString("RI_PROD_TYPE"),
                                itemRs.getDouble("RI_PROD_PRICE"),
                                itemRs.getInt("RI_RESTOCKED_QUANTITY")
                            );
                            items.add(item);
                        }
                    }
                }

                RestockData restock = new RestockData(restockId, year, month, day, time, items);
                restockList.add(restock);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    /**
     * Performs a restock operation for a single product, creating a restock event, adding a restock
     * item, and updating the product's quantity in a single transaction. Refreshes the product and
     * restock lists upon success. Displays appropriate error messages for invalid inputs or database errors.
     *
     * @param product  The product to restock. Must not be null and must have valid attributes.
     * @param quantity The quantity to add. Must be positive.
     * @return {@code true} if the restock operation is successful, {@code false} otherwise.
     * @throws NullPointerException if product is null.
     */
    public boolean restockProduct(ProductData product, int quantity) {
        // Validate inputs
        if (!validateRestockInputs(product, quantity)) {
            return false;
        }

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Insert restock event
            int restockId = createRestockEvent(conn);

            // Insert restock item
            insertRestockItem(conn, restockId, product, quantity);

            // Update product quantity
            updateProductQuantity(conn, product.getProductId(), quantity);

            conn.commit();
            productDataManager.loadProducts(); // Refresh product list
            loadRestocks(); // Refresh restock list
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, "Error Rollback (restockProduct)." + rollbackEx);
                }
            }
            handleSQLException(e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    JOptionPane.showMessageDialog(null, "Error Close (restockProduct)." + closeEx);
                }
            }
        }
    }

    /**
     * Performs a restock operation for multiple products as a single restock event, creating one
     * restock event, adding restock items, and updating product quantities in a single transaction.
     * Refreshes the product and restock lists upon success. Displays appropriate error messages for
     * invalid inputs or database errors.
     *
     * @param items A list of maps, each containing a ProductData object (key "product") and its restock
     *              quantity (key "quantity"). Must not be null or empty.
     * @return {@code true} if the restock operation is successful, {@code false} otherwise.
     * @throws NullPointerException if items is null.
     */
    public boolean restockMultipleProducts(List<Map<String, Object>> items) {
        if (items == null || items.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "No items provided for restocking.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Validate all inputs
        for (Map<String, Object> item : items) {
            ProductData product = (ProductData) item.get("product");
            Integer quantity = (Integer) item.get("quantity");
            if (!validateRestockInputs(product, quantity)) {
                return false;
            }
        }

        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            // Insert restock event
            int restockId = createRestockEvent(conn);

            // Insert restock items and update quantities
            for (Map<String, Object> item : items) {
                ProductData product = (ProductData) item.get("product");
                int quantity = (Integer) item.get("quantity");
                insertRestockItem(conn, restockId, product, quantity);
                updateProductQuantity(conn, product.getProductId(), quantity);
            }

            conn.commit();
            productDataManager.loadProducts(); // Refresh product list
            loadRestocks(); // Refresh restock list
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, "Error Rollback (restockMultipleProducts)." + rollbackEx);
                }
            }
            handleSQLException(e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    JOptionPane.showMessageDialog(null, "Error Close (restockMultipleProducts)." + closeEx);
                }
            }
        }
    }

    /**
     * Deletes a restock event and its associated items from the database using a transaction.
     * Refreshes the restock list upon success. Displays an error message if the restock ID does not
     * exist or a database error occurs.
     *
     * @param restockId The ID of the restock event to delete. Must exist in the database.
     * @return {@code true} if the deletion is successful, {@code false} otherwise.
     */
    public boolean deleteRestock(int restockId) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Begin transaction

            try (PreparedStatement pstmt = conn.prepareStatement(DELETE_RESTOCK_QUERY)) {
                pstmt.setInt(1, restockId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    throw new SQLException("No restock found with ID: " + restockId);
                }
            }

            conn.commit();
            loadRestocks(); // Refresh restock list
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    JOptionPane.showMessageDialog(null, "Error Rollback (deleteRestock)." + rollbackEx);
                }
            }
            handleSQLException(e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    JOptionPane.showMessageDialog(null, "Error Close (deleteRestock)." + closeEx);
                }
            }
        }
    }

    /**
     * Validates inputs for a restock operation based on database schema constraints for the
     * RestockItems table (e.g., non-null fields, non-negative price, positive quantity).
     * Displays appropriate error messages for invalid inputs.
     *
     * @param product  The product to restock. Must not be null and must have valid attributes.
     * @param quantity The quantity to restock. Must be positive.
     * @return {@code true} if the inputs are valid, {@code false} otherwise.
     * @throws NullPointerException if product is null.
     */
    private boolean validateRestockInputs(ProductData product, int quantity) {
        if (product == null) {
            JOptionPane.showMessageDialog(null,
                "Product cannot be null.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product name cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (product.getProductBrand() == null || product.getProductBrand().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product brand cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (product.getProductSize() == null || product.getProductSize().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product size cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (product.getProductType() == null || product.getProductType().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product type cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (product.getProductPrice() < 0) {
            JOptionPane.showMessageDialog(null,
                "Product price cannot be negative.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null,
                "Restock quantity must be greater than 0.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Creates a new restock event in the Restock table with the current date and time.
     * Validates date components to ensure they are non-empty.
     *
     * @param conn The database connection. Must not be null.
     * @return The ID of the created restock event.
     * @throws SQLException If a database error occurs or date components are invalid.
     * @throws NullPointerException if conn is null.
     */
    private int createRestockEvent(Connection conn) throws SQLException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String[] dateTimeParts = now.format(formatter).split(" ");
        String date = dateTimeParts[0];
        String[] dateParts = date.split("-");
        String year = dateParts[0];
        String month = dateParts[1];
        String day = dateParts[2];
        String time = dateTimeParts[1];

        // Validate date components
        if (year == null || year.trim().isEmpty()) {
            throw new SQLException("Restock date year cannot be empty.");
        }
        if (month == null || month.trim().isEmpty()) {
            throw new SQLException("Restock date month cannot be empty.");
        }
        if (day == null || day.trim().isEmpty()) {
            throw new SQLException("Restock date day cannot be empty.");
        }
        if (time == null || time.trim().isEmpty()) {
            throw new SQLException("Restock date time cannot be empty.");
        }

        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_RESTOCK_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, year);
            pstmt.setString(2, month);
            pstmt.setString(3, day);
            pstmt.setString(4, time);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Failed to create restock event.");
            }

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
                throw new SQLException("Failed to retrieve restock ID.");
            }
        }
    }

    /**
     * Inserts a restock item into the RestockItems table, recording the product details and
     * restocked quantity for a specific restock event.
     *
     * @param conn      The database connection. Must not be null.
     * @param restockId The ID of the restock event. Must exist in the Restock table.
     * @param product   The product being restocked. Must not be null and must have valid attributes.
     * @param quantity  The quantity restocked. Must be positive.
     * @throws SQLException If a database error occurs or the insertion fails.
     * @throws NullPointerException if conn or product is null.
     */
    private void insertRestockItem(Connection conn, int restockId, ProductData product, int quantity) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(INSERT_RESTOCK_ITEM_QUERY)) {
            pstmt.setInt(1, restockId);
            pstmt.setInt(2, product.getProductId());
            pstmt.setString(3, product.getProductName().trim());
            pstmt.setString(4, product.getProductBrand().trim());
            pstmt.setString(5, product.getProductSize().trim());
            pstmt.setString(6, product.getProductType().trim());
            pstmt.setDouble(7, product.getProductPrice());
            pstmt.setInt(8, quantity);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Failed to insert restock item.");
            }
        }
    }

    /**
     * Updates the quantity of a product in the Product table by adding the specified amount.
     *
     * @param conn      The database connection. Must not be null.
     * @param productId The ID of the product. Must exist in the Product table.
     * @param quantity  The quantity to add. Must be positive.
     * @throws SQLException If a database error occurs or the product ID does not exist.
     * @throws NullPointerException if conn is null.
     */
    private void updateProductQuantity(Connection conn, int productId, int quantity) throws SQLException {
        try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY_QUERY)) {
            pstmt.setInt(1, quantity);
            pstmt.setInt(2, productId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No product found with ID: " + productId);
            }
        }
    }

    /**
     * Handles SQL exceptions by displaying specific error messages based on the error type,
     * such as constraint violations (NOT NULL, CHECK, FOREIGN KEY) or general database errors.
     *
     * @param e The SQLException to handle. Must not be null.
     * @throws NullPointerException if e is null.
     */
    private void handleSQLException(SQLException e) {
        String errorMessage = e.getMessage();
        if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
            JOptionPane.showMessageDialog(null,
                "A required field (e.g., product name, brand, size, type, date) is null.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
            JOptionPane.showMessageDialog(null,
                "Invalid input: Price must be non-negative, and restock quantity must be positive.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } else if (errorMessage.contains("SQLITE_CONSTRAINT_FOREIGNKEY")) {
            JOptionPane.showMessageDialog(null,
                "Invalid restock or product ID referenced.",
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "Database error: " + errorMessage,
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}