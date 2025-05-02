package plasystem_main;

import plasystem_gui.LaunchPanelGUI;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.net.ServerSocket;
import java.io.IOException;

/**
 * The main class to start the PlaSystem application.
 * Ensures only a single instance of the application runs and initializes the UI.
 */
public class PlaSystem {
    /** The fixed port used to check for a running instance of the application. */
    private static final int PORT = 9999;
    /** The ServerSocket used to enforce single-instance execution. */
    private static ServerSocket serverSocket;

    /**
     * The main method to initialize and launch the application.
     * Checks for single-instance execution and starts the LaunchPanelGUI.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Check if another instance is already running by attempting to bind to the port
        try {
            // Create a ServerSocket on the specified port
            serverSocket = new ServerSocket(PORT);
            // Register a shutdown hook to close the socket when the application exits
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                // Check if the socket exists and is not closed
                if (serverSocket != null && !serverSocket.isClosed()) {
                    try {
                        // Close the socket to release the port
                        serverSocket.close();
                    } catch (IOException e) {
                        // Print the stack trace for any errors during socket closure
                        e.printStackTrace();
                    }
                }
            }));
        } catch (IOException e) {
            // Port is already in use, indicating another instance is running
            SwingUtilities.invokeLater(() -> {
                // Display an error message to the user
                JOptionPane.showMessageDialog(null,
                    "PlaSystem is already running. Only one instance can be launched at a time.",
                    "Instance Already Running",
                    JOptionPane.ERROR_MESSAGE);
                // Exit the application
                System.exit(1);
            });
            return;
        }

        // Set up the FlatIntelliJLaf look and feel for the UI
        FlatIntelliJLaf.setup();

        // Launch the LaunchPanelGUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create a new LaunchPanelGUI instance
            JFrame launchPanel = new LaunchPanelGUI();
            // Pack the frame to fit its contents
            launchPanel.pack();
            // Make the launch panel visible
            launchPanel.setVisible(true);
        });
    }
}