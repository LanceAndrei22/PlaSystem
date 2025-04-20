package plasystem_functions;

import java.util.*;

/**
 * A class for handling transactions.
 */
public class TransactionDataManager {
    // Fields to store transaction information
    private String productName;
    private int quantity;
    private double price;
    private String productID;
    
    /**
     * Empty constructor for TransactionHandling.
     */
    public TransactionDataManager() {
        // Constructor left empty intentionally
    }
    
    /**
     * Constructor to initialize TransactionHandling with specific values.
     *
     * @param productID The unique identifier for the product.
     * @param gameName  The name of the game/product.
     * @param quantity  The quantity of the product involved in the transaction.
     * @param price     The price of the product.
     */
    public TransactionDataManager(String productID, String gameName, int quantity, double price){
        this.productName = gameName;
        this.quantity = quantity;
        this.price = price;
        this.productID = productID;
    }
    
    /**
     * Calculates the total value of a list of transactions.
     *
     * @param list The list of TransactionDataManager objects.
     * @return The total value of the transactions.
     */
    public double getTotal(LinkedList<TransactionDataManager> list){
        double sum = 0;
        double temp = 0.0;
        
        // Iterate through the list of transactions and calculate total price
        for(TransactionDataManager element : list){
            temp = (element.getPrice() * element.getQuantity());
            sum += temp;
        }
        
        return sum;
    }
    
    /**
     * Sets the product ID.
     *
     * @param productID The unique identifier for the product.
     */
    public void setProductID(String productID){
        this.productID = productID;
    }
    
    /**
     * Gets the product ID.
     *
     * @return The unique identifier for the product.
     */
    public String getProductID(){
        return productID;
    }
    
    /**
     * Sets the quantity of the product involved in the transaction.
     *
     * @param quantity The quantity of the product.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    /**
     * Gets the quantity of the product involved in the transaction.
     *
     * @return The quantity of the product.
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * Sets the name of the game/product involved in the transaction.
     *
     * @param gameName The name of the game/product.
     */
    public void setName(String gameName){
        this.productName = gameName;
    }
    
    /**
     * Gets the name of the game/product involved in the transaction.
     *
     * @return The name of the game/product.
     */
    public String getName(){
        return productName;
    }
    
    /**
     * Sets the price of the product involved in the transaction.
     *
     * @param price The price of the product.
     */
    public void setPrice(Double price){
        this.price = price;
    }
    
    /**
     * Gets the price of the product involved in the transaction.
     *
     * @return The price of the product.
     */
    public Double getPrice(){
        return price;
    }
}