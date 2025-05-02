package plasystem_functions;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Utility class for applying custom rendering to a cart table in the PlaSystem application.
 * The cart table is expected to have three columns: Name (left-aligned), Quantity (right-aligned),
 * and Price (right-aligned, formatted to two decimal places).
 */
public class CartTableRenderer {

    /**
     * Applies custom rendering to the provided JTable for proper alignment and formatting.
     * <ul>
     *     <li>Name (Column 0): Left-aligned</li>
     *     <li>Quantity (Column 1): Right-aligned</li>
     *     <li>Price (Column 2): Right-aligned, formatted to two decimal places</li>
     * </ul>
     * This method configures cell renderer for each column and adjusts column widths based on
     * sample content to ensure proper display.
     *
     * @param table The JTable to apply rendering to. Must have at least three columns.
     * @throws IllegalArgumentException if the table is null or has fewer than three columns.
     */
    public static void applyCartTableRendering(JTable table) {
        if (table == null) {
            throw new IllegalArgumentException("Table cannot be null.");
        }

        TableColumnModel columnModel = table.getColumnModel();
        if (columnModel.getColumnCount() < 3) {
            throw new IllegalArgumentException("Table must have at least three columns (Name, Quantity, Price).");
        }

        // Renderer for Name (left-aligned)
        DefaultTableCellRenderer nameRenderer = new DefaultTableCellRenderer() {
            /**
             * Sets the value for the Name column cell, ensuring left alignment.
             *
             * @param value The value to be displayed in the cell.
             */
            @Override
            protected void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setHorizontalAlignment(JLabel.LEFT);
            }
        };

        // Renderer for Quantity (right-aligned)
        DefaultTableCellRenderer quantityRenderer = new DefaultTableCellRenderer() {
            /**
             * Sets the value for the Quantity column cell, ensuring right alignment.
             *
             * @param value The value to be displayed in the cell.
             */
            @Override
            protected void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setHorizontalAlignment(JLabel.RIGHT);
            }
        };

        // Renderer for Price (right-aligned, two decimal places)
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            /**
             * Sets the value for the Price column cell, formatting numeric values to two decimal
             * places and ensuring right alignment. Non-numeric values are displayed as-is.
             *
             * @param value The value to be displayed in the cell.
             */
            @Override
            protected void setValue(Object value) {
                try {
                    double price = Double.parseDouble(value.toString());
                    setText(String.format("%.2f", price));
                } catch (NumberFormatException e) {
                    setText(value != null ? value.toString() : "");
                }
                setHorizontalAlignment(JLabel.RIGHT);
            }
        };

        // Apply renderers to cartTable columns
        columnModel.getColumn(0).setCellRenderer(nameRenderer); // Name
        columnModel.getColumn(1).setCellRenderer(quantityRenderer); // Quantity
        columnModel.getColumn(2).setCellRenderer(priceRenderer); // Price

        // Adjust column widths based on content
        FontMetrics metrics = table.getFontMetrics(table.getFont());
        int[] columnWidths = new int[3];
        columnWidths[0] = metrics.stringWidth("Sample Product Name") + 10; // Name
        columnWidths[1] = metrics.stringWidth("9999") + 10; // Quantity
        columnWidths[2] = metrics.stringWidth("9999.99") + 10; // Price

        // Apply widths
        for (int i = 0; i < 3; i++) {
            TableColumn column = columnModel.getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
            column.setMinWidth(columnWidths[i]);
        }
    }
}