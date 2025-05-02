package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Manages product data in the PlaSystem database, providing functionality to add, edit, delete,
 * and load products, as well as update a JTable with product information. Maintains an in-memory
 * list of products synchronized with the database.
 */
public class ProductDataManager {
    /** SQL query to select all products from the Product table. */
    private static final String SELECT_ALL_PRODUCTS_QUERY = "SELECT * FROM Product";
    
    /** SQL query to insert a new product into the Product table. */
    private static final String INSERT_PRODUCT_QUERY = 
        "INSERT INTO Product (PROD_NAME, PROD_BRAND, PROD_SIZE, PROD_TYPE, PROD_PRICE, PROD_QUANTITY, PROD_RESTOCK_VALUE) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    /** SQL query to update an existing product in the Product table. */
    private static final String UPDATE_PRODUCT_QUERY = 
        "UPDATE Product SET PROD_NAME = ?, PROD_BRAND = ?, PROD_SIZE = ?, PROD_TYPE = ?, PROD_PRICE = ?, " +
        "PROD_QUANTITY = ?, PROD_RESTOCK_VALUE = ? WHERE PROD_ID = ?";
    
    /** SQL query to delete a product from the Product table. */
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM Product WHERE PROD_ID = ?";

    /** In-memory list of ProductData objects, synchronized with the database. */
    private final List<ProductData> productList;

    /**
     * Constructs a ProductDataManager, initializing an empty product list and loading
     * all products from the database.
     */
    public ProductDataManager() {
        this.productList = new LinkedList<>();
        loadProducts();
    }

    /**
     * Loads all products from the Product table into the in-memory product list.
     * Clears the existing list before loading to ensure synchronization with the database.
     * Displays an error message if a database error occurs.
     */
    public void loadProducts() {
        productList.clear();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_PRODUCTS_QUERY)) {
            
            while (rs.next()) {
                ProductData product = new ProductData(
                    rs.getInt("PROD_ID"),
                    rs.getString("PROD_NAME"),
                    rs.getString("PROD_BRAND"),
                    rs.getString("PROD_SIZE"),
                    rs.getString("PROD_TYPE"),
                    rs.getDouble("PROD_PRICE"),
                    rs.getInt("PROD_QUANTITY"),
                    rs.getInt("PROD_RESTOCK_VALUE")
                );
                productList.add(product);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error loading products: " + e.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Adds a new product to the Product table in the database. Validates input parameters
     * to ensure they meet database constraints (non-null, non-empty strings, non-negative numbers).
     * Refreshes the in-memory product list upon successful insertion. Displays appropriate
     * error messages for invalid inputs or database errors.
     *
     * @param name         The product name. Must not be null or empty.
     * @param brand        The product brand. Must not be null or empty.
     * @param size         The product size. Must not be null or empty.
     * @param type         The product type. Must not be null or empty.
     * @param price        The product price. Must be non-negative.
     * @param quantity     The product quantity. Must be non-negative.
     * @param restockValue The product restock value. Must be non-negative.
     * @return {@code true} if the product was added successfully, {@code false} otherwise.
     */
    public boolean addProduct(String name, String brand, String size, String type,
                             double price, int quantity, int restockValue) {
        // Validate inputs
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Product name cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (brand == null || brand.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Brand cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (size == null || size.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Size cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (type == null || type.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Type cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (price < 0) {
            JOptionPane.showMessageDialog(null, 
                "Price cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, 
                "Quantity cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (restockValue < 0) {
            JOptionPane.showMessageDialog(null, 
                "Restock value cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_PRODUCT_QUERY)) {
            
            pstmt.setString(1, name.trim());
            pstmt.setString(2, brand.trim());
            pstmt.setString(3, size.trim());
            pstmt.setString(4, type.trim());
            pstmt.setDouble(5, price);
            pstmt.setInt(6, quantity);
            pstmt.setInt(7, restockValue);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadProducts(); // Refresh the product list
                return true;
            }
            return false;
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
                JOptionPane.showMessageDialog(null, 
                    "A required field (name, brand, size, type, price, quantity, or restock value) is null.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid input: Price, quantity, or restock value must be non-negative.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error adding product: " + errorMessage,
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    /**
     * Updates an existing product in the Product table. Validates input parameters to ensure
     * they meet database constraints (non-null, non-empty strings, non-negative numbers).
     * Refreshes the in-memory product list upon successful update. Displays appropriate
     * error messages for invalid inputs, non-existent product IDs, or database errors.
     *
     * @param productId    The ID of the product to update. Must exist in the database.
     * @param name         The new product name. Must not be null or empty.
     * @param brand        The new product brand. Must not be null or empty.
     * @param size         The new product size. Must not be null or empty.
     * @param type         The new product type. Must not be null or empty.
     * @param price        The new product price. Must be non-negative.
     * @param quantity     The new product quantity. Must be non-negative.
     * @param restockValue The new product restock value. Must be non-negative.
     * @return {@code true} if the product was updated successfully, {@code false} otherwise.
     */
    public boolean updateProduct(int productId, String name, String brand, String size, String type,
                                double price, int quantity, int restockValue) {
        // Validate inputs
        if (name == null || name.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Product name cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (brand == null || brand.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Brand cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (size == null || size.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Size cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (type == null || type.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Type cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (price < 0) {
            JOptionPane.showMessageDialog(null, 
                "Price cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, 
                "Quantity cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (restockValue < 0) {
            JOptionPane.showMessageDialog(null, 
                "Restock value cannot be negative.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_PRODUCT_QUERY)) {
            
            pstmt.setString(1, name.trim());
            pstmt.setString(2, brand.trim());
            pstmt.setString(3, size.trim());
            pstmt.setString(4, type.trim());
            pstmt.setDouble(5, price);
            pstmt.setInt(6, quantity);
            pstmt.setInt(7, restockValue);
            pstmt.setInt(8, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadProducts(); // Refresh the product list
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No product found with the specified ID.",
                    "Update Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
                JOptionPane.showMessageDialog(null, 
                    "A required field (name, brand, size, type, price, quantity, or restock value) is null.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid input: Price, quantity, or restock value must be non-negative.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error updating product: " + errorMessage,
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

    /**
     * Deletes a product from the Product table based on its ID. Refreshes the in-memory
     * product list upon successful deletion. Displays an error message if the product ID
     * does not exist or a database error occurs.
     *
     * @param productId The ID of the product to delete. Must exist in the database.
     * @return {@code true} if the product was deleted successfully, {@code false} otherwise.
     */
    public boolean deleteProduct(int productId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_PRODUCT_QUERY)) {
            
            pstmt.setInt(1, productId);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadProducts(); // Refresh the product list
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No product found with the specified ID.",
                    "Delete Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error deleting product: " + e.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Retrieves the in-memory list of all products.
     *
     * @return An unmodifiable view of the list of ProductData objects.
     */
    public List<ProductData> getList() {
        return productList;
    }

    /**
     * Updates the provided JTable with the current product list, populating it with
     * product attributes. Clears existing rows before adding new data.
     *
     * @param table The JTable to update. Must have a DefaultTableModel and sufficient
     *              columns to display product attributes (ID, name, brand, size, type,
     *              price, quantity, restock value).
     * @throws NullPointerException if table or its model is null.
     */
    public void updateTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows

        for (ProductData product : productList) {
            model.addRow(new Object[] {
                product.getProductId(),
                product.getProductName(),
                product.getProductBrand(),
                product.getProductSize(),
                product.getProductType(),
                product.getProductPrice(),
                product.getProductQuantity(),
                product.getProductRestockValue()
            });
        }
    }
}