package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Custom table renderer for the restock history table in the PlaSystem application.
 * Configures the ID column with a minimal fixed width and left alignment, while
 * distributing the remaining table width equally between the Date and Time columns.
 */
public class RestockHistoryTableRenderer {
    /** The JTable to render. */
    private final JTable table;
    
    /** Minimal fixed width for the ID column (in pixels). */
    private static final int ID_COLUMN_WIDTH = 50;

    /**
     * Constructs a RestockHistoryTableRenderer to initialize rendering for the specified table.
     * Applies custom column widths and alignment based on the provided total table width.
     *
     * @param table      The JTable to render. Must not be null and must have at least 3 columns
     *                   (ID, Date, Time).
     * @param tableWidth The total width of the table in pixels. Should be positive and sufficient
     *                   to accommodate the ID column width.
     * @throws NullPointerException if table or its column model is null.
     */
    public RestockHistoryTableRenderer(JTable table, int tableWidth) {
        this.table = table;
        applyColumnWidthsAndAlignment(tableWidth);
    }

    /**
     * Applies custom widths and alignment to the table columns. Sets the ID column to a fixed
     * minimal width and left alignment, and equally distributes the remaining width between the
     * Date and Time columns. Assumes the table has at least 3 columns (ID, Date, Time).
     *
     * @param tableWidth The total width of the table in pixels. Should be positive and greater
     *                   than or equal to ID_COLUMN_WIDTH to avoid negative column widths.
     * @throws NullPointerException if the table's column model is null.
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