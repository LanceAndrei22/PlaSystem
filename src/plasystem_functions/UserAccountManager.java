package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class UserAccountManager {
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM UserAccount";
    
    // Using LinkedList instead of ArrayList
    private List<UserAccount> userAccounts;
    
    public UserAccountManager() {
        this.userAccounts = new LinkedList<>(); // Initialize as LinkedList
    }
    
    // Method to fetch and load user accounts from the database
    public void loadUserAccounts() {
        try (Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS_QUERY)) {
            
            while (rs.next()) {
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("USER_NAME");
                String userPassword = rs.getString("USER_PASSWORD");
                String userRole = rs.getString("USER_ROLE");
                
                UserAccount userAccount = new UserAccount(userId, userName, userPassword, userRole);
                userAccounts.add(userAccount); // Add to LinkedList
            }
        } catch (SQLException error) {
            System.err.println("Error loading user accounts: " + error.getMessage());
            javax.swing.JOptionPane.showMessageDialog(null, 
                "An error occurred while loading user accounts:\n" + error.getMessage(),
                "Database Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to get all user accounts
    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    // Method to validate user login credentials securely
    public boolean validateUserLogin(String username, String password) {
        String sql = "SELECT 1 FROM UserAccount WHERE USER_NAME = ? AND USER_PASSWORD = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // If a record is found, login is valid
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


    // Additional methods for adding, updating, or deleting users can go here
}