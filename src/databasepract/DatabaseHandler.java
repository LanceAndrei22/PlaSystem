package databasepract;
import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:information.db";

    static {
        createTable(); // Ensure table exists when the class loads
    }

    // ✅ Create Table (Only if it Doesn't Exist)
    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS products (" +
                     "id INTEGER PRIMARY KEY, " +
                     "name TEXT, " +
                     "brand TEXT, " +
                     "size TEXT, " +
                     "type TEXT, " +
                     "quantity INTEGER, " +
                     "price REAL)";

        try (Connection conn = (Connection) DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Insert Product
    public static void insertProduct(MainProduct product) {
        String sql = "INSERT INTO products (id, name, brand, size, type, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = (Connection) DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, product.getId());
            pstmt.setString(2, product.getName());
            pstmt.setString(3, product.getBrand());
            pstmt.setString(4, product.getSize());
            pstmt.setString(5, product.getType());
            pstmt.setInt(6, product.getQuantity());
            pstmt.setDouble(7, product.getPrice());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete Product
    public static void deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = (Connection) DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
