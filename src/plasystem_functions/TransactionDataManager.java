package plasystem_functions;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Manages transaction data in the PlaSystem database, providing functionality to add, load, and
 * delete transactions, including their items, and update product quantities. Ensures database
 * consistency through transactions and validates inputs against schema constraints.
 */
public class TransactionDataManager {
    /** SQL query to insert a new transaction into the Transactions table. */
    private static final String INSERT_TRANSACTION_QUERY =
        "INSERT INTO Transactions (TRANS_DATE_YEAR, TRANS_DATE_MONTH, TRANS_DATE_DAY, TRANS_DATE_TIME, " +
        "TRANS_TOTAL_AMOUNT, TRANS_PAYMENT_AMOUNT, TRANS_CHANGE_AMOUNT) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    /** SQL query to insert a transaction item into the TransactionItems table. */
    private static final String INSERT_TRANSACTION_ITEM_QUERY =
        "INSERT INTO TransactionItems (TI_TRANS_ID, TI_PROD_ID, TI_PROD_NAME, TI_PROD_BRAND, TI_PROD_SIZE, " +
        "TI_PROD_TYPE, TI_PROD_BUYQUANTITY, TI_PROD_UNITPRICE, TI_PROD_TOTALPRICE) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    /** SQL query to select all transactions from the Transactions table. */
    private static final String SELECT_ALL_TRANSACTIONS_QUERY =
        "SELECT * FROM Transactions";
    
    /** SQL query to select transaction items for a specific transaction from the TransactionItems table. */
    private static final String SELECT_TRANSACTION_ITEMS_QUERY =
        "SELECT * FROM TransactionItems WHERE TI_TRANS_ID = ?";
    
    /** SQL query to delete a transaction from the Transactions table. */
    private static final String DELETE_TRANSACTION_QUERY =
        "DELETE FROM Transactions WHERE TRANS_ID = ?";
    
    /** SQL query to update the quantity of a product in the Product table. */
    private static final String UPDATE_PRODUCT_QUANTITY_QUERY =
        "UPDATE Product SET PROD_QUANTITY = PROD_QUANTITY - ? WHERE PROD_ID = ?";

    /** Manager for product data operations, used to refresh product quantities after transactions. */
    private final ProductDataManager productDataManager;
    
    /** In-memory list of transactions, synchronized with the database. */
    private final List<TransactionData> transactionList;

    /**
     * Constructs a TransactionDataManager with a dependency on ProductDataManager and initializes
     * the transaction list by loading all transactions from the database.
     *
     * @param productDataManager The manager for product data operations. Must not be null.
     * @throws NullPointerException if productDataManager is null.
     */
    public TransactionDataManager(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
        this.transactionList = new LinkedList<>();
        loadTransactions();
    }

