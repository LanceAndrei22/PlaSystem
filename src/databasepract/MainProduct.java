package databasepract;
public class MainProduct {
    private int id, quantity;
    private String name, brand, size, type;
    private double price;

    public MainProduct(int id, String name, String brand, String size, String type, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.type = type;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBrand() { return brand; }
    public String getSize() { return size; }
    public String getType() { return type; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}
