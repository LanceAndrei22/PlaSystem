package plasystem_functions;

/**
 * Represents information about a user account.
 */
public class UserAccount {
    // Attributes representing user account information
    protected int userId;
    protected String username;
    protected String userPassword;
    protected String userRole;

    /**
     * Constructor to initialize UserAccount with provided values.
     *
     * @param userId        The unique ID of the user.
     * @param username      The username of the user.
     * @param userPassword  The password of the user.
     * @param userRole      The role of the user (e.g., admin, cashier).
     */
    public UserAccount(int userId, String username, String userPassword, String userRole) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    /**
     * Default constructor for UserAccount.
     */
    public UserAccount() {
    }

    // Getter and Setter methods for each attribute

    // Setters and getters for User ID
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getUserId() {
        return userId;
    }

    // Setters and getters for Username
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    // Setters and getters for User Password
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserPassword() {
        return userPassword;
    }

    // Setters and getters for User Role
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public String getUserRole() {
        return userRole;
    }
}