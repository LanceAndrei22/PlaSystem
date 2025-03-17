package plasystem_functions;

import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.Properties;

/**
 * Utility class to handle file selection and retrieval of file-related information.
 */
public class DatabaseFileChooser {
    // Default file path for the inventory file
    private static String filepath = "src/plasystem_inventory/inventory.dat";
    
    // Configuration file constants
    private static final String CONFIG_FILE = "src/plasystem_inventory/config.properties";
    private static final String LAST_CHOSEN_FILE = "last_chosen_file";

    /**
     * Opens a file chooser dialog to select a text file and sets it as the current file path.
     * If a text file is selected, it updates the file path and saves it for future use.
     */
    public static void chooseFile() {
        // Create a file chooser and set file filter to .txt files only
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Data Files", "dat");
        chooser.setFileFilter(filter);

        // Load the last chosen file path and set it as the initial directory
        String lastChosenFilePath = loadLastChosenFilePath();
        if (lastChosenFilePath != null && !lastChosenFilePath.isEmpty()) {
            chooser.setCurrentDirectory(new File(lastChosenFilePath));
        }
        
        // Display the file chooser dialog
        int returnValue = chooser.showOpenDialog(null);
        
        // If a file is chosen and it's a .txt file, update the filepath and save the new chosen path
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File database = chooser.getSelectedFile();
            if (database.getName().toLowerCase().endsWith(".dat")) {
                filepath = database.getAbsolutePath();
                saveLastChosenFilePath(filepath);
            } else {
                // If the chosen file is not a .txt file, show an error message and prompt for selection again
                JOptionPane.showMessageDialog(null, "Please select a .dat file!");
                chooseFile(); // Recursive call to select a file again
            }
        }
    }

    /**
     * Retrieves the current file path.
     *
     * @return The current file path.
     */
    public static String getFilePath() {
        return filepath;
    }
    
    /**
     * Retrieves the name of the selected file.
     *
     * @return The name of the selected file.
     */
    public static String getFileName() {
        File selectedFile = new File(filepath);
        return selectedFile.getName();
    }

    /**
     * Loads the last chosen file path from the configuration file.
     *
     * @return The last chosen file path.
     */
    private static String loadLastChosenFilePath() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            // Creates a Properties object to handle key-value pairs
            Properties prop = new Properties();
            // Loads properties from the input stream into the Properties object
            prop.load(input);
            // Retrieves the value associated with the key LAST_CHOSEN_FILE and returns it
            return prop.getProperty(LAST_CHOSEN_FILE);
        } catch (IOException ex) {
            // Prints the stack trace if an IOException occurs
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Saves the last chosen file path to the configuration file.
     *
     * @param path The file path to save as the last chosen file.
     */
    private static void saveLastChosenFilePath(String path) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            // Creates a Properties object to handle key-value pairs
            Properties prop = new Properties();
            // Sets the property LAST_CHOSEN_FILE to the provided path
            prop.setProperty(LAST_CHOSEN_FILE, path);
            // Stores the properties in the output stream
            prop.store(output, null);
        } catch (IOException ex) {
            // Prints the stack trace if an IOException occurs
            ex.printStackTrace();
        }
    }
}
