package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
        } catch (SQLException e) {
            System.err.println("Error loading user accounts: " + e.getMessage());
        }
    }

    // Method to get all user accounts
    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    // Method to validate user login credentials
    public boolean validateUserLogin(String username, String password) {
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getUsername().equals(username) && userAccount.getUserPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Additional methods for adding, updating, or deleting users can go here
}