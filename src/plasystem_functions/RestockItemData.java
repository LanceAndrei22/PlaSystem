package plasystem_functions;

/**
 * Represents an item restocked during a restocking event.
 * Captures the product's state and the quantity added at that time.
 */
public class RestockItemData {
    private int RI_itemId;
    private int RI_restockId;
    private int RI_productId;
    private String RI_productName;
    private String RI_productBrand;
    private String RI_productSize;
    private String RI_productType;
    private double RI_productPrice;
    private int RI_restockedQuantity;
    
    
    /**
     * Default constructor.
     */
    public RestockItemData() {
    
    }

    public RestockItemData(int RI_itemId, int RI_restockId, int RI_productId, String RI_productName,
                           String RI_productBrand, String RI_productSize, String RI_productType,
                           double RI_productPrice, int RI_restockedQuantity) {
        this.RI_itemId = RI_itemId;
        this.RI_restockId = RI_restockId;
        this.RI_productId = RI_productId;
        this.RI_productName = RI_productName;
        this.RI_productBrand = RI_productBrand;
        this.RI_productSize = RI_productSize;
        this.RI_productType = RI_productType;
        this.RI_productPrice = RI_productPrice;
        this.RI_restockedQuantity = RI_restockedQuantity;
    }

    // Getters and setters
    public int getRI_itemId() {
        return RI_itemId;
    }

    public int getRI_restockId() {
        return RI_restockId;
    }

    public void setRI_restockId(int RI_restockId) {
        this.RI_restockId = RI_restockId;
    }

    public int getRI_productId() {
        return RI_productId;
    }

    public void setRI_productId(int RI_productId) {
        this.RI_productId = RI_productId;
    }

    public String getRI_productName() {
        return RI_productName;
    }

    public void setRI_productName(String RI_productName) {
        this.RI_productName = RI_productName;
    }

    public String getRI_productBrand() {
        return RI_productBrand;
    }

    public void setRI_productBrand(String RI_productBrand) {
        this.RI_productBrand = RI_productBrand;
    }

    public String getRI_productSize() {
        return RI_productSize;
    }

    public void setRI_productSize(String RI_productSize) {
        this.RI_productSize = RI_productSize;
    }

    public String getRI_productType() {
        return RI_productType;
    }

    public void setRI_productType(String RI_productType) {
        this.RI_productType = RI_productType;
    }

    public double getRI_productPrice() {
        return RI_productPrice;
    }

    public void setRI_productPrice(double RI_productPrice) {
        this.RI_productPrice = RI_productPrice;
    }

    public int getRI_restockedQuantity() {
        return RI_restockedQuantity;
    }

    public void setRI_restockedQuantity(int RI_restockedQuantity) {
        this.RI_restockedQuantity = RI_restockedQuantity;
    }
}