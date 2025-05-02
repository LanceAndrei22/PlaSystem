package plasystem_functions;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a transaction in the PlaSystem database, mapping to the Transactions table.
 * Stores the transaction's ID, date components, total amount, payment amount, change amount,
 * and associated items. Provides getters for accessing attributes and a method to format
 * the date. Amounts are rounded to two decimal places where specified.
 */
public class TransactionData {
    /** The unique ID of the transaction (TRANS_ID, INTEGER PRIMARY KEY AUTOINCREMENT). */
    private final int transactionId;
    
    /** The year of the transaction date (TRANS_DATE_YEAR, TEXT NOT NULL). */
    private final String transDateYear;
    
    /** The month of the transaction date (TRANS_DATE_MONTH, TEXT NOT NULL). */
    private final String transDateMonth;
    
    /** The day of the transaction date (TRANS_DATE_DAY, TEXT NOT NULL). */
    private final String transDateDay;
    
    /** The time of the transaction (TRANS_DATE_TIME, TEXT NOT NULL). */
    private final String transDateTime;
    
    /** The total amount of the transaction, rounded to two decimal places (TRANS_TOTAL_AMOUNT, REAL NOT NULL, CHECK >= 0). */
    private final double totalAmount;
    
    /** The payment amount provided (TRANS_PAYMENT_AMOUNT, REAL NOT NULL, CHECK >= TRANS_TOTAL_AMOUNT). */
    private final double paymentAmount;
    
    /** The change returned, rounded to two decimal places (TRANS_CHANGE_AMOUNT, REAL NOT NULL, CHECK >= 0). */
    private final double changeAmount;
    
    /** The list of items in the transaction (linked to TransactionItems table). */
    private final List<TransactionItemData> transactionItems;

    /**
     * Constructs a TransactionData object with the specified values, initializing all attributes.
     * Rounds totalAmount and changeAmount to two decimal places using HALF_UP rounding.
     *
     * @param transactionId    The unique ID of the transaction (auto-incremented by the database).
     * @param transDateYear    The year of the transaction date. Should not be null to match database constraints.
     * @param transDateMonth   The month of the transaction date. Should not be null to match database constraints.
     * @param transDateDay     The day of the transaction date. Should not be null to match database constraints.
     * @param transDateTime    The time of the transaction. Should not be null to match database constraints.
     * @param totalAmount      The total amount of the transaction. Should be non-negative to match database constraints.
     * @param paymentAmount    The payment amount provided. Should be at least totalAmount to match database constraints.
     * @param changeAmount     The change returned. Should be non-negative to match database constraints.
     * @param transactionItems The list of transaction items. May be null or empty.
     */
    public TransactionData(int transactionId, String transDateYear, String transDateMonth, String transDateDay,
                           String transDateTime, double totalAmount, double paymentAmount, double changeAmount,
                           List<TransactionItemData> transactionItems) {
        this.transactionId = transactionId;
        this.transDateYear = transDateYear;
        this.transDateMonth = transDateMonth;
        this.transDateDay = transDateDay;
        this.transDateTime = transDateTime;
        // Round totalAmount and changeAmount to two decimal places
        this.totalAmount = new BigDecimal(totalAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.paymentAmount = paymentAmount;
        this.changeAmount = new BigDecimal(changeAmount).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.transactionItems = transactionItems;
    }

    /**
     * Gets the unique ID of the transaction.
     *
     * @return The transaction ID (TRANS_ID).
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Gets the year of the transaction date.
     *
     * @return The transaction date year (TRANS_DATE_YEAR).
     */
    public String getTransDateYear() {
        return transDateYear;
    }

    /**
     * Gets the month of the transaction date.
     *
     * @return The transaction date month (TRANS_DATE_MONTH).
     */
    public String getTransDateMonth() {
        return transDateMonth;
    }

    /**
     * Gets the day of the transaction date.
     *
     * @return The transaction date day (TRANS_DATE_DAY).
     */
    public String getTransDateDay() {
        return transDateDay;
    }

    /**
     * Gets the time of the transaction.
     *
     * @return The transaction date time (TRANS_DATE_TIME).
     */
    public String getTransDateTime() {
        return transDateTime;
    }

    /**
     * Gets the formatted transaction date as a string in the format "YYYY-MM-DD HH:MM:SS".
     *
     * @return The formatted date and time string.
     */
    public String getFormattedDate() {
        return String.format("%s-%s-%s %s", transDateYear, transDateMonth, transDateDay, transDateTime);
    }

    /**
     * Gets the total amount of the transaction, rounded to two decimal places.
     *
     * @return The total amount (TRANS_TOTAL_AMOUNT).
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Gets the payment amount provided for the transaction.
     *
     * @return The payment amount (TRANS_PAYMENT_AMOUNT).
     */
    public double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Gets the change returned for the transaction, rounded to two decimal places.
     *
     * @return The change amount (TRANS_CHANGE_AMOUNT).
     */
    public double getChangeAmount() {
        return changeAmount;
    }

    /**
     * Gets the list of items in the transaction.
     *
     * @return The list of TransactionItemData objects. May be null or empty.
     */
    public List<TransactionItemData> getTransactionItems() {
        return transactionItems;
    }
}