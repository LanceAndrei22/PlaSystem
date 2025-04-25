package plasystem_functions;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Manages restocking operations in the PlaSystem database, including creating, retrieving,
 * and deleting restock events, with comprehensive error checking based on schema constraints.
 */
public class RestockDataManager {
    private static final String INSERT_RESTOCK_QUERY = 
        "INSERT INTO Restock (RESTOCK_DATE_YEAR, RESTOCK_DATE_MONTH, RESTOCK_DATE_DAY, RESTOCK_DATE_TIME) " +
        "VALUES (?, ?, ?, ?)";
    private static final String INSERT_RESTOCK_ITEM_QUERY = 
        "INSERT INTO RestockItems (RI_RESTOCK_ID, RI_PROD_ID, RI_PROD_NAME, RI_PROD_BRAND, " +
        "RI_PROD_SIZE, RI_PROD_TYPE, RI_PROD_PRICE, RI_RESTOCKED_QUANTITY) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUANTITY_QUERY = 
        "UPDATE Product SET PROD_QUANTITY = PROD_QUANTITY + ? WHERE PROD_ID = ?";
    private static final String DELETE_RESTOCK_QUERY = 
        "DELETE FROM Restock WHERE RESTOCK_ID = ?";
    private static final String SELECT_RESTOCK_QUERY = 
        "SELECT * FROM Restock";
    private static final String SELECT_RESTOCK_ITEMS_QUERY = 
        "SELECT * FROM RestockItems WHERE RI_RESTOCK_ID = ?";

    private final ProductDataManager productDataManager;
    private final LinkedList<RestockData> restockList;

    /**
     * Constructor initializes the ProductDataManager dependency and loads restock data.
     *
     * @param productDataManager The manager for product data operations.
     */
    public RestockDataManager(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
        this.restockList = new LinkedList<>();
        loadRestocks();
    }

    /**
     * Retrieves a copy of the list of restock events.
     *
     * @return A list of RestockData objects.
     */
    public List<RestockData> getRestockList() {
        return restockList;
    }

    /**
     * Loads all restock events and their items from the database into restockList.
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
     * Performs a restock operation for a single product.
     *
     * @param product The product to restock.
     * @param quantity The quantity to add.
     * @return True if restocking is successful, false otherwise.
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
                    rollbackEx.printStackTrace();
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
                    closeEx.printStackTrace();
                }
            }
        }
    }

    /**
     * Performs a restock operation for multiple products as a single restock event.
     *
     * @param items A list of maps, each containing a ProductData object and its restock quantity.
     * @return True if restocking is successful, false otherwise.
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
                    rollbackEx.printStackTrace();
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
                    closeEx.printStackTrace();
                }
            }
        }
    }

    /**
     * Deletes a restock event and its associated items from the database.
     *
     * @param restockId The ID of the restock to delete.
     * @return True if deletion is successful, false otherwise.
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
                    rollbackEx.printStackTrace();
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
                    closeEx.printStackTrace();
                }
            }
        }
    }

    /**
     * Validates inputs for restocking based on database schema constraints.
     *
     * @param product The product to restock.
     * @param quantity The quantity to add.
     * @return True if inputs are valid, false otherwise.
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
     * Creates a new restock event in the Restock table.
     *
     * @param conn The database connection.
     * @return The ID of the created restock event.
     * @throws SQLException If a database error occurs.
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
     * Inserts a restock item into the RestockItems table.
     *
     * @param conn The database connection.
     * @param restockId The ID of the restock event.
     * @param product The product being restocked.
     * @param quantity The quantity restocked.
     * @throws SQLException If a database error occurs.
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
     * Updates the quantity of a product in the Product table.
     *
     * @param conn The database connection.
     * @param productId The ID of the product.
     * @param quantity The quantity to add.
     * @throws SQLException If a database error occurs.
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
     * Handles SQL exceptions by providing specific error messages based on the error type.
     *
     * @param e The SQLException to handle.
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