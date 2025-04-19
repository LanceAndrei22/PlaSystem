package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Manages product data in the PlaSystem database, providing methods to add, edit, delete, and load products.
 */
public class ProductDataManager {
    private static final String SELECT_ALL_PRODUCTS_QUERY = "SELECT * FROM Product";
    private static final String INSERT_PRODUCT_QUERY = 
        "INSERT INTO Product (PROD_NAME, PROD_BRAND, PROD_SIZE, PROD_TYPE, PROD_PRICE, PROD_QUANTITY, PROD_RESTOCK_VALUE) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT_QUERY = 
        "UPDATE Product SET PROD_NAME = ?, PROD_BRAND = ?, PROD_SIZE = ?, PROD_TYPE = ?, PROD_PRICE = ?, " +
        "PROD_QUANTITY = ?, PROD_RESTOCK_VALUE = ? WHERE PROD_ID = ?";
    private static final String DELETE_PRODUCT_QUERY = "DELETE FROM Product WHERE PROD_ID = ?";

    private List<ProductData> productList;

    /**
     * Constructor initializes the product list.
     */
    public ProductDataManager() {
        this.productList = new LinkedList<>();
        loadProducts();
    }

    /**
     * Loads all products from the database into the productList.
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
     * Adds a new product to the database.
     *
     * @param name        Product name.
     * @param brand       Product brand.
     * @param size        Product size.
     * @param type        Product type.
     * @param price       Product price.
     * @param quantity    Product quantity.
     * @param restockValue Product restock value.
     * @return True if added successfully, false otherwise.
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
     * Updates an existing product in the database.
     *
     * @param productId   The ID of the product to update.
     * @param name        New product name.
     * @param brand       New product brand.
     * @param size        New product size.
     * @param type        New product type.
     * @param price       New product price.
     * @param quantity    New product quantity.
     * @param restockValue New restock value.
     * @return True if updated successfully, false otherwise.
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
     * Deletes a product from the database.
     *
     * @param productId The ID of the product to delete.
     * @return True if deleted successfully, false otherwise.
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
     * Gets the list of all products.
     *
     * @return The list of ProductData objects.
     */
    public List<ProductData> getList() {
        return productList;
    }

    /**
     * Updates the JTable with the current product list.
     *
     * @param table The JTable to update.
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