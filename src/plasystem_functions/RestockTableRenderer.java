package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Custom renderer for the restock table in the PlaSystem application, dynamically adjusting
 * column widths to fit content. Minimizes the "Select" (checkbox) and "ID" columns while
 * sizing other columns based on their maximum content width, with padding and a width cap.
 */
public class RestockTableRenderer {
    /** The JTable to render. */
    private final JTable table;

    /**
     * Constructs a RestockTableRenderer to apply rendering to the specified table.
     * Adjusts column widths upon initialization to fit content and optimize display.
     *
     * @param table The JTable to render. Must not be null and must have at least 2 columns
     *              (Select, ID, and potentially others).
     * @throws NullPointerException if table or its column model is null.
     */
    public RestockTableRenderer(JTable table) {
        this.table = table;
        adjustColumnWidths();
    }

    /**
     * Adjusts column widths to fit content, setting fixed minimal widths for the "Select" (checkbox)
     * and "ID" columns, and dynamically sizing other columns based on their maximum content width.
     * Applies padding to all columns and caps dynamic column widths at 300 pixels to prevent
     * excessive expansion.
     *
     * @throws NullPointerException if the table's column model is null.
     */
    private void adjustColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        int rowCount = table.getRowCount();

        // Adjust "Select" column (checkbox)
        TableColumn selectColumn = columnModel.getColumn(0);
        selectColumn.setPreferredWidth(50); // Fixed small width for checkbox
        selectColumn.setMaxWidth(50);
        selectColumn.setMinWidth(20);

        // Adjust "ID" column
        TableColumn idColumn = columnModel.getColumn(1);
        int maxIdWidth = calculateMaxIdWidth();
        idColumn.setPreferredWidth(maxIdWidth);
        idColumn.setMaxWidth(maxIdWidth + 20);
        idColumn.setMinWidth(maxIdWidth);

        // Adjust other columns to fit content
        for (int col = 2; col < columnModel.getColumnCount(); col++) {
            TableColumn column = columnModel.getColumn(col);
            int maxWidth = 0;
            for (int row = 0; row < rowCount; row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, col);
                Component comp = table.prepareRenderer(renderer, row, col);
                maxWidth = Math.max(comp.getPreferredSize().width, maxWidth);
            }
            maxWidth = Math.min(maxWidth + 10, 300); // Add padding, cap at 300
            column.setPreferredWidth(maxWidth);
        }
    }

    /**
     * Calculates the maximum width needed for the ID column based on the content of all rows.
     * Uses the table's font metrics to measure the width of ID values and adds padding.
     *
     * @return The calculated width for the ID column in pixels, including padding.
     */
    private int calculateMaxIdWidth() {
        int maxWidth = 0;
        FontMetrics metrics = table.getFontMetrics(table.getFont());
        for (int row = 0; row < table.getRowCount(); row++) {
            Object value = table.getValueAt(row, 1);
            if (value != null) {
                String idStr = value.toString();
                int width = metrics.stringWidth(idStr);
                maxWidth = Math.max(maxWidth, width);
            }
        }
        return maxWidth + 10; // Add padding
    }
}