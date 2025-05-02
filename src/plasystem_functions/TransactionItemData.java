package plasystem_functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an item in a transaction in the PlaSystem database, mapping to the TransactionItems table.
 * Stores details about the purchased product (ID, name, brand, size, type), quantity, unit price,
 * and total price, with the total price rounded to two decimal places. Provides getters for accessing
 * attributes.
 */
public class TransactionItemData {
    /** The unique ID of the transaction item (TI_ITEM_ID, INTEGER PRIMARY KEY AUTOINCREMENT). */
    private final int TI_itemId;
    
    /** The ID of the associated transaction (TI_TRANS_ID, INTEGER NOT NULL, FOREIGN KEY). */
    private final int TI_transId;
    
    /** The ID of the associated product (TI_PROD_ID, INTEGER, FOREIGN KEY, nullable). */
    private final int TI_productId;
    
    /** The name of the product at the time of purchase (TI_PROD_NAME, TEXT NOT NULL). */
    private final String TI_productName;
    
    /** The brand of the product at the time of purchase (TI_PROD_BRAND, TEXT NOT NULL). */
    private final String TI_productBrand;
    
    /** The size of the product at the time of purchase (TI_PROD_SIZE, TEXT NOT NULL). */
    private final String TI_productSize;
    
    /** The type of the product at the time of purchase (TI_PROD_TYPE, TEXT NOT NULL). */
    private final String TI_productType;
    
    /** The quantity purchased (TI_PROD_BUYQUANTITY, INTEGER NOT NULL, CHECK > 0). */
    private final int TI_buyQuantity;
    
    /** The unit price of the product (TI_PROD_UNITPRICE, REAL NOT NULL, CHECK >= 0). */
    private final double TI_unitPrice;
    
    /** The total price of the item, rounded to two decimal places (TI_PROD_TOTALPRICE, REAL NOT NULL, CHECK >= TI_PROD_UNITPRICE). */
    private final double TI_totalPrice;

    /**
     * Constructs a TransactionItemData object with the specified values, initializing all attributes.Rounds the total price to two decimal places using HALF_UP rounding.
     *
     * @param TI_itemId       The unique ID of the transaction item (auto-incremented by the database).
     * @param TI_transId      The ID of the associated transaction. Must reference a valid TRANS_ID to match database constraints.
     * @param TI_productId    The ID of the associated product. May be 0 if null in the database.
     * @param TI_productName  The product name. Should not be null to match database constraints.
     * @param TI_productBrand The product brand. Should not be null to match database constraints.
     * @param TI_productSize  The product size. Should not be null to match database constraints.
     * @param TI_productType  The product type. Should not be null to match database constraints.
     * @param TI_buyQuantity  The quantity purchased. Should be positive to match database constraints.
     * @param TI_unitPrice    The unit price of the product. Should be non-negative to match database constraints.
     * @param TI_totalPrice   The total price of the item. Should be at least equal to unitPrice to match database constraints.
     */
    public TransactionItemData(int TI_itemId, int TI_transId, int TI_productId, String TI_productName,
                               String TI_productBrand, String TI_productSize, String TI_productType,
                               int TI_buyQuantity, double TI_unitPrice, double TI_totalPrice) {
        this.TI_itemId = TI_itemId;
        this.TI_transId = TI_transId;
        this.TI_productId = TI_productId;
        this.TI_productName = TI_productName;
        this.TI_productBrand = TI_productBrand;
        this.TI_productSize = TI_productSize;
        this.TI_productType = TI_productType;
        this.TI_buyQuantity = TI_buyQuantity;
        this.TI_unitPrice = TI_unitPrice;
        // Round TI_totalPrice to two decimal places
        this.TI_totalPrice = new BigDecimal(TI_totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * Gets the unique ID of the transaction item.
     *
     * @return The transaction item ID (TI_ITEM_ID).
     */
    public int getTI_itemId() {
        return TI_itemId;
    }

    /**
     * Gets the ID of the associated transaction.
     *
     * @return The transaction ID (TI_TRANS_ID).
     */
    public int getTI_transId() {
        return TI_transId;
    }

    /**
     * Gets the ID of the associated product.
     *
     * @return The product ID (TI_PROD_ID). May be 0 if null in the database.
     */
    public int getTI_productId() {
        return TI_productId;
    }

    /**
     * Gets the name of the product at the time of purchase.
     *
     * @return The product name (TI_PROD_NAME).
     */
    public String getTI_productName() {
        return TI_productName;
    }

    /**
     * Gets the brand of the product at the time of purchase.
     *
     * @return The product brand (TI_PROD_BRAND).
     */
    public String getTI_productBrand() {
        return TI_productBrand;
    }

    /**
     * Gets the size of the product at the time of purchase.
     *
     * @return The product size (TI_PROD_SIZE).
     */
    public String getTI_productSize() {
        return TI_productSize;
    }

    /**
     * Gets the type of the product at the time of purchase.
     *
     * @return The product type (TI_PROD_TYPE).
     */
    public String getTI_productType() {
        return TI_productType;
    }

    /**
     * Gets the quantity purchased.
     *
     * @return The buy quantity (TI_PROD_BUYQUANTITY).
     */
    public int getTI_buyQuantity() {
        return TI_buyQuantity;
    }

    /**
     * Gets the unit price of the product.
     *
     * @return The unit price (TI_PROD_UNITPRICE).
     */
    public double getTI_unitPrice() {
        return TI_unitPrice;
    }

    /**
     * Gets the total price of the item, rounded to two decimal places.
     *
     * @return The total price (TI_PROD_TOTALPRICE).
     */
    public double getTI_totalPrice() {
        return TI_totalPrice;
    }
}