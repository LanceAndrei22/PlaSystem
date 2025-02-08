package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A renderer class to align the content in a JTable to the center.
 */
public class TableAlignmentRenderer {
    
    /**
     * Constructor to set up the table cell renderer for alignment.
     *
     * @param alignTable The JTable to align its content.
     */
    public TableAlignmentRenderer(JTable alignTable) {
        // Create a default cell renderer for center alignment
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        // Loop through each column of the provided table
        for (int i = 0; i < alignTable.getColumnCount(); i++) {
            // Set the cell renderer for each column to the centerRenderer
            alignTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}