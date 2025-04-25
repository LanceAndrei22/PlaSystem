package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

/**
 * Custom renderer for the restock table to adjust column widths dynamically.
 */
public class RestockTableRenderer {
    private final JTable table;

    /**
     * Constructor applies rendering to the specified table.
     *
     * @param table The JTable to render.
     */
    public RestockTableRenderer(JTable table) {
        this.table = table;
        adjustColumnWidths();
    }

    /**
     * Adjusts column widths to fit content, minimizing "Select" and "ID" columns.
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
     * Calculates the maximum width needed for the ID column based on content.
     *
     * @return The calculated width.
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