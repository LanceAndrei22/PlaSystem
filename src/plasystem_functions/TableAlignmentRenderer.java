package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import plasystem_gui.MainProgramGUI;
import plasystem_functions.ProductData;

/**
 * A renderer class to format and dynamically size columns in a JTable based on content.
 * Aligns columns as follows:
 * - Product ID, Name, Brand, Size, Type: Left-aligned
 * - Price, Quantity, Restock Value: Right-aligned
 * - Price: Formatted to two decimal places
 */
public class TableAlignmentRenderer {

    private static final int PADDING = 10; // Padding for visual appeal
    private static final int[] LEFT_ALIGNED_COLUMNS = {0, 1, 2, 3, 4}; // Product ID, Name, Brand, Size, Type
    private static final int[] RIGHT_ALIGNED_COLUMNS = {5, 6, 7}; // Price, Quantity, Restock Value
    private static final int PRICE_COLUMN = 5; // Index of Price column

    /**
     * Constructor to set up renders and dynamically size columns for the given JTable.
     *
     * @param alignTable The JTable to format.
     * @param productList The list of ProductData to determine maximum content lengths.
     * @param totalTableWidth The total width available for the table (in pixels).
     */
    public TableAlignmentRenderer(JTable alignTable, List<ProductData> productList, int totalTableWidth) {
        // Renderer for Price column (2 decimal places, right-aligned)
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                try {
                    double price = Double.parseDouble(value.toString());
                    setText(String.format("%.2f", price));
                } catch (NumberFormatException e) {
                    setText(value.toString()); // Fallback if parsing fails
                }
                setHorizontalAlignment(JLabel.RIGHT);
            }
        };

        // Renderer for other right-aligned columns (Quantity, Restock Value)
        DefaultTableCellRenderer rightAlignedRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setHorizontalAlignment(JLabel.RIGHT);
            }
        };

        // Renderer for left-aligned columns (Product ID, Name, Brand, Size, Type)
        DefaultTableCellRenderer leftAlignedRenderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setHorizontalAlignment(JLabel.LEFT);
            }
        };

        // Apply renderers to columns
        TableColumnModel columnModel = alignTable.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if (i == PRICE_COLUMN) {
                columnModel.getColumn(i).setCellRenderer(priceRenderer);
            } else if (isRightAlignedColumn(i)) {
                columnModel.getColumn(i).setCellRenderer(rightAlignedRenderer);
            } else {
                columnModel.getColumn(i).setCellRenderer(leftAlignedRenderer);
            }
        }

        // Dynamically size columns
        adjustColumnWidths(alignTable, productList, totalTableWidth);
    }

    /**
     * Checks if a column index is right-aligned.
     *
     * @param columnIndex The column index.
     * @return True if the column is right-aligned, false otherwise.
     */
    private boolean isRightAlignedColumn(int columnIndex) {
        for (int rightColumn : RIGHT_ALIGNED_COLUMNS) {
            if (columnIndex == rightColumn) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adjusts column widths based on the longest content in each column.
     * Ensures total width does not exceed the table’s available width.
     *
     * @param table The JTable to adjust.
     * @param productList The list of ProductData to determine content lengths.
     * @param totalTableWidth The total width available for the table.
     */
    private void adjustColumnWidths(JTable table, List<ProductData> productList, int totalTableWidth) {
        TableColumnModel columnModel = table.getColumnModel();
        FontMetrics metrics = table.getFontMetrics(table.getFont());
        int columnCount = columnModel.getColumnCount();
        int[] columnWidths = new int[columnCount];

        // Calculate maximum width for each column
        for (int col = 0; col < columnCount; col++) {
            String longestValue = getLongestValueForColumn(productList, col);
            columnWidths[col] = metrics.stringWidth(longestValue) + PADDING;
        }

        // Calculate total width of all columns
        int totalCalculatedWidth = 0;
        for (int width : columnWidths) {
            totalCalculatedWidth += width;
        }

        // Scale widths if they exceed totalTableWidth
        if (totalCalculatedWidth > totalTableWidth) {
            double scaleFactor = (double) totalTableWidth / totalCalculatedWidth;
            for (int col = 0; col < columnCount; col++) {
                columnWidths[col] = (int) (columnWidths[col] * scaleFactor);
            }
        }

        // Apply widths to columns
        for (int col = 0; col < columnCount; col++) {
            TableColumn column = columnModel.getColumn(col);
            column.setPreferredWidth(columnWidths[col]);
            column.setMinWidth(columnWidths[col]); // Prevent shrinking
        }
    }

    /**
     * Gets the longest value (as a string) for a given column.
     *
     * @param productList The list of ProductData.
     * @param columnIndex The column index (0=Product ID, 1=Name, etc.).
     * @return The longest value as a string.
     */
    private String getLongestValueForColumn(List<ProductData> productList, int columnIndex) {
        String longestValue = "";
        int maxLength = 0;

        // Default values for empty table
        String[] defaultValues = {
            "9999", // Product ID
            "Sample Product Name", // Name
            "Sample Brand", // Brand
            "Sample Size", // Size
            "Sample Type", // Type
            "9999.99", // Price
            "9999", // Quantity
            "9999"  // Restock Value
        };

        if (productList.isEmpty()) {
            return defaultValues[columnIndex];
        }

        for (ProductData product : productList) {
            String value = getColumnValue(product, columnIndex);
            if (value.length() > maxLength) {
                maxLength = value.length();
                longestValue = value;
            }
        }

        return longestValue;
    }

    /**
     * Gets the string representation of a column’s value for a ProductData object.
     *
     * @param product The ProductData object.
     * @param columnIndex The column index.
     * @return The string value.
     */
    private String getColumnValue(ProductData product, int columnIndex) {
        switch (columnIndex) {
            case 0: return String.valueOf(product.getProductId());
            case 1: return product.getProductName();
            case 2: return product.getProductBrand();
            case 3: return product.getProductSize();
            case 4: return product.getProductType();
            case 5: return String.format("%.2f", product.getProductPrice());
            case 6: return String.valueOf(product.getProductQuantity());
            case 7: return String.valueOf(product.getProductRestockValue());
            default: return "";
        }
    }
}