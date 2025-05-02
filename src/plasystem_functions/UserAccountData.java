package plasystem_functions;

/**
 * Represents a user account in the PlaSystem database, mapping to the UserAccount table.
 * Stores attributes such as user ID, username, password, and role, with getters and setters
 * for accessing and modifying these attributes.
 */
public class UserAccountData {
    /** The unique ID of the user (USER_ID, INTEGER PRIMARY KEY AUTOINCREMENT). */
    protected int userId;
    
    /** The username of the user (USER_NAME, TEXT NOT NULL UNIQUE). */
    protected String username;
    
    /** The password of the user (USER_PASSWORD, TEXT NOT NULL). */
    protected String userPassword;
    
    /** The role of the user (USER_ROLE, TEXT NOT NULL, CHECK IN ('admin', 'cashier', 'store_manager', 'inventory_manager', 'restocker')). */
    protected String userRole;

    /**
     * Constructs a UserAccountData object with the specified values, initializing all attributes.
     *
     * @param userId       The unique ID of the user (auto-incremented by the database).
     * @param username     The username of the user. Should not be null to match database constraints.
     * @param userPassword The password of the user. Should not be null to match database constraints.
     * @param userRole     The role of the user. Should be one of 'admin', 'cashier', 'store_manager',
     *                     'inventory_manager', or 'restocker' to match database constraints.
     */
    public UserAccountData(int userId, String username, String userPassword, String userRole) {
        this.userId = userId;
        this.username = username;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

    /**
     * Default constructor. Initializes a UserAccountData object with default values
     * (0 for userId, null for String fields).
     */
    public UserAccountData() {
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param userId The new user ID (auto-incremented by the database).
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the unique ID of the user.
     *
     * @return The user ID (USER_ID).
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username. Should not be null and must be unique to match database constraints.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username (USER_NAME).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the password of the user.
     *
     * @param userPassword The new password. Should not be null to match database constraints.
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password (USER_PASSWORD).
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the role of the user.
     *
     * @param userRole The new role. Should be one of 'admin', 'cashier', 'store_manager',
     *                 'inventory_manager', or 'restocker' to match database constraints.
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Gets the role of the user.
     *
     * @return The user role (USER_ROLE).
     */
    public String getUserRole() {
        return userRole;
    }
}