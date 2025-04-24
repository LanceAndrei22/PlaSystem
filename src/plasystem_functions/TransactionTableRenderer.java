package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;

public class TransactionTableRenderer {
    private final JTable table;
    private final int tableWidth;

    /**
     * Constructor for TransactionTableRenderer.
     *
     * @param table      The JTable to render.
     * @param tableWidth The preferred width of the table.
     */
    public TransactionTableRenderer(JTable table, int tableWidth) {
        this.table = table;
        this.tableWidth = tableWidth;
        applyColumnWidths();
        applyDecimalFormatting();
        applyColumnAlignment();
    }

    /**
     * Applies custom column widths, minimizing the ID column.
     */
    private void applyColumnWidths() {
        TableColumnModel columnModel = table.getColumnModel();
        int columnCount = columnModel.getColumnCount();
        int[] widths = new int[columnCount];

        // Set ID column to minimal width
        widths[0] = 30; // ID column
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
     * Applies decimal formatting to amount columns.
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
     * Applies alignment to columns: left for ID, right for amounts.
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
     * Custom renderer to format double values to two decimal places.
     */
    private static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        private final DecimalFormat formatter = new DecimalFormat("#0.00");

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
            if (value instanceof Number) {
                value = formatter.format(((Number) value).doubleValue());
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
}