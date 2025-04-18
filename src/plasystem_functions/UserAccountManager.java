package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class UserAccountManager {
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM UserAccount";
    private static final String INSERT_USER_QUERY = "INSERT INTO UserAccount (USER_NAME, USER_PASSWORD, USER_ROLE) VALUES (?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM UserAccount WHERE USER_NAME = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE UserAccount SET USER_NAME = ?, USER_PASSWORD = ?, USER_ROLE = ? WHERE USER_NAME = ?";
    
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
    
    // Method to add a new user account to the database
    public boolean addUserAccount(String username, String password, String role) {
        // Validate inputs
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Username cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (password == null || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Password cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (role == null || role.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Role cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Validate role against allowed values
        String[] validRoles = {"admin", "cashier", "store_manager", "inventory_manager", "restocker"};
        boolean isValidRole = false;
        for (String validRole : validRoles) {
            if (validRole.equals(role)) {
                isValidRole = true;
                break;
            }
        }
        if (!isValidRole) {
            JOptionPane.showMessageDialog(null, 
                "Invalid role. Must be one of: admin, cashier, store_manager, inventory_manager, restocker.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_USER_QUERY)) {
            
            pstmt.setString(1, username.trim());
            pstmt.setString(2, password.trim());
            pstmt.setString(3, role);
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadUserAccounts(); // Refresh the userAccounts list
                return true;
            }
            return false;
        } catch (SQLException e) {
            // SQLite error codes for specific constraints
            String errorMessage = e.getMessage();
            int errorCode = e.getErrorCode();
            
            if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
                JOptionPane.showMessageDialog(null, 
                    "A required field (username, password, or role) is null.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_UNIQUE")) {
                if (errorMessage.contains("UserAccount.USER_NAME")) {
                    JOptionPane.showMessageDialog(null, 
                        "Username already exists. Please choose a different username.",
                        "Duplicate Username", 
                        JOptionPane.ERROR_MESSAGE);
                } else if (errorMessage.contains("UserAccount.USER_NAME, UserAccount.USER_PASSWORD")) {
                    JOptionPane.showMessageDialog(null, 
                        "This username and password combination already exists.",
                        "Duplicate Username/Password", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid role. Must be one of: admin, cashier, store_manager, inventory_manager, restocker.",
                    "Invalid Role", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error adding user account: " + errorMessage,
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }
    
    // Method to delete a user account from the database
    public boolean deleteUserAccount(String username) {
        if (username == null || username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Username cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_USER_QUERY)) {
            
            pstmt.setString(1, username.trim());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadUserAccounts(); // Refresh the userAccounts list
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No user found with the specified username.",
                    "Delete Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error deleting user account: " + e.getMessage(),
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Method to update a user account in the database
    public boolean updateUserAccount(String originalUsername, String newUsername, String newPassword, String newRole) {
        // Validate inputs
        if (originalUsername == null || originalUsername.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "Original username cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (newUsername == null || newUsername.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "New username cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "New password cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (newRole == null || newRole.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "New role cannot be empty.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Validate role
        String[] validRoles = {"admin", "cashier", "store_manager", "inventory_manager", "restocker"};
        boolean isValidRole = false;
        for (String validRole : validRoles) {
            if (validRole.equals(newRole)) {
                isValidRole = true;
                break;
            }
        }
        if (!isValidRole) {
            JOptionPane.showMessageDialog(null, 
                "Invalid role. Must be one of: admin, cashier, store_manager, inventory_manager, restocker.",
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_USER_QUERY)) {
            
            pstmt.setString(1, newUsername.trim());
            pstmt.setString(2, newPassword.trim());
            pstmt.setString(3, newRole);
            pstmt.setString(4, originalUsername.trim());
            
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadUserAccounts(); // Refresh the userAccounts list
                return true;
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No user found with the specified original username.",
                    "Update Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (SQLException e) {
            String errorMessage = e.getMessage();
            if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
                JOptionPane.showMessageDialog(null, 
                    "A required field (username, password, or role) is null.",
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_UNIQUE")) {
                if (errorMessage.contains("UserAccount.USER_NAME")) {
                    JOptionPane.showMessageDialog(null, 
                        "New username already exists. Please choose a different username.",
                        "Duplicate Username", 
                        JOptionPane.ERROR_MESSAGE);
                } else if (errorMessage.contains("UserAccount.USER_NAME, UserAccount.USER_PASSWORD")) {
                    JOptionPane.showMessageDialog(null, 
                        "This username and password combination already exists.",
                        "Duplicate Username/Password", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
                JOptionPane.showMessageDialog(null, 
                    "Invalid role. Must be one of: admin, cashier, store_manager, inventory_manager, restocker.",
                    "Invalid Role", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, 
                    "Error updating user account: " + errorMessage,
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
            return false;
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
}