package plasystem_functions;

/**
 * Represents a product entity in the PlaSystem database, mapping to the Product table.
 * Stores attributes such as product ID, name, brand, size, type, price, quantity, and restock value,
 * with getters and setters for accessing and modifying these attributes.
 */
public class ProductData {
    // Attributes matching the Product table schema
    /** The unique ID of the product, auto-incremented by the database (PROD_ID). */
    private int productId; // PROD_ID (INTEGER PRIMARY KEY AUTOINCREMENT)
    
    /** The name of the product (PROD_NAME, TEXT NOT NULL). */
    private String productName; // PROD_NAME (TEXT NOT NULL)
    
    /** The brand of the product (PROD_BRAND, TEXT NOT NULL). */
    private String productBrand; // PROD_BRAND (TEXT NOT NULL)
    
    /** The size of the product (PROD_SIZE, TEXT NOT NULL). */
    private String productSize; // PROD_SIZE (TEXT NOT NULL)
    
    /** The type of the product (PROD_TYPE, TEXT NOT NULL). */
    private String productType; // PROD_TYPE (TEXT NOT NULL)
    
    /** The price of the product (PROD_PRICE, REAL NOT NULL, CHECK >= 0). */
    private double productPrice; // PROD_PRICE (REAL NOT NULL, CHECK >= 0)
    
    /** The quantity of the product in stock (PROD_QUANTITY, INTEGER NOT NULL, CHECK >= 0). */
    private int productQuantity; // PROD_QUANTITY (INTEGER NOT NULL, CHECK >= 0)
    
    /** The restock threshold value for the product (PROD_RESTOCK_VALUE, INTEGER NOT NULL, CHECK >= 0). */
    private int productRestockValue; // PROD_RESTOCK_VALUE (INTEGER NOT NULL, CHECK >= 0)

    /**
     * Default constructor. Initializes a ProductData object with default values
     * (0 for numeric fields, null for String fields).
     */
    public ProductData() {
    }

    /**
     * Constructs a ProductData object with the specified values, initializing all attributes.
     *
     * @param productId          The unique ID of the product (auto-incremented by the database).
     * @param productName        The name of the product. Should not be null to match database constraints.
     * @param productBrand       The brand of the product. Should not be null to match database constraints.
     * @param productSize        The size of the product. Should not be null to match database constraints.
     * @param productType        The type of the product. Should not be null to match database constraints.
     * @param productPrice       The price of the product. Should be non-negative to match database constraints.
     * @param productQuantity    The quantity of the product in stock. Should be non-negative to match database constraints.
     * @param productRestockValue The restock threshold value. Should be non-negative to match database constraints.
     */
    public ProductData(int productId, String productName, String productBrand, String productSize,
                       String productType, double productPrice, int productQuantity, int productRestockValue) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productSize = productSize;
        this.productType = productType;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productRestockValue = productRestockValue;
    }

    /**
     * Gets the unique ID of the product.
     *
     * @return The product ID (PROD_ID).
     */
    public int getProductId() {
        return productId;
    }

    // No setter for productId as it is managed by the database

    /**
     * Gets the name of the product.
     *
     * @return The product name (PROD_NAME).
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the name of the product.
     *
     * @param productName The new product name. Should not be null to match database constraints.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Gets the brand of the product.
     *
     * @return The product brand (PROD_BRAND).
     */
    public String getProductBrand() {
        return productBrand;
    }

    /**
     * Sets the brand of the product.
     *
     * @param productBrand The new product brand. Should not be null to match database constraints.
     */
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    /**
     * Gets the size of the product.
     *
     * @return The product size (PROD_SIZE).
     */
    public String getProductSize() {
        return productSize;
    }

    /**
     * Sets the size of the product.
     *
     * @param productSize The new product size. Should not be null to match database constraints.
     */
    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    /**
     * Gets the type of the product.
     *
     * @return The product type (PROD_TYPE).
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the type of the product.
     *
     * @param productType The new product type. Should not be null to match database constraints.
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Gets the price of the product.
     *
     * @return The product price (PROD_PRICE).
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * Sets the price of the product.
     *
     * @param productPrice The new product price. Should be non-negative to match database constraints.
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * Gets the quantity of the product in stock.
     *
     * @return The product quantity (PROD_QUANTITY).
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * Sets the quantity of the product in stock.
     *
     * @param productQuantity The new product quantity. Should be non-negative to match database constraints.
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Gets the restock threshold value for the product.
     *
     * @return The product restock value (PROD_RESTOCK_VALUE).
     */
    public int getProductRestockValue() {
        return productRestockValue;
    }

    /**
     * Sets the restock threshold value for the product.
     *
     * @param productRestockValue The new restock threshold value. Should be non-negative to match database constraints.
     */
    public void setProductRestockValue(int productRestockValue) {
        this.productRestockValue = productRestockValue;
    }
}