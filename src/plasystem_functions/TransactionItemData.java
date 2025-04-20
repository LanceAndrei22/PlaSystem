package plasystem_functions;

/**
 * Represents an individual item in a transaction.
 * Includes a snapshot of the product's details at the time of transaction.
 */
public class TransactionItemData {
    private int TI_itemId;
    private int TI_transactionId;
    private int TI_productId;
    private String TI_productName;
    private String TI_productBrand;
    private String TI_productSize;
    private String TI_productType;
    private int TI_buyQuantity;
    private double TI_unitPrice;
    private double TI_totalPrice;
    
    /**
     * Default constructor.
     */
    public TransactionItemData() {
    
    }

    public TransactionItemData(int TI_itemId, int TI_transactionId, int TI_productId, String TI_productName,
                               String TI_productBrand, String TI_productSize, String TI_productType,
                               int TI_buyQuantity, double TI_unitPrice, double TI_totalPrice) {
        this.TI_itemId = TI_itemId;
        this.TI_transactionId = TI_transactionId;
        this.TI_productId = TI_productId;
        this.TI_productName = TI_productName;
        this.TI_productBrand = TI_productBrand;
        this.TI_productSize = TI_productSize;
        this.TI_productType = TI_productType;
        this.TI_buyQuantity = TI_buyQuantity;
        this.TI_unitPrice = TI_unitPrice;
        this.TI_totalPrice = TI_totalPrice;
    }

    // Getters and setters
    public int getTI_itemId() {
        return TI_itemId;
    }

    public int getTI_transactionId() {
        return TI_transactionId;
    }

    public void setTI_transactionId(int TI_transactionId) {
        this.TI_transactionId = TI_transactionId;
    }

    public int getTI_productId() {
        return TI_productId;
    }

    public void setTI_productId(int TI_productId) {
        this.TI_productId = TI_productId;
    }

    public String getTI_productName() {
        return TI_productName;
    }

    public void setTI_productName(String TI_productName) {
        this.TI_productName = TI_productName;
    }

    public String getTI_productBrand() {
        return TI_productBrand;
    }

    public void setTI_productBrand(String TI_productBrand) {
        this.TI_productBrand = TI_productBrand;
    }

    public String getTI_productSize() {
        return TI_productSize;
    }

    public void setTI_productSize(String TI_productSize) {
        this.TI_productSize = TI_productSize;
    }

    public String getTI_productType() {
        return TI_productType;
    }

    public void setTI_productType(String TI_productType) {
        this.TI_productType = TI_productType;
    }

    public int getTI_buyQuantity() {
        return TI_buyQuantity;
    }

    public void setTI_buyQuantity(int TI_buyQuantity) {
        this.TI_buyQuantity = TI_buyQuantity;
    }

    public double getTI_unitPrice() {
        return TI_unitPrice;
    }

    public void setTI_unitPrice(double TI_unitPrice) {
        this.TI_unitPrice = TI_unitPrice;
    }

    public double getTI_totalPrice() {
        return TI_totalPrice;
    }

    public void setTI_totalPrice(double TI_totalPrice) {
        this.TI_totalPrice = TI_totalPrice;
    }
}