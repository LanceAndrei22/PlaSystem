package plasystem_functions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The FrameExporter class is responsible for exporting the content of a JFrame as an image.
 */
public class FrameExporter {

    /**
     * Exports the content of a JFrame as an image.
     *
     * @param frameToExport The JFrame whose content needs to be exported.
     * @param imageName     The name to be given to the exported image.
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
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error exporting frame as image.");
        }
    }
}