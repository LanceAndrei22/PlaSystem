package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A renderer class to format a specific column in a JTable to two decimal places.
 */
public class TableAlignmentRenderer {

    /**
     * Constructor to set up the table cell renderer for a given column index.
     *
     * @param alignTable The JTable to format.
     * @param columnIndex The index of the column to format (0-based).
     */
    public TableAlignmentRenderer(JTable alignTable, int columnIndex) {
        // Custom renderer for decimal formatting (2 decimal places)
        DefaultTableCellRenderer decimalRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                try {
                    double price = Double.parseDouble(value.toString());
                    setText(String.format("%.2f", price));
                } catch (NumberFormatException e) {
                    setText(value.toString()); // Fallback if parsing fails
                }
                setHorizontalAlignment(JLabel.RIGHT); // Align right (optional)
            }
        };

        // Apply renderer if column exists
        if (alignTable.getColumnCount() > columnIndex) {
            alignTable.getColumnModel().getColumn(columnIndex).setCellRenderer(decimalRenderer);
        }
    }
}