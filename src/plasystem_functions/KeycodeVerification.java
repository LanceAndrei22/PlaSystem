package plasystem_functions;

import javax.swing.JOptionPane;
import java.io.*;

/**
 * Handles verification and checks related to key codes stored in a file.
 */
public class KeycodeVerification {
    private String keyFilePath = "src/plasystem_main/key.dat"; // Path to the keycode file
    
    /**
     * Verifies if the provided key code matches any key code in the file.
     *
     * @param keycode The key code to verify.
     * @return True if the key code is valid, otherwise false.
     */
    public boolean verifyKeycode(String keycode) {
        // Check the keycode against a text file
        try (BufferedReader br = new BufferedReader(new FileReader(keyFilePath))) {
            String line;
            // Read each line in the file
            while ((line = br.readLine()) != null) {
                if (line.equals(keycode)) {
                    return true; // Keycode found in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Prints the exception trace if an error occurs
        }
        return false; // Keycode not found or an error occurred
    }
    
    /**
     * Checks if the key code file is empty.
     *
     * @return True if the key code file is empty, otherwise false.
     */
    public boolean isKeycodeFileEmpty() {
        try (BufferedReader br = new BufferedReader(new FileReader(keyFilePath))) {
            return br.readLine() == null; // Check if the file has no content
        } catch (IOException e) {
            e.printStackTrace(); // Prints the exception trace if an error occurs
        }
        return true; // Error occurred or file not accessible
    }

    /**
     * Checks if the key code file is visible and accessible.
     *
     * @return True if the key code file cannot be seen or accessed, otherwise false.
     */
    public boolean isKeycodeFileVisible() {
        File file = new File(keyFilePath);
        if (!file.exists() || !file.canRead()) {
            JOptionPane.showMessageDialog(null, "Keycode File cannot be seen or accessed!", "Error", JOptionPane.ERROR_MESSAGE);
            return true; // File is not visible or accessible
        }
        return false; // File is visible and accessible
    }
}