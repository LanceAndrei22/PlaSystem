package datamine_main;

import com.formdev.flatlaf.FlatDarkLaf;
import datamine_gui.*;

import javax.swing.*;

/**
 * The main class to start the DataMine application.
 */
public class DataMine {
    /**
     * The main method to initialize and launch the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        FlatDarkLaf.setup(); // Set up FlatDarkLaf look and feel for the UI
        
        // Create a JFrame instance of LaunchPanelGUI to start the application
        JFrame launchPanel = new LaunchPanelGUI();
        launchPanel.setVisible(true); // Make the launch panel visible
    }
}