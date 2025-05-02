package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;

/**
 * Custom renderer for the transaction history table in the PlaSystem application.
 * Configures column widths, applies decimal formatting to amount columns (Total Amount,
 * Payment Amount, Change Given), and sets alignment (left for ID, right for amounts).
 */
public class TransactionHistoryTableRenderer {
    /** The JTable to render. */
    private final JTable table;
    
    /** The preferred total width of the table in pixels. */
    private final int tableWidth;

    /**
     * Constructs a TransactionHistoryTableRenderer to initialize rendering for the specified table.
     * Applies custom column widths, decimal formatting, and alignment based on the provided table width.
     *
     * @param table      The JTable to render. Must not be null and must have at least 6 columns
     *                   (ID, Date, Time, Total Amount, Payment Amount, Change Given).
     * @param tableWidth The preferred width of the table in pixels. Should be positive.
     * @throws NullPointerException if table or its column model is null.
     */
    public TransactionHistoryTableRenderer(JTable table, int tableWidth) {
        this.table = table;
        this.tableWidth = tableWidth;
        applyColumnWidths();
        applyDecimalFormatting();
        applyColumnAlignment();
    }

    /**
     * Applies custom column widths, setting a minimal fixed width for the ID column and
     * distributing the remaining width equally among other columns. Assumes the table has
     * at least 6 columns (ID, Date, Time, Total Amount, Payment Amount, Change Given).
     *
     * @throws NullPointerException if the table's column model is null.
     */
    private void applyColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        int[] widths = new int[columnCount];

        // Set ID column to minimal width
        widths[0] = 40; // ID column
        int remainingWidth = tableWidth - widths[0];
        int equalWidth = remainingWidth / (columnCount - 1);

        // Distribute remaining width equally
        for (int i = 1; i < columnCount; i++) {
            widths[i] = equalWidth;
        }

        // Apply widths
        for (int i = 0; i < columnCount; i++) {
            columnModel.getColumn(i).setPreferredWidth(widths[i]);
        }
    }

    /**
     * Applies decimal formatting to the amount columns (Total Amount, Payment Amount, Change Given)
     * using a custom DecimalFormatRenderer to display values with two decimal places.
     *
     * @throws NullPointerException if the table's column model is null.
     */
    private void applyDecimalFormatting() {
        DecimalFormatRenderer renderer = new DecimalFormatRenderer();
        TableColumnModel columnModel = table.getColumnModel();
        // Apply to Total Amount, Payment Amount, Change Given
        columnModel.getColumn(3).setCellRenderer(renderer);
        columnModel.getColumn(4).setCellRenderer(renderer);
        columnModel.getColumn(5).setCellRenderer(renderer);
    }

    /**
     * Applies alignment to columns: left for the ID column, right for the amount columns
     * (Total Amount, Payment Amount, Change Given). Uses a left-aligned renderer for the ID
     * column and a right-aligned DecimalFormatRenderer for the amount columns.
     *
     * @throws NullPointerException if the table's column model is null.
     */
    private void applyColumnAlignment() {
        TableColumnModel columnModel = table.getColumnModel();

        // Left align ID column (index 0)
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        columnModel.getColumn(0).setCellRenderer(leftRenderer);

        // Right align Total Amount, Payment Amount, Change Given (indices 3, 4, 5)
        DecimalFormatRenderer rightRenderer = new DecimalFormatRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        columnModel.getColumn(3).setCellRenderer(rightRenderer);
        columnModel.getColumn(4).setCellRenderer(rightRenderer);
        columnModel.getColumn(5).setCellRenderer(rightRenderer);
    }

    /**
     * Custom renderer to format numeric values to two decimal places in table cells.
     * Extends DefaultTableCellRenderer to format numbers using a DecimalFormat with "#0.00" pattern.
     */
    private static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        /** Formatter for displaying numbers with two decimal places. */
        private final DecimalFormat formatter = new DecimalFormat("#0.00");

        /**
         * Renders a table cell, formatting numeric values to two decimal places and passing
         * non-numeric values unchanged. Applies the specified alignment and selection styling.
         *
         * @param table      The JTable containing the cell.
         * @param value      The value to render (expected to be a Number for amount columns).
         * @param isSelected True if the cell is selected.
         * @param hasFocus   True if the cell has focus.
         * @param row        The row index of the cell.
         * @param column     The column index of the cell.
         * @return The rendered component for the table cell.
         */
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
            if (value instanceof Number number) {
                value = formatter.format(number.doubleValue());
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}