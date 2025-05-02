package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

/**
 * Utility class for formatting and dynamically sizing columns in a JTable displaying product data
 * in the PlaSystem application. Aligns columns (left for ID, Name, Brand, Size, Type; right for
 * Price, Quantity, Restock Value), formats the Price column to two decimal places, and adjusts
 * column widths based on content length while respecting a total table width constraint.
 */
public class ProductTableRenderer {
    /** Padding added to column widths for visual appeal (in pixels). */
    private static final int PADDING = 10;
    
    /** Indices of left-aligned columns: Product ID, Name, Brand, Size, Type. */
    private static final int[] LEFT_ALIGNED_COLUMNS = {0, 1, 2, 3, 4};
    
    /** Indices of right-aligned columns: Price, Quantity, Restock Value. */
    private static final int[] RIGHT_ALIGNED_COLUMNS = {5, 6, 7};
    
    /** Index of the Price column, which requires special formatting. */
    private static final int PRICE_COLUMN = 5;

    /**
     * Constructs a ProductTableRenderer to set up cell renderers and dynamically size columns
     * for the given JTable based on the provided product data and total table width.
     *
     * @param alignTable      The JTable to format. Must not be null and must have at least 8 columns
     *                        (ID, Name, Brand, Size, Type, Price, Quantity, Restock Value).
     * @param productList     The list of ProductData objects to determine maximum content lengths.
     *                        May be empty, in which case default values are used.
     * @param totalTableWidth The total width available for the table (in pixels). Should be positive.
     * @throws NullPointerException if alignTable or its column model is null.
     */
    public ProductTableRenderer(JTable alignTable, List<ProductData> productList, int totalTableWidth) {
        // Renderer for Price column (2 decimal places, right-aligned)
        DefaultTableCellRenderer priceRenderer = new DefaultTableCellRenderer() {
            /**
             * Sets the value for the Price column, formatting numeric values to two decimal places
             * and aligning right. Non-numeric values are displayed as-is.
             *
             * @param value The value to display in the cell.
             */
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
            /**
             * Sets the value for right-aligned columns (Quantity, Restock Value), aligning right.
             * Null values are displayed as empty strings.
             *
             * @param value The value to display in the cell.
             */
            @Override
            protected void setValue(Object value) {
                setText(value != null ? value.toString() : "");
                setHorizontalAlignment(JLabel.RIGHT);
            }
        };

        // Renderer for left-aligned columns (Product ID, Name, Brand, Size, Type)
        DefaultTableCellRenderer leftAlignedRenderer = new DefaultTableCellRenderer() {
            /**
             * Sets the value for left-aligned columns (Product ID, Name, Brand, Size, Type),
             * aligning left. Null values are displayed as empty strings.
             *
             * @param value The value to display in the cell.
             */
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
     * Checks if a column index corresponds to a right-aligned column.
     *
     * @param columnIndex The index of the column to check.
     * @return {@code true} if the column is right-aligned (Price, Quantity, Restock Value),
     *         {@code false} otherwise.
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
     * Adjusts column widths based on the longest content in each column, ensuring the total width
     * does not exceed the specified table width. Scales widths proportionally if necessary and
     * applies padding for visual appeal.
     *
     * @param table           The JTable to adjust. Must not be null.
     * @param productList     The list of ProductData objects to determine content lengths.
     *                        May be empty, in which case default values are used.
     * @param totalTableWidth The total width available for the table (in pixels). Should be positive.
     * @throws NullPointerException if table or its column model is null.
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
     * Determines the longest value (as a string) for a given column based on the product list.
     * Uses default values for empty lists to ensure reasonable column widths.
     *
     * @param productList The list of ProductData objects. May be empty.
     * @param columnIndex The column index (0=Product ID, 1=Name, etc.).
     * @return The longest value as a string for the specified column.
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
     * Retrieves the string representation of a column's value for a ProductData object.
     * Formats the Price column to two decimal places for consistency.
     *
     * @param product     The ProductData object. Must not be null.
     * @param columnIndex The column index (0=Product ID, 1=Name, etc.).
     * @return The string representation of the column's value, or an empty string for invalid indices.
     * @throws NullPointerException if product is null.
     */
    private String getColumnValue(ProductData product, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> String.valueOf(product.getProductId());
            case 1 -> product.getProductName();
            case 2 -> product.getProductBrand();
            case 3 -> product.getProductSize();
            case 4 -> product.getProductType();
            case 5 -> String.format("%.2f", product.getProductPrice());
            case 6 -> String.valueOf(product.getProductQuantity());
            case 7 -> String.valueOf(product.getProductRestockValue());
            default -> "";
        };
    }
}