package plasystem_functions;

/**
 * Represents information about a game item.
 */
public class GameData {
    // Attributes representing game information
    protected int quantity;
    protected double price;
    protected String productID;
   
    protected String name;
    protected String genre;
    protected String publisher;
    protected String platform;
    
    /**
     * Constructor to initialize GameData with provided values.
     *
     * @param productID   The unique ID of the game.
     * @param quantity    The quantity of the game available in stock.
     * @param price       The price of the game.
     * @param gameName    The name of the game.
     * @param genre       The genre of the game.
     * @param platform    The platform(s) the game is available on.
    
     * @param publisher   The publisher of the game.
     */
    public GameData(String productID, int quantity, double price, String gameName, String genre, String platform, String publisher){
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
        this.name = gameName;
        this.genre = genre;
        this.platform = platform;
        
        this.publisher = publisher;  
    }
    
    /**
     * Default constructor for GameData.
     */
    public GameData(){
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
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    public int getQuantity(){
        return quantity;
    }
    
    // Setters and getters for Price
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    
    // Setters and getters for Name
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    // Setters and getters for Genre
    public void setGenre(String genre){
        this.genre = genre;
    }
    public String getGenre(){
        return genre;
    }
    
    // Setters and getters for Platform
    public void setPlatform(String platform){
        this.platform = platform;
    }
    public String getPlatform(){
        return platform;
    }
    
   
    
    // Setters and getters for Publisher
    public void setPublisher(String publisher){
        this.publisher = publisher;
    }
    public String getPublisher(){
        return publisher;
    }    
}