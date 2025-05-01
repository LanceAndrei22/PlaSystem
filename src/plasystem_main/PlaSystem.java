package plasystem_main;

import plasystem_gui.LaunchPanelGUI;
import com.formdev.flatlaf.FlatIntelliJLaf;

import javax.swing.*;
import java.net.ServerSocket;
import java.io.IOException;

/**
 * The main class to start the PlaSystem application.
 */
public class PlaSystem {
    // Fixed port for single-instance check
    private static final int PORT = 9999;
    private static ServerSocket serverSocket;

    /**
     * The main method to initialize and launch the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Check if another instance is already running
        try {
            serverSocket = new ServerSocket(PORT);
            // Register shutdown hook to close the socket when the application exits
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
        } catch (IOException e) {
            // Port is already in use, another instance is running
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null,
                    "PlaSystem is already running. Only one instance can be launched at a time.",
                    "Instance Already Running",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            });
            return;
        }

        // Set up FlatIntelliJLaf look and feel for the UI
        FlatIntelliJLaf.setup();

        // Create a JFrame instance of LaunchPanelGUI to start the application
        SwingUtilities.invokeLater(() -> {
            JFrame launchPanel = new LaunchPanelGUI();
            launchPanel.pack();
            launchPanel.setVisible(true); // Make the launch panel visible
        });
    }
}