    /**
     * Loads all transactions and their associated items from the database into the in-memory
     * transaction list. Clears the existing list before loading to ensure synchronization with
     * the database. Displays an error message if a database error occurs.
     */
    public void loadTransactions() {
        transactionList.clear();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_TRANSACTIONS_QUERY)) {

            while (rs.next()) {
                int transId = rs.getInt("TRANS_ID");
                List<TransactionItemData> items = loadTransactionItems(transId);
                TransactionData transaction = new TransactionData(
                    transId,
                    rs.getString("TRANS_DATE_YEAR"),
                    rs.getString("TRANS_DATE_MONTH"),
                    rs.getString("TRANS_DATE_DAY"),
                    rs.getString("TRANS_DATE_TIME"),
                    rs.getDouble("TRANS_TOTAL_AMOUNT"),
                    rs.getDouble("TRANS_PAYMENT_AMOUNT"),
                    rs.getDouble("TRANS_CHANGE_AMOUNT"),
                    items
                );
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error loading transactions: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads all transaction items for a given transaction ID from the TransactionItems table.
     * Displays an error message if a database error occurs.
     *
     * @param transId The ID of the transaction. Must exist in the Transactions table.
     * @return A list of TransactionItemData objects, possibly empty if no items are found.
     */
    private List<TransactionItemData> loadTransactionItems(int transId) {
        List<TransactionItemData> items = new LinkedList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_TRANSACTION_ITEMS_QUERY)) {
            pstmt.setInt(1, transId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                TransactionItemData item = new TransactionItemData(
                    rs.getInt("TI_ITEM_ID"),
                    rs.getInt("TI_TRANS_ID"),
                    rs.getInt("TI_PROD_ID"),
                    rs.getString("TI_PROD_NAME"),
                    rs.getString("TI_PROD_BRAND"),
                    rs.getString("TI_PROD_SIZE"),
                    rs.getString("TI_PROD_TYPE"),
                    rs.getInt("TI_PROD_BUYQUANTITY"),
                    rs.getDouble("TI_PROD_UNITPRICE"),
                    rs.getDouble("TI_PROD_TOTALPRICE")
                );
                items.add(item);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error loading transaction items: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
        }
        return items;
    }

    /**
     * Adds a new transaction to the database, including its items, and updates product quantities
     * in a single transaction. Validates inputs against schema constraints and rounds monetary
     * amounts to two decimal places. Refreshes the transaction and product lists upon success.
     * Displays appropriate error messages for invalid inputs or database errors.
     *
     * @param transDateYear    The year of the transaction date. Must not be null or empty.
     * @param transDateMonth   The month of the transaction date. Must not be null or empty.
     * @param transDateDay     The day of the transaction date. Must not be null or empty.
     * @param transDateTime    The time of the transaction. Must not be null or empty.
     * @param totalAmount      The total amount of the transaction. Must be non-negative.
     * @param paymentAmount    The payment amount provided. Must be at least equal to totalAmount.
     * @param changeAmount     The change returned. Must be non-negative.
     * @param transactionItems The list of transaction items. Must not be null or empty.
     * @return The ID of the newly created transaction, or -1 if the operation fails.
     * @throws NullPointerException if transactionItems is null.
     */
    public int addTransaction(String transDateYear, String transDateMonth, String transDateDay, String transDateTime,
                              double totalAmount, double paymentAmount, double changeAmount,
                              List<TransactionItemData> transactionItems) {
        // Validate inputs based on schema constraints
        if (transDateYear == null || transDateYear.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Transaction date year cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (transDateMonth == null || transDateMonth.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Transaction date month cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (transDateDay == null || transDateDay.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Transaction date day cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (transDateTime == null || transDateTime.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Transaction date time cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (totalAmount < 0) {
            JOptionPane.showMessageDialog(null,
                "Total amount cannot be negative.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (paymentAmount < totalAmount) {
            JOptionPane.showMessageDialog(null,
                "Payment amount must be at least equal to total amount.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (changeAmount < 0) {
            JOptionPane.showMessageDialog(null,
                "Change amount cannot be negative.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        if (transactionItems == null || transactionItems.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Transaction must include at least one item.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return -1;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int transactionId = -1;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            // Insert transaction
            pstmt = conn.prepareStatement(INSERT_TRANSACTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, transDateYear.trim());
            pstmt.setString(2, transDateMonth.trim());
            pstmt.setString(3, transDateDay.trim());
            pstmt.setString(4, transDateTime.trim());
            pstmt.setDouble(5, new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());
            pstmt.setDouble(6, paymentAmount);
            pstmt.setDouble(7, new BigDecimal(changeAmount).setScale(2, RoundingMode.HALF_UP).doubleValue());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    transactionId = rs.getInt(1);
                }
            } else {
                conn.rollback();
                return -1;
            }

            // Insert transaction items
            pstmt = conn.prepareStatement(INSERT_TRANSACTION_ITEM_QUERY);
            for (TransactionItemData item : transactionItems) {
                if (!validateTransactionItem(item)) {
                    conn.rollback();
                    return -1;
                }
                pstmt.setInt(1, transactionId);
                pstmt.setInt(2, item.getTI_productId());
                pstmt.setString(3, item.getTI_productName().trim());
                pstmt.setString(4, item.getTI_productBrand().trim());
                pstmt.setString(5, item.getTI_productSize().trim());
                pstmt.setString(6, item.getTI_productType().trim());
                pstmt.setInt(7, item.getTI_buyQuantity());
                pstmt.setDouble(8, item.getTI_unitPrice());
                pstmt.setDouble(9, new BigDecimal(item.getTI_totalPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                pstmt.addBatch();
            }
            int[] batchResults = pstmt.executeBatch();
            for (int result : batchResults) {
                if (result <= 0) {
                    conn.rollback();
                    return -1;
                }
            }

            // Update product quantities
            pstmt = conn.prepareStatement(UPDATE_PRODUCT_QUANTITY_QUERY);
            for (TransactionItemData item : transactionItems) {
                pstmt.setInt(1, item.getTI_buyQuantity());
                pstmt.setInt(2, item.getTI_productId());
                rowsAffected = pstmt.executeUpdate();
                if (rowsAffected == 0) {
                    conn.rollback();
                    JOptionPane.showMessageDialog(null,
                        "Failed to update product quantity for product ID: " + item.getTI_productId(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                    return -1;
                }
            }

            conn.commit();
            loadTransactions();
            productDataManager.loadProducts(); // Refresh product list
            return transactionId;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error AddTransaction1." + ex);
            }
            String errorMessage = e.getMessage();
            if (errorMessage.contains("SQLITE_CONSTRAINT_NOTNULL")) {
                JOptionPane.showMessageDialog(null,
                    "A required field is null.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            } else if (errorMessage.contains("SQLITE_CONSTRAINT_CHECK")) {
                JOptionPane.showMessageDialog(null,
                    "Invalid input: Ensure all amounts and quantities meet schema constraints.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Error adding transaction: " + errorMessage,
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            return -1;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error AddTransaction2." + e);
            }
        }
    }

    /**
     * Deletes a transaction from the database by its ID, including its associated items.
     * Refreshes the transaction list upon success. Displays an error message if the transaction
     * ID does not exist or a database error occurs.
     *
     * @param transactionId The ID of the transaction to delete. Must exist in the database.
     * @return {@code true} if the transaction was deleted successfully, {@code false} otherwise.
     */
    public boolean deleteTransaction(int transactionId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_TRANSACTION_QUERY)) {
            pstmt.setInt(1, transactionId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                loadTransactions(); // Refresh the transaction list
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Error deleting transaction: " + e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Validates a transaction item based on schema constraints for the TransactionItems table
     * (e.g., non-null fields, positive quantity, non-negative prices, total price at least
     * equal to unit price). Displays appropriate error messages for invalid inputs.
     *
     * @param item The TransactionItemData to validate. Must not be null.
     * @return {@code true} if the item is valid, {@code false} otherwise.
     * @throws NullPointerException if item is null.
     */
    private boolean validateTransactionItem(TransactionItemData item) {
        if (item.getTI_productName() == null || item.getTI_productName().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product name cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_productBrand() == null || item.getTI_productBrand().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product brand cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_productSize() == null || item.getTI_productSize().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product size cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_productType() == null || item.getTI_productType().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "Product type cannot be empty.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_buyQuantity() <= 0) {
            JOptionPane.showMessageDialog(null,
                "Buy quantity must be greater than 0.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_unitPrice() < 0) {
            JOptionPane.showMessageDialog(null,
                "Unit price cannot be negative.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (item.getTI_totalPrice() < item.getTI_unitPrice()) {
            JOptionPane.showMessageDialog(null,
                "Total price must be at least equal to unit price.",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Retrieves the in-memory list of all transactions, refreshing it from the database
     * before returning.
     *
     * @return An unmodifiable view of the list of TransactionData objects.
     */
    public List<TransactionData> getTransactionList() {
        loadTransactions(); // Refresh the list before returning
        return transactionList;
    }

    /**
     * Calculates the total value of a list of transaction items, summing their total prices
     * and rounding the result to two decimal places.
     *
     * @param items The list of TransactionItemData objects. Must not be null.
     * @return The total value of the transaction items, rounded to two decimal places.
     * @throws NullPointerException if items is null.
     */
    public double getTotal(List<TransactionItemData> items) {
        double sum = 0;
        for (TransactionItemData item : items) {
            sum += item.getTI_totalPrice();
        }
        return new BigDecimal(sum).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}