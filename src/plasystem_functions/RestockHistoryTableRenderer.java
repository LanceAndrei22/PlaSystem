package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Custom table renderer for the restock history table, setting the ID column to the smallest possible width
 * and left-aligned, with Date and Time columns proportionally sized.
 */
public class RestockHistoryTableRenderer {
    private final JTable table;
    private static final int ID_COLUMN_WIDTH = 50; // Minimal width for ID column (in pixels)

    /**
     * Constructor initializes the renderer with the table and total width.
     *
     * @param table      The JTable to render.
     * @param tableWidth The total width of the table in pixels.
     */
    public RestockHistoryTableRenderer(JTable table, int tableWidth) {
        this.table = table;
        applyColumnWidthsAndAlignment(tableWidth);
    }

    /**
     * Applies custom widths and alignment to the table columns.
     * ID column is set to a minimal fixed width and left-aligned.
     * Date and Time columns share the remaining width equally.
     *
     * @param tableWidth The total width of the table in pixels.
     */
    private void applyColumnWidthsAndAlignment(int tableWidth) {
        TableColumnModel columnModel = table.getColumnModel();

        // Calculate widths
        int remainingWidth = tableWidth - ID_COLUMN_WIDTH;
        int dateWidth = remainingWidth / 2; // 50% of remaining width
        int timeWidth = remainingWidth - dateWidth; // Remaining 50%

        // Set column widths
        columnModel.getColumn(0).setPreferredWidth(ID_COLUMN_WIDTH); // ID
        columnModel.getColumn(1).setPreferredWidth(dateWidth); // Date
        columnModel.getColumn(2).setPreferredWidth(timeWidth); // Time

        // Apply left alignment to ID column
        DefaultTableCellRenderer idRenderer = new DefaultTableCellRenderer();
        idRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        columnModel.getColumn(0).setCellRenderer(idRenderer);
    }
}