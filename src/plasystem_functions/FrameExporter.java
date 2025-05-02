package plasystem_functions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Utility class for exporting the content of a JFrame as a PNG image in the PlaSystem application.
 * The exported image is saved in a specified directory, capturing the visual content of the frame's
 * content pane.
 */
public class FrameExporter {

    /**
     * Exports the content of a JFrame's content pane as a PNG image. The image is saved in a
     * "receipts" directory, which is created if it does not exist. The method captures the visual
     * content of the frame, saves it with the specified name, and displays a confirmation message
     * upon success or an error message if the export fails.
     *
     * @param frameToExport The JFrame whose content pane will be exported as an image. Must not be null.
     * @param imageName     The base name for the exported image file (without extension). A ".png" extension
     *                      is automatically appended. Must not be null or empty.
     * @throws NullPointerException if frameToExport or imageName is null.
     */
    public static void exportFrameAsImage(JFrame frameToExport, String imageName) {
        // Define the directory path to save the image
        String directory = "receipts";
        File directoryFile = new File(directory);

        // Check if directory doesn't exist, create it
        if (!directoryFile.exists()) {
            directoryFile.mkdirs(); // Create directory and any necessary parent directories
        }

        try {
            // Capture the content of the frame
            Rectangle screenRect = frameToExport.getContentPane().getBounds();
            BufferedImage capture = new BufferedImage(screenRect.width, screenRect.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = capture.createGraphics();
            frameToExport.getContentPane().paint(graphics);
            graphics.dispose();

            // Specify the directory where the captured content will be saved
            File outputFile = new File(directoryFile, imageName + ".png");
            ImageIO.write(capture, "png", outputFile);
            JOptionPane.showMessageDialog(null, "Frame exported as image: " + outputFile.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error exporting frame as image." + ex);
        }
    }
}