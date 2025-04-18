package plasystem_functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:database/PlaSystem.db";
    
    private static final String SCHEMA = 
        "CREATE TABLE UserAccount (USER_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, USER_NAME TEXT NOT NULL UNIQUE,"
            + "USER_PASSWORD TEXT NOT NULL, USER_ROLE TEXT NOT NULL CHECK (USER_ROLE IN ('admin', 'cashier', 'store_manager',"
            + "'inventory_manager', 'restocker')), UNIQUE (USER_NAME, USER_PASSWORD));" +
        "CREATE TABLE Product (PROD_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, PROD_NAME TEXT NOT NULL, PROD_BRAND TEXT NOT NULL,"
            + "PROD_SIZE TEXT NOT NULL, PROD_TYPE TEXT NOT NULL, PROD_PRICE REAL NOT NULL CHECK (PROD_PRICE >= 0),"
            + "PROD_QUANTITY INTEGER NOT NULL CHECK (PROD_QUANTITY >= 0),"
            + "PROD_RESTOCK_VALUE INTEGER NOT NULL CHECK (PROD_RESTOCK_VALUE >= 0));" +
        "CREATE TABLE Restock (RESTOCK_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, RESTOCK_DATE TEXT NOT NULL);" +
        "CREATE TABLE RestockItems (RI_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "RI_RESTOCK_ID INTEGER NOT NULL REFERENCES Restock (RESTOCK_ID) ON DELETE CASCADE ON UPDATE NO ACTION,"
            + "RI_PROD_ID INTEGER REFERENCES Product (PROD_ID) ON DELETE SET NULL ON UPDATE NO ACTION, RI_PROD_NAME TEXT NOT NULL,"
            + "RI_PROD_BRAND TEXT NOT NULL, RI_PROD_SIZE TEXT NOT NULL, RI_PROD_TYPE TEXT NOT NULL,"
            + "RI_PROD_PRICE REAL NOT NULL CHECK (RI_PROD_PRICE >= 0), RI_RESTOCKED_QUANTITY INTEGER NOT NULL CHECK (RI_RESTOCKED_QUANTITY > 0));" +
        "CREATE TABLE Transactions (TRANS_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TRANS_DATE TEXT NOT NULL,"
            + "TRANS_TOTAL_AMOUNT REAL NOT NULL CHECK (TRANS_TOTAL_AMOUNT >= 0), TRANS_PAYMENT_AMOUNT REAL NOT NULL CHECK (TRANS_PAYMENT_AMOUNT >= 0),"
            + "TRANS_CHANGE_AMOUNT REAL NOT NULL CHECK (TRANS_CHANGE_AMOUNT >= 0));" +
        "CREATE TABLE TransactionItems (TI_ITEM_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            + "TI_TRANS_ID INTEGER NOT NULL REFERENCES Transactions (TRANS_ID) ON DELETE CASCADE ON UPDATE NO ACTION,"
            + "TI_PROD_ID INTEGER REFERENCES Product (PROD_ID) ON DELETE SET NULL ON UPDATE NO ACTION, TI_PROD_NAME TEXT NOT NULL,"
            + "TI_PROD_BRAND TEXT NOT NULL, TI_PROD_SIZE TEXT NOT NULL, TI_PROD_TYPE TEXT NOT NULL,"
            + "TI_PROD_BUYQUANTITY INTEGER NOT NULL CHECK (TI_PROD_BUYQUANTITY > 0),"
            + "TI_PROD_UNITPRICE REAL NOT NULL CHECK (TI_PROD_UNITPRICE >= 0),"
            + "TI_PROD_TOTALPRICE REAL NOT NULL CHECK (TI_PROD_TOTALPRICE >= 0));";
    
    private static final String INSERT_DEFAULT_ADMIN = 
        "INSERT INTO UserAccount (USER_NAME, USER_PASSWORD, USER_ROLE) VALUES ('admin', 'admin', 'admin');";

    public static Connection getConnection() throws SQLException {
        File dbFolder = new File("database");
        File dbFile = new File("database/PlaSystem.db");
        boolean initializeDatabase = false;

        // Check if the database folder exists, create it if it doesn't
        if (!dbFolder.exists()) {
            dbFolder.mkdirs();
        }

        // Check if the database file exists
        if (!dbFile.exists()) {
            initializeDatabase = true;
        }

        try {
            // Attempt to establish a connection
            Connection conn = DriverManager.getConnection(DB_URL);

            // If the database file was just created or doesn't have the schema, initialize it
            if (initializeDatabase) {
                try (Statement stmt = conn.createStatement()) {
                    // Execute schema creation
                    String[] schemaStatements = SCHEMA.split(";");
                    for (String statement : schemaStatements) {
                        if (!statement.trim().isEmpty()) {
                            stmt.executeUpdate(statement.trim());
                        }
                    }

                    // Insert default admin user
                    stmt.executeUpdate(INSERT_DEFAULT_ADMIN);
                }
            }
            return conn;
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw e;
        }
    }
}