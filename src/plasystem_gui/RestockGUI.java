package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.ProductData;
import plasystem_functions.TableAlignmentRenderer;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class RestockGUI extends JFrame {
    private JTable restockTable;
    private JTable mainTable; // new member variable to hold the main table reference
    private JButton restockBtn;
    private JButton cancelBtn;
    private DataHandling dataHandler;
    private LinkedList<ProductData> productList;
    private String filePath;

    /**
     * Constructor initializes the GUI and filters products needing restocking.
     *
     * @param productList1 The full product list.
     * @param plasystemTbl The main table from MainProgramGUI.
     * @param filePath     The file path to load and update product data.
     */
    public RestockGUI(LinkedList<ProductData> productList1, JTable plasystemTbl, String filePath) {
        this.filePath = filePath;
        this.dataHandler = new DataHandling(filePath);
        // Use the full list instead of filtering:
        this.productList = dataHandler.getList();
        this.mainTable = plasystemTbl; // store the main table reference

        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Filters items that have a Restock_Value below their threshold.
     *
     * @param fullList The complete product list.
     * @return Filtered list of products needing restock.
     */
    private LinkedList<ProductData> filterRestockItems(LinkedList<ProductData> fullList) {
        LinkedList<ProductData> restockItems = new LinkedList<>();
        for (ProductData product : fullList) {
            if (product.getProductQuantity() < product.getProductRestockValue()) {
                restockItems.add(product);
            }
        }
        return restockItems;
    }

    /**
     * Initializes the components for the Restock GUI.
     */
    private void initComponents() {
        setTitle("Restock Inventory");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // Table Model for restock table
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Select", "Product ID", "Name", "Current Qty", "Restock Value", "Incoming Qty"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                } else if (columnIndex == 5) {
                    return Integer.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 5;
            }
        };

        // Populate the restock table
        for (ProductData product : productList) {
            model.addRow(new Object[]{false, product.getProductID(), product.getProductName(), product.getProductQuantity(), product.getProductRestockValue(), 0});
        }

        restockTable = new JTable(model);
        restockTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        restockTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(restockTable);
        
        // Create a cell renderer that centers text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Apply the renderer to all columns except the first ("Select")
        for (int i = 1; i < restockTable.getColumnCount(); i++) {
            restockTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Buttons
        restockBtn = new JButton("Restock Selected");
        restockBtn.addActionListener(this::restockItems);

        cancelBtn = new JButton("Cancel");
        cancelBtn.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(restockBtn);
        buttonPanel.add(cancelBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    /**
     * Handles the restocking process and updates the main table dynamically.
     *
     * @param evt The action event from the button click.
     */
    private void restockItems(ActionEvent evt) {
        DefaultTableModel restockModel = (DefaultTableModel) restockTable.getModel();
        boolean updated = false;
        // Retrieve the full inventory list from DataHandling
        LinkedList<ProductData> fullList = dataHandler.getList();

        for (int i = 0; i < restockModel.getRowCount(); i++) {
            boolean selected = (boolean) restockModel.getValueAt(i, 0);
            int incomingQty = (int) restockModel.getValueAt(i, 5);

            if (selected && incomingQty > 0) {
                String productId = (String) restockModel.getValueAt(i, 1);
                for (ProductData product : fullList) {
                    if (product.getProductID().equals(productId)) {
                        product.setProductQuantity(product.getProductQuantity() + incomingQty);
                        updated = true;
                        break;
                    }
                }
            }
        }

        if (updated) {
            if (DataHandling.saveInventoryChanges(fullList, filePath)) {
                // Refresh the main table in MainProgramGUI dynamically
                DefaultTableModel mainModel = (DefaultTableModel) mainTable.getModel();
                mainModel.setRowCount(0);
                for (ProductData product : fullList) {
                    Object[] rowData = {
                        product.getProductID(),
                        product.getProductQuantity(),
                        product.getProductPrice(),
                        product.getProductName(),
                        product.getProductSize(),
                        product.getProductBrand(),
                        product.getProductType(),
                        product.getProductRestockValue()
                    };
                    mainModel.addRow(rowData);
                }
                JOptionPane.showMessageDialog(this, "Restocking successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving changes!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No items selected or invalid quantities!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
