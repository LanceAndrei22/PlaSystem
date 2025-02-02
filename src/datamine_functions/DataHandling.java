package datamine_functions;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * DataHandling class manages game data, handles file operations,
 * and provides methods to read, edit, and save game data.
 */
public class DataHandling extends GameData {
    private LinkedList<GameData> gameList; // Stores the list of game data
    private String path = ""; // Stores the file path

    /**
     * Constructor for DataHandling class.
     * Initializes gameList and reads data from a file or creates a new file if it doesn't exist.
     *
     * @param filePath The file path to read or create for storing game data.
     */
    public DataHandling(String filePath) {
        // Call the parent class constructor
        super("", 0, 0, "", "", "", 0, "");

        // Initialize variables and load data from file if it exists
        this.gameList = new LinkedList<>();
        File file = new File(filePath);
        
        if (file.exists()) {
            // File exists, read data from the file
            readFromFile(filePath);
            path = filePath; // Set the file path
        } else {
            // File not found, create a new file
            try {
                boolean fileCreated = file.createNewFile();
                if (fileCreated) {
                    // File created successfully
                    JOptionPane.showMessageDialog(null, "File not found. Created new file: " + filePath, "File Created", JOptionPane.INFORMATION_MESSAGE);
                    path = filePath; // Set the file path
                } else {
                    // Failed to create file
                    JOptionPane.showMessageDialog(null, "Failed to create file: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            } catch (IOException e) {
                // Error creating file
                JOptionPane.showMessageDialog(null, "Error creating file: " + filePath + "\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }
     
    /**
     * Reads game data from a file and populates the gameList LinkedList.
     * If the data is complete, it creates GameData objects and adds them to the list.
     * Handles file not found and invalid data format scenarios.
     *
     * @param path The file path to read game data from.
     */
    private void readFromFile(String path) {
        try {
            // Create a File object using the given file path
            File file = new File(path);
            // Create a Scanner to read from the file
            Scanner scan = new Scanner(file);
            
            // Loop through each line in the file
            while (scan.hasNextLine()) {
                // Read a line of game data from the file
                String gameData = scan.nextLine();
                // Split the gameData string into an array using the " | " delimiter
                String[] fileData = gameData.split(" \\| ");
                
                // Check if the data has all required fields
                // If complete data, create a GameData object and add it to the linked list
                if (fileData.length == 8) {
                    // Extract individual data elements from the fileData array
                    String productID = fileData[0];
                    int quantity = Integer.parseInt(fileData[1]);
                    double price = Double.parseDouble(fileData[2]);
                    String name = fileData[3];
                    String genre = fileData[4];
                    String platform = fileData[5];
                    int year = Integer.parseInt(fileData[6]);
                    String publisher = fileData[7];
                    
                    // Create a GameData object with extracted data
                    GameData data = new GameData(productID, quantity, price, name, genre, platform, year, publisher);
                    // Add the data to the gameList (LinkedList)
                    gameList.add(data);
                } else {
                    // Display an error message for invalid data format
                    JOptionPane.showMessageDialog(null, "Invalid Data Format", "Error", JOptionPane.ERROR_MESSAGE);
                    // Exit the application due to error in data format
                    System.exit(0);
                }
            }
            // Close the scanner
            scan.close();
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves the linked list containing game data.
     *
     * @return The LinkedList containing GameData objects.
     */
    public LinkedList<GameData> getList() {
        return gameList;
    }

    /**
     * Saves changes made to the inventory by writing the updated game data to the specified file.
     *
     * @param gameList The LinkedList containing GameData to be saved.
     * @param path     The file path where the data will be saved.
     * @return True if the inventory changes are successfully saved, false otherwise.
     */
    public static boolean saveInventoryChanges(LinkedList<GameData> gameList, String path ){
        try (FileWriter fileWriter = new FileWriter(path)) {
            // Loop through each GameData object in the list
            for (GameData element : gameList) {
                // Constructing the line to write in the file
                String line = element.getProductID() + " | " + element.getQuantity() + " | " + element.getPrice() + " | "
                        + element.getName() + " | " + element.getGenre() + " | " + element.getPlatform() + " | "
                        + element.getReleaseYear() + " | " + element.getPublisher() + "\n";
                fileWriter.write(line); // Writing the constructed line to the file
            }
        } catch (IOException e) {
            // If an exception occurs while writing to the file, print the stack trace
            e.printStackTrace();
            return false; // Return false to indicate the failure in saving changes
        }
        return true; // Return true to indicate successful saving of changes
    }   
        
    /**
     * Edits the selected row of the JTable with new values and updates the linked list accordingly.
     *
     * @param list          The LinkedList containing GameData.
     * @param table         The JTable displaying the data.
     * @param selectedRow   The index of the selected row to be edited.
     * @param newQuantity   The new quantity value for the selected row.
     * @param newPrice      The new price value for the selected row.
     * @param newName       The new name value for the selected row.
     * @param newGenre      The new genre value for the selected row.
     * @param newPlatform   The new platform value for the selected row.
     * @param newYear       The new release year value for the selected row.
     * @param newPublisher  The new publisher value for the selected row.
     */
    public void editSelectedData(LinkedList<GameData> list, JTable table, int selectedRow, int newQuantity, double newPrice, String newName, String newGenre, String newPlatform, int newYear, String newPublisher) {
        // Check if the selected row is valid
        if (selectedRow >= 0 && selectedRow < list.size()) {
            DefaultTableModel model = (DefaultTableModel) table.getModel(); // Get the table's model

            // Update the JTable with the new values
            model.setValueAt(newQuantity, selectedRow, 1);   // Update quantity
            model.setValueAt(newPrice, selectedRow, 2);      // Update price
            model.setValueAt(newName, selectedRow, 3);       // Update name
            model.setValueAt(newGenre, selectedRow, 4);      // Update genre
            model.setValueAt(newPlatform, selectedRow, 5);   // Update platform
            model.setValueAt(newYear, selectedRow, 6);       // Update year
            model.setValueAt(newPublisher, selectedRow, 7);  // Update publisher

            // Update the data in the linked list
            GameData editedGameData = list.get(selectedRow);    // Get the selected GameData object
            editedGameData.setQuantity(newQuantity);          // Update quantity
            editedGameData.setPrice(newPrice);                  // Update price
            editedGameData.setName(newName);                     // Update name
            editedGameData.setGenre(newGenre);                  // Update genre
            editedGameData.setPlatform(newPlatform);          // Update platform
            editedGameData.setReleaseYear(newYear);              // Update year
            editedGameData.setPublisher(newPublisher);        // Update publisher

            // Save the changes to the file and display appropriate messages
            if (!saveInventoryChanges(list, path)) {
                JOptionPane.showMessageDialog(null, "File Error! Data not saved!");
            } else {
                JOptionPane.showMessageDialog(null, "Updated File Successfully !");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a valid row"); // Show error if an invalid row is selected
        }
    }
   
    /**
     * Adds new data to the LinkedList and updates the table and file accordingly.
     *
     * @param list       The LinkedList containing GameData.
     * @param table      The JTable displaying the data.
     * @param productID  The product ID for the new data.
     * @param quantity   The quantity for the new data.
     * @param price      The price for the new data.
     * @param name       The name for the new data.
     * @param genre      The genre for the new data.
     * @param platform   The platform for the new data.
     * @param year       The release year for the new data.
     * @param publisher  The publisher for the new data.
     */
    public void addNewData(LinkedList<GameData> list, JTable table, String productID, int quantity, double price, String name, String genre, String platform, int year, String publisher ) {
        // Create a new GameData object
        GameData data = new GameData(
                productID,
                quantity,
                price,
                name,
                genre,
                platform,
                year,
                publisher);

        // Add the new data to the LinkedList
        list.add(data);

        // Get the table model and create a new row data array
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] newRowData = {
                productID,
                quantity,
                price,
                name,
                genre,
                platform,
                year,
                publisher,
        };

        // Check if the changes are saved to the file
        if (!saveInventoryChanges(list, path)) {
            JOptionPane.showMessageDialog(null, "File Error! Data not saved!");
        } else {
            // If saved successfully, update the table and display a success message
            model.addRow(newRowData); // Add a new row to the table
            saveInventoryChanges(list, path); // Save changes to the file
            JOptionPane.showMessageDialog(null, "Added Data Successfully !");
        }
    }
    
    /**
     * Deletes selected data from the table and updates the file accordingly.
     *
     * @param TableData     The JTable containing the data.
     * @param list          The LinkedList containing GameData.
     * @param selectedRow   The index of the selected row to delete.
     */
    public void deleteData(JTable TableData, LinkedList<GameData> list, int selectedRow) {
        // Retrieves the DefaultTableModel instance associated with the JTable 'TableData'.
        DefaultTableModel model = (DefaultTableModel) TableData.getModel();
        
        // Converts the view-based row index 'selectedRow' to the model-based row index 'modelRow'.
        // This conversion is useful for handling sorting or filtering in the JTable.
        int modelRow = TableData.convertRowIndexToModel(selectedRow);

        // Check if a row is selected
        if (TableData.getSelectedRow() != -1) {
            // Ask for confirmation before deleting
            int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Item?", "Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == 0) {
                model.removeRow(modelRow); // Remove the row from the table
                list.remove(modelRow); // Remove the corresponding data from the LinkedList

                // Check if changes are saved to the file
                if (saveInventoryChanges(list, path) == false) {
                    JOptionPane.showMessageDialog(null, "File Error! Data not saved!");
                } else {
                    // If saved successfully, update the file and display a success message
                    saveInventoryChanges(list, path); // Save changes to the file
                    JOptionPane.showMessageDialog(null, "Selected Item Deleted Successfully");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select an item to delete!"); // If no row is selected, show an error message
        }
    }

    /**
     * Decreases the quantity of items in the inventory list based on the purchase list.
     * Updates both the table and file data accordingly.
     *
     * @param list          The LinkedList containing GameData.
     * @param purchaseList  The LinkedList containing TransactionHandling for purchased items.
     * @param table         The JTable representing the inventory.
     */
    public void decreaseQuantity(LinkedList<GameData> list, LinkedList<TransactionHandling> purchaseList, JTable table) {
        // Loop through each transaction in the purchase list
        for(TransactionHandling element: purchaseList) {
            int index = -1; // Initialize index to -1 (indicating not found initially)

            // Search for the matching product ID in the game list
            for (int i = 0; i < list.size(); i++) {
                GameData inventory = list.get(i);
                if (inventory.getProductID().equals(element.getProductID())) {
                    index = i; // Store the index when the condition is met
                    break;
                }
            }

            // If the product ID is found in the game list
            if (index != -1) {
                GameData selectedData = list.get(index);
                int newQuantity = selectedData.getQuantity() - element.getQuantity();

                // Update the table view with the new quantity
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(newQuantity, index, 1);

                // Update the quantity in the list
                GameData editedGameData = list.get(index);
                editedGameData.setQuantity(newQuantity);
            }
        }

        // Check if file is there and save changes to the file
        if(saveInventoryChanges(list, path) == false) {
            JOptionPane.showMessageDialog(null, "File Error! Data not saved!");
        } else {
            // Save data to file
            saveInventoryChanges(list, path);
            // Done
        }
    }
}