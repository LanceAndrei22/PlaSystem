package plasystem_functions;

import javax.swing.*;
import javax.swing.table.*;

/**
 * A utility class to retrieve data from the selected row of a JTable.
 */
public class TableRowSelector {
    private String tblProductID = "";
    private String tblQuantity = "";
    private String tblPrice = "";
    private String tblName = "";
    private String tblSize = "";
    private String tblBrand = "";
    private String tblType = "";
    private String tblRestockValue = "";
    private int viewRow = 0;

    /**
     * Constructor to extract data from the selected row of the provided JTable.
     *
     * @param jTable The JTable from which data needs to be extracted.
     */
    public TableRowSelector(JTable jTable) {
        viewRow = jTable.getSelectedRow(); // Get the index of the selected row in the view

        // Check if a row is selected (row index starts from 0, -1 means no selection)
        if (viewRow != -1) {
            DefaultTableModel tblModel = (DefaultTableModel) jTable.getModel();
            int modelRow = jTable.convertRowIndexToModel(viewRow);

            // Retrieve data using the model index
            tblProductID = tblModel.getValueAt(modelRow, 0).toString();
            tblName = tblModel.getValueAt(modelRow, 1).toString();
            tblBrand = tblModel.getValueAt(modelRow, 2).toString();
            tblSize = tblModel.getValueAt(modelRow, 3).toString();
            tblType = tblModel.getValueAt(modelRow, 4).toString();
            tblPrice = tblModel.getValueAt(modelRow, 5).toString();
            tblQuantity = tblModel.getValueAt(modelRow, 6).toString();
            tblRestockValue = tblModel.getValueAt(modelRow, 7).toString();
            viewRow = modelRow;
        }
    }

    // Getter methods for each retrieved data field
    public String getTblPrice() {
        return tblPrice;
    }

    public String getTblName() {
        return tblName;
    }

    public String getTblSize() {
        return tblSize;
    }

    public String getTblBrand() {
        return tblBrand;
    }

    public String getTblType() {
        return tblType;
    }

    public String getTblProductID() {
        return tblProductID;
    }

    public String getQuantity() {
        return tblQuantity;
    }
    
    public String getRestockValue() {
        return tblRestockValue;
    }

    public int getRow() {
        return viewRow;
    }
}
