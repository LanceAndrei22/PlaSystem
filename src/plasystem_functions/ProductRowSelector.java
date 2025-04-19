package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A utility class to retrieve data from the selected row of a JTable as a ProductData object.
 * Handles cases where the table is sorted, filtered, or searched by converting view indices to model indices.
 */
public class ProductRowSelector {
    private ProductData productData;
    private int viewRow;

    /**
     * Constructor to extract data from the selected row of the provided JTable.
     *
     * @param jTable The JTable from which data needs to be extracted.
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
     * Gets the ProductData object representing the selected row.
     *
     * @return The ProductData object, or an empty ProductData if no valid row is selected.
     */
    public ProductData getProductData() {
        return productData;
    }

    /**
     * Gets the model row index of the selected row.
     *
     * @return The model row index, or -1 if no valid row is selected.
     */
    public int getRow() {
        return viewRow;
    }
}