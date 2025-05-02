package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Utility class for retrieving data from the selected row of a JTable as a ProductData object in the PlaSystem application.
 * Handles cases where the table is sorted, filtered, or searched by converting view indices to model indices.
 * Provides error handling for invalid selections or data parsing issues.
 */
public class ProductRowSelector {
    /** The ProductData object representing the selected row's data. */
    private ProductData productData;
    
    /** The model row index of the selected row, or -1 if no valid row is selected. */
    private int viewRow;

    /**
     * Constructs a ProductRowSelector to extract data from the selected row of the provided JTable.
     * Converts the view row index to a model row index to handle sorting or filtering, retrieves
     * the row data, and creates a ProductData object. If no row is selected, the row index is invalid,
     * or data parsing fails, an empty ProductData object is created, and appropriate error messages
     * are displayed.
     *
     * @param jTable The JTable from which to extract the selected row's data. Must not be null
     *               and must have a DefaultTableModel with at least 8 columns (ID, name, brand,
     *               size, type, price, quantity, restock value).
     * @throws NullPointerException if jTable or its model is null.
     */
    public ProductRowSelector(JTable jTable) {
        viewRow = jTable.getSelectedRow(); // Get the view index of the selected row
        productData = null; // Initialize as null

        // Check if a row is selected (viewRow == -1 means no selection)
        if (viewRow != -1) {
            DefaultTableModel tblModel = (DefaultTableModel) jTable.getModel();
            // Convert view index to model index to handle sorting/filtering
            int modelRow = jTable.convertRowIndexToModel(viewRow);

            // Validate model row index
            if (modelRow < 0 || modelRow >= tblModel.getRowCount()) {
                JOptionPane.showMessageDialog(null,
                    "Invalid row selected. The table may be incorrectly sorted or filtered.",
                    "Selection Error",
                    JOptionPane.ERROR_MESSAGE);
                productData = new ProductData(); // Fallback to empty object
                viewRow = -1; // Reset viewRow to indicate invalid selection
                return;
            }

            try {
                // Retrieve data using the model index and create a ProductData object
                int productId = Integer.parseInt(tblModel.getValueAt(modelRow, 0).toString());
                String productName = tblModel.getValueAt(modelRow, 1).toString();
                String productBrand = tblModel.getValueAt(modelRow, 2).toString();
                String productSize = tblModel.getValueAt(modelRow, 3).toString();
                String productType = tblModel.getValueAt(modelRow, 4).toString();
                double productPrice = Double.parseDouble(tblModel.getValueAt(modelRow, 5).toString());
                int productQuantity = Integer.parseInt(tblModel.getValueAt(modelRow, 6).toString());
                int productRestockValue = Integer.parseInt(tblModel.getValueAt(modelRow, 7).toString());

                // Create ProductData using constructor to initialize all fields
                productData = new ProductData(
                    productId,
                    productName,
                    productBrand,
                    productSize,
                    productType,
                    productPrice,
                    productQuantity,
                    productRestockValue
                );
                viewRow = modelRow; // Store model row index for consistency
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                    "Error parsing table data: " + e.getMessage() + ". Ensure numeric fields are valid.",
                    "Data Error",
                    JOptionPane.ERROR_MESSAGE);
                productData = new ProductData(); // Fallback to empty object
                viewRow = -1; // Reset viewRow to indicate invalid selection
            }
        } else {
            productData = new ProductData(); // No row selected, use empty object
        }
    }

    /**
     * Retrieves the ProductData object representing the selected row's data.
     *
     * @return The ProductData object, or an empty ProductData object if no valid row
     *         is selected or an error occurred during data extraction.
     */
    public ProductData getProductData() {
        return productData;
    }

    /**
     * Retrieves the model row index of the selected row.
     *
     * @return The model row index, or -1 if no valid row is selected or an error
     *         occurred during row selection.
     */
    public int getRow() {
        return viewRow;
    }
}