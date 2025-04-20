package plasystem_functions;

import java.util.List;

/**
 * Represents a complete transaction in the system.
 * Each transaction contains general information and a list of transaction items.
 */
public class TransactionData {
    private int transactionId;
    private String transactionDate;
    private double totalAmount;
    private double paymentAmount;
    private double changeAmount;
    private List<TransactionItemData> transactionItems;
    
    /**
     * Default constructor.
     */
    public TransactionData() {
        
    }

    public TransactionData(int transactionId, String transactionDate, double totalAmount,
                           double paymentAmount, double changeAmount, List<TransactionItemData> transactionItems) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.totalAmount = totalAmount;
        this.paymentAmount = paymentAmount;
        this.changeAmount = changeAmount;
        this.transactionItems = transactionItems;
    }

    // Getters and setters
    public int getTransactionId() {
        return transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public List<TransactionItemData> getTransactionItems() {
        return transactionItems;
    }

    public void setTransactionItems(List<TransactionItemData> transactionItems) {
        this.transactionItems = transactionItems;
    }
}