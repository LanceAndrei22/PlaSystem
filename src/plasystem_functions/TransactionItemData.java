package plasystem_functions;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an item in a transaction in the PlaSystem.
 */
public class TransactionItemData {
    private final int ti_itemId;
    private final int ti_transId;
    private final int ti_productId;
    private final String ti_productName;
    private final String ti_productBrand;
    private final String ti_productSize;
    private final String ti_productType;
    private final int ti_buyQuantity;
    private final double ti_unitPrice;
    private final double ti_totalPrice;

    /**
     * Constructor for TransactionItemData with all fields.
     *
     * @param ti_itemId      The unique ID of the transaction item.
     * @param ti_transId     The ID of the associated transaction.
     * @param ti_productId   The ID of the product.
     * @param ti_productName The name of the product.
     * @param ti_productBrand The brand of the product.
     * @param ti_productSize The size of the product.
     * @param ti_productType The type of the product.
     * @param ti_buyQuantity The quantity purchased.
     * @param ti_unitPrice   The unit price of the product.
     * @param ti_totalPrice  The total price of the item, rounded to two decimal places.
     */
    public TransactionItemData(int ti_itemId, int ti_transId, int ti_productId, String ti_productName,
                               String ti_productBrand, String ti_productSize, String ti_productType,
                               int ti_buyQuantity, double ti_unitPrice, double ti_totalPrice) {
        this.ti_itemId = ti_itemId;
        this.ti_transId = ti_transId;
        this.ti_productId = ti_productId;
        this.ti_productName = ti_productName;
        this.ti_productBrand = ti_productBrand;
        this.ti_productSize = ti_productSize;
        this.ti_productType = ti_productType;
        this.ti_buyQuantity = ti_buyQuantity;
        this.ti_unitPrice = ti_unitPrice;
        // Round ti_totalPrice to two decimal places
        this.ti_totalPrice = new BigDecimal(ti_totalPrice).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public int getTI_itemId() {
        return ti_itemId;
    }

    public int getTI_transId() {
        return ti_transId;
    }

    public int getTI_productId() {
        return ti_productId;
    }

    public String getTI_productName() {
        return ti_productName;
    }

    public String getTI_productBrand() {
        return ti_productBrand;
    }

    public String getTI_productSize() {
        return ti_productSize;
    }

    public String getTI_productType() {
        return ti_productType;
    }

    public int getTI_buyQuantity() {
        return ti_buyQuantity;
    }

    public double getTI_unitPrice() {
        return ti_unitPrice;
    }

    public double getTI_totalPrice() {
        return ti_totalPrice;
    }
}