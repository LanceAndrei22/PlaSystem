package plasystem_functions;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a transaction in the PlaSystem, including its ID, date components, amounts, and associated items.
 */
public class TransactionData {
    private final int transactionId;
    private final String transDateYear;
    private final String transDateMonth;
    private final String transDateDay;
    private final String transDateTime;
    private final double totalAmount;
    private final double paymentAmount;
    private final double changeAmount;
    private final List<TransactionItemData> transactionItems;

    /**
     * Constructor for TransactionData with all fields.
     *
     * @param transactionId   The unique ID of the transaction.
     * @param transDateYear   The year of the transaction date.
     * @param transDateMonth  The month of the transaction date.
     * @param transDateDay    The day of the transaction date.
     * @param transDateTime   The time of the transaction.
     * @param totalAmount     The total amount of the transaction, rounded to two decimal places.
     * @param paymentAmount   The payment amount provided.
     * @param changeAmount    The change returned, rounded to two decimal places.
     * @param transactionItems The list of transaction items.
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

    public int getTransactionId() {
        return transactionId;
    }

    public String getTransDateYear() {
        return transDateYear;
    }

    public String getTransDateMonth() {
        return transDateMonth;
    }

    public String getTransDateDay() {
        return transDateDay;
    }

    public String getTransDateTime() {
        return transDateTime;
    }

    public String getFormattedDate() {
        return String.format("%s-%s-%s %s", transDateYear, transDateMonth, transDateDay, transDateTime);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public List<TransactionItemData> getTransactionItems() {
        return transactionItems;
    }
}