package datamine_functions;

import java.util.Random;

/**
 * Generates random IDs for products and receipts using alphanumeric characters.
 */
public class RandomIDGenerator {
    private String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Characters to use in the ID
    private Random rnd = new Random();
       
    /**
     * Generates a random product ID consisting of 5 characters.
     *
     * @return A randomly generated product ID.
     */
    public String generateProductID() {
        // Initialize a StringBuilder to store the generated product ID
        StringBuilder productID = new StringBuilder();
        
        // Generate a product ID consisting of 5 characters
        for (int i = 0; i < 5; i++) {
            // Generate a random index within the range of available characters
            int index = rnd.nextInt(chars.length());
            // Append a character from the 'chars' string at the randomly generated index to the product ID
            productID.append(chars.charAt(index));
        }

        return productID.toString(); // Return Generated Product ID String
    }
     
    /**
     * Generates a random receipt ID with a specific format.
     *
     * @return A randomly generated receipt ID.
     */
    public String generateReceiptID() {
        // Initialize a StringBuilder to store the generated receipt ID
        StringBuilder receiptID = new StringBuilder();
            
        // Append 4 characters without a delimiter
        for (int i = 0; i < 4; i++) {
            receiptID.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        // Append a hyphen after the first 4 characters
        receiptID.append('-');

        // Append 3 characters after the hyphen
        for (int i = 0; i < 3; i++) {
            receiptID.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        // Append another hyphen after the next 3 characters
        receiptID.append('-');

        // Append 3 characters after the second hyphen
        for (int i = 0; i < 3; i++) {
            receiptID.append(chars.charAt(rnd.nextInt(chars.length())));
        }

        return receiptID.toString(); // Return Generated Receipt ID String
    }
}