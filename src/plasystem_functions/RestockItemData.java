package plasystem_functions;

/**
 * Represents an item restocked during a restocking event in the PlaSystem database, mapping to the
 * RestockItems table. Captures the product's state (ID, name, brand, size, type, price) and the
 * quantity added at the time of restocking. Provides getters and setters for accessing and modifying
 * the item's attributes.
 */
public class RestockItemData {
    /** The unique ID of the restock item (RI_ITEM_ID, INTEGER PRIMARY KEY AUTOINCREMENT). */
    private int RI_itemId;
    
    /** The ID of the associated restock event (RI_RESTOCK_ID, INTEGER NOT NULL, FOREIGN KEY). */
    private int RI_restockId;
    
    /** The ID of the associated product (RI_PROD_ID, INTEGER, FOREIGN KEY, nullable). */
    private int RI_productId;
    
    /** The name of the product at the time of restocking (RI_PROD_NAME, TEXT NOT NULL). */
    private String RI_productName;
    
    /** The brand of the product at the time of restocking (RI_PROD_BRAND, TEXT NOT NULL). */
    private String RI_productBrand;
    
    /** The size of the product at the time of restocking (RI_PROD_SIZE, TEXT NOT NULL). */
    private String RI_productSize;
    
    /** The type of the product at the time of restocking (RI_PROD_TYPE, TEXT NOT NULL). */
    private String RI_productType;
    
    /** The price of the product at the time of restocking (RI_PROD_PRICE, REAL NOT NULL, CHECK >= 0). */
    private double RI_productPrice;
    
    /** The quantity restocked for the product (RI_RESTOCKED_QUANTITY, INTEGER NOT NULL, CHECK > 0). */
    private int RI_restockedQuantity;

    /**
     * Default constructor. Initializes a RestockItemData object with default values
     * (0 for numeric fields, null for String fields).
     */
    public RestockItemData() {
    }

    /**
     * Constructs a RestockItemData object with the specified values, initializing all attributes.
     *
     * @param RI_itemId            The unique ID of the restock item (auto-incremented by the database).
     * @param RI_restockId         The ID of the associated restock event. Must not be null and must
     *                             reference a valid RESTOCK_ID to match database constraints.
     * @param RI_productId         The ID of the associated product. May be 0 if null in the database.
     * @param RI_productName       The product name. Should not be null to match database constraints.
     * @param RI_productBrand      The product brand. Should not be null to match database constraints.
     * @param RI_productSize       The product size. Should not be null to match database constraints.
     * @param RI_productType       The product type. Should not be null to match database constraints.
     * @param RI_productPrice      The product price. Should be non-negative to match database constraints.
     * @param RI_restockedQuantity The restocked quantity. Should be positive to match database constraints.
     */
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

    /**
     * Gets the unique ID of the restock item.
     *
     * @return The restock item ID (RI_ITEM_ID).
     */
    public int getRI_itemId() {
        return RI_itemId;
    }

    /**
     * Gets the ID of the associated restock event.
     *
     * @return The restock event ID (RI_RESTOCK_ID).
     */
    public int getRI_restockId() {
        return RI_restockId;
    }

    /**
     * Sets the ID of the associated restock event.
     *
     * @param RI_restockId The new restock event ID. Should reference a valid RESTOCK_ID to match
     *                     database constraints.
     */
    public void setRI_restockId(int RI_restockId) {
        this.RI_restockId = RI_restockId;
    }

    /**
     * Gets the ID of the associated product.
     *
     * @return The product ID (RI_PROD_ID). May be 0 if null in the database.
     */
    public int getRI_productId() {
        return RI_productId;
    }

    /**
     * Sets the ID of the associated product.
     *
     * @param RI_productId The new product ID. May be 0 to represent null in the database.
     */
    public void setRI_productId(int RI_productId) {
        this.RI_productId = RI_productId;
    }

    /**
     * Gets the name of the product at the time of restocking.
     *
     * @return The product name (RI_PROD_NAME).
     */
    public String getRI_productName() {
        return RI_productName;
    }

    /**
     * Sets the name of the product at the time of restocking.
     *
     * @param RI_productName The new product name. Should not be null to match database constraints.
     */
    public void setRI_productName(String RI_productName) {
        this.RI_productName = RI_productName;
    }

    /**
     * Gets the brand of the product at the time of restocking.
     *
     * @return The product brand (RI_PROD_BRAND).
     */
    public String getRI_productBrand() {
        return RI_productBrand;
    }

    /**
     * Sets the brand of the product at the time of restocking.
     *
     * @param RI_productBrand The new product brand. Should not be null to match database constraints.
     */
    public void setRI_productBrand(String RI_productBrand) {
        this.RI_productBrand = RI_productBrand;
    }

    /**
     * Gets the size of the product at the time of restocking.
     *
     * @return The product size (RI_PROD_SIZE).
     */
    public String getRI_productSize() {
        return RI_productSize;
    }

    /**
     * Sets the size of the product at the time of restocking.
     *
     * @param RI_productSize The new product size. Should not be null to match database constraints.
     */
    public void setRI_productSize(String RI_productSize) {
        this.RI_productSize = RI_productSize;
    }

    /**
     * Gets the type of the product at the time of restocking.
     *
     * @return The product type (RI_PROD_TYPE).
     */
    public String getRI_productType() {
        return RI_productType;
    }

    /**
     * Sets the type of the product at the time of restocking.
     *
     * @param RI_productType The new product type. Should not be null to match database constraints.
     */
    public void setRI_productType(String RI_productType) {
        this.RI_productType = RI_productType;
    }

    /**
     * Gets the price of the product at the time of restocking.
     *
     * @return The product price (RI_PROD_PRICE).
     */
    public double getRI_productPrice() {
        return RI_productPrice;
    }

    /**
     * Sets the price of the product at the time of restocking.
     *
     * @param RI_productPrice The new product price. Should be non-negative to match database constraints.
     */
    public void setRI_productPrice(double RI_productPrice) {
        this.RI_productPrice = RI_productPrice;
    }

    /**
     * Gets the quantity restocked for the product.
     *
     * @return The restocked quantity (RI_RESTOCKED_QUANTITY).
     */
    public int getRI_restockedQuantity() {
        return RI_restockedQuantity;
    }

    /**
     * Sets the quantity restocked for the product.
     *
     * @param RI_restockedQuantity The new restocked quantity. Should be positive to match database constraints.
     */
    public void setRI_restockedQuantity(int RI_restockedQuantity) {
        this.RI_restockedQuantity = RI_restockedQuantity;
    }
}