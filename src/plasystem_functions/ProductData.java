package plasystem_functions;

/**
 * Represents information about a game item.
 */
public class ProductData {
    // Attributes representing game information
    protected int productQuantity;
    protected double productPrice;
    protected String productID;
    protected String productName;
    protected String productSize;
    protected String productBrand;
    protected String productType;
    
    /**
     * Constructor to initialize GameData with provided values.
     *
     * @param prodID          The unique ID of the product.
     * @param prodQuantity    The Quantity of the product available in stock.
     * @param prodPrice       The Price of the product.
     * @param prodName        The Name of the product.
     * @param prodSize        The Size of the product.
     * @param prodBrand       The Brand the product.
     * @param prodType        The Type of the product.
     */
    public ProductData(String prodID, int prodQuantity, double prodPrice, String prodName, String prodSize, String prodBrand, String prodType){
        this.productID = prodID;
        this.productQuantity = prodQuantity;
        this.productPrice = prodPrice;
        this.productName = prodName;
        this.productSize = prodSize;
        this.productBrand = prodBrand;
        this.productType = prodType;  
    }
    
    /**
     * Default constructor for GameData.
     */
    public ProductData(){
    }
    
    // Getter and Setter methods for each attribute
    
    // Setters and getters for Product ID
    public void setProductID(String productID){
        this.productID = productID;
    }
    public String getProductID(){
        return productID;
    }
    
    // Setters and getters for Quantity
    public void setProductQuantity(int productQuantity){
        this.productQuantity=productQuantity;
    }
    public int getProductQuantity(){
        return productQuantity;
    }
    
    // Setters and getters for Price
    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }
    public double getProductPrice(){
        return productPrice;
    }
    
    // Setters and getters for Name
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return productName;
    }
    
    // Setters and getters for Size
    public void setProductSize(String productSize){
        this.productSize = productSize;
    }
    public String getProductSize(){
        return productSize;
    }
    
    // Setters and getters for Brand
    public void setProductBrand(String productBrand){
        this.productBrand = productBrand;
    }
    public String getProductBrand(){
        return productBrand;
    }
    
    // Setters and getters for Type
    public void setProductType(String productType){
        this.productType = productType;
    }
    public String getProductType(){
        return productType;
    }    
}
