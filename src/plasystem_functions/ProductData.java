package plasystem_functions;

/**
 * Represents a product in the PlaSystem database.
 */
public class ProductData {
    // Attributes matching the Product table schema
    private int productId; // PROD_ID (INTEGER PRIMARY KEY AUTOINCREMENT)
    private String productName; // PROD_NAME (TEXT NOT NULL)
    private String productBrand; // PROD_BRAND (TEXT NOT NULL)
    private String productSize; // PROD_SIZE (TEXT NOT NULL)
    private String productType; // PROD_TYPE (TEXT NOT NULL)
    private double productPrice; // PROD_PRICE (REAL NOT NULL, CHECK >= 0)
    private int productQuantity; // PROD_QUANTITY (INTEGER NOT NULL, CHECK >= 0)
    private int productRestockValue; // PROD_RESTOCK_VALUE (INTEGER NOT NULL, CHECK >= 0)

    /**
     * Default constructor.
     */
    public ProductData() {
    }

    /**
     * Constructor to initialize ProductData with provided values.
     *
     * @param productId          The unique ID of the product (auto-incremented by database).
     * @param productName        The name of the product.
     * @param productBrand       The brand of the product.
     * @param productSize        The size of the product.
     * @param productType        The type of the product.
     * @param productPrice       The price of the product.
     * @param productQuantity    The quantity of the product in stock.
     * @param productRestockValue The restock value of the product.
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

    // Getter and Setter methods

    public int getProductId() {
        return productId;
    }

    // No setter for productId as it is managed by the database

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductRestockValue() {
        return productRestockValue;
    }

    public void setProductRestockValue(int productRestockValue) {
        this.productRestockValue = productRestockValue;
    }
}