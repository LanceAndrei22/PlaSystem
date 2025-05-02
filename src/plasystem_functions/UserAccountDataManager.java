package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

/**
 * Manages user account data in the PlaSystem database, providing functionality to add, load,
 * update, delete, and validate user accounts. Maintains an in-memory list of user accounts
 * synchronized with the database and tracks the logged-in user's username and role.
 */
public class UserAccountDataManager {
    /** SQL query to select all user accounts from the UserAccount table. */
    private static final String SELECT_ALL_USERS_QUERY = "SELECT * FROM UserAccount";
    
    /** SQL query to insert a new user account into the UserAccount table. */
    private static final String INSERT_USER_QUERY = "INSERT INTO UserAccount (USER_NAME, USER_PASSWORD, USER_ROLE) VALUES (?, ?, ?)";
    
    /** SQL query to delete a user account from the UserAccount table by username. */
    private static final String DELETE_USER_QUERY = "DELETE FROM UserAccount WHERE USER_NAME = ?";
    
    /** SQL query to update an existing user account in the UserAccount table. */
    private static final String UPDATE_USER_QUERY = "UPDATE UserAccount SET USER_NAME = ?, USER_PASSWORD = ?, USER_ROLE = ? WHERE USER_NAME = ?";

    /** In-memory list of user accounts, synchronized with the database. */
    private final List<UserAccountData> userAccounts;
    
    /** The username of the currently logged-in user, or null if no user is logged in. */
    private String loggedInUsername;
    
    /** The role of the currently logged-in user, or null if no user is logged in. */
    private String loggedInRole;

    /**
     * Constructs a UserAccountDataManager, initializing an empty user accounts list and
     * setting logged-in user details to null.
     */
    public UserAccountDataManager() {
        this.userAccounts = new LinkedList<>(); // Initialize as LinkedList
        this.loggedInUsername = null;
        this.loggedInRole = null;
    }

    /**
     * Gets the username of the currently logged-in user.
     *
     * @return The logged-in username, or null if no user is logged in.
     */
    public String getLoggedInUsername() {
        return loggedInUsername;
    }

    /**
     * Gets the role of the currently logged-in user.
     *
     * @return The logged-in user's role, or null if no user is logged in.
     */
    public String getLoggedInRole() {
        return loggedInRole;
    }

    /**
     * Sets the details of the currently logged-in user.
     *
     * @param username The username of the logged-in user. May be null to clear the user.
     * @param role     The role of the logged-in user. May be null to clear the user.
     */
    public void setLoggedInUser(String username, String role) {
        this.loggedInUsername = username;
        this.loggedInRole = role;
    }

    /**
     * Clears the details of the currently logged-in user, setting both username and role to null.
     */
    public void clearLoggedInUser() {
        this.loggedInUsername = null;
        this.loggedInRole = null;
    }

    /**
     * Loads all user accounts from the UserAccount table into the in-memory list.
     * Clears the existing list before loading to ensure synchronization with the database.
     * Displays an error message if a database error occurs.
     */
    public void loadUserAccounts() {
        userAccounts.clear();
        try (Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS_QUERY)) {
            
            while (rs.next()) {
                int userId = rs.getInt("USER_ID");
                String userName = rs.getString("USER_NAME");
                String userPassword = rs.getString("USER_PASSWORD");
                String userRole = rs.getString("USER_ROLE");
                
                UserAccountData userAccount = new UserAccountData(userId, userName, userPassword, userRole);
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

    /**
     * Adds a new user account to the UserAccount table. Validates inputs to ensure they meet
     * database constraints (non-null, non-empty, valid role). Refreshes the in-memory user
     * accounts list upon success. Displays appropriate error messages for invalid inputs or
     * database errors (e.g., duplicate username).
     *
     * @param username The username of the new user. Must not be null or empty and must be unique.
     * @param password The password of the new user. Must not be null or empty.
     * @param role     The role of the new user. Must be one of 'admin', 'cashier', 'store_manager',
     *                 'inventory_manager', or 'restocker'.
     * @return {@code true} if the user account was added successfully, {@code false} otherwise.
     */
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

    /**
     * Deletes a user account from the UserAccount table by username. Refreshes the in-memory
     * user accounts list upon success. Displays an error message if the username does not exist
     * or a database error occurs.
     *
     * @param username The username of the user account to delete. Must not be null or empty.
     * @return {@code true} if the user account was deleted successfully, {@code false} otherwise.
     */
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

    /**
     * Updates an existing user account in the UserAccount table, identified by the original
     * username. Validates inputs to ensure they meet database constraints (non-null, non-empty,
     * valid role). Refreshes the in-memory user accounts list upon success. Displays appropriate
     * error messages for invalid inputs, non-existent usernames, or database errors (e.g.,
     * duplicate username).
     *
     * @param originalUsername The original username of the user account to update. Must not be null or empty.
     * @param newUsername      The new username. Must not be null or empty and must be unique.
     * @param newPassword      The new password. Must not be null or empty.
     * @param newRole          The new role. Must be one of 'admin', 'cashier', 'store_manager',
     *                         'inventory_manager', or 'restocker'.
     * @return {@code true} if the user account was updated successfully, {@code false} otherwise.
     */
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

    /**
     * Retrieves the in-memory list of all user accounts.
     *
     * @return An unmodifiable view of the list of UserAccountData objects.
     */
    public List<UserAccountData> getUserAccounts() {
        return userAccounts;
    }

    /**
     * Validates user login credentials by querying the UserAccount table. If credentials are
     * valid, stores the logged-in user's username and role. Displays an error message if a
     * database error occurs.
     *
     * @param username The username to validate. Must not be null.
     * @param password The password to validate. Must not be null.
     * @return {@code true} if the credentials are valid and the user is logged in, {@code false} otherwise.
     * @throws NullPointerException if username or password is null.
     */
    public boolean validateUserLogin(String username, String password) {
        String sql = "SELECT USER_ROLE FROM UserAccount WHERE USER_NAME = ? AND USER_PASSWORD = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Store logged-in user details
                    setLoggedInUser(username, rs.getString("USER_ROLE"));
                    return true;
                }
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}