package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.ProductData;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * RestockGUI displays products that need restocking and allows adding incoming shipment quantities.
 */
public class RestockGUI extends JFrame {
    private JTable restockTable;
    private JButton restockBtn;
    private JButton cancelBtn;
    private DataHandling dataHandler;
    private LinkedList<ProductData> productList;
    private String filePath;

    /**
     * Constructor initializes the GUI and filters products needing restocking.
     *
     * @param filePath The file path to load and update product data.
     */
    public RestockGUI(LinkedList<ProductData> productList1, JTable plasystemTbl, String filePath) {
        this.filePath = filePath;
        this.dataHandler = new DataHandling(filePath);
        this.productList = filterRestockItems(dataHandler.getList());

        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * Filters items that have a Restock_Value below 45.
     *
     * @param fullList The complete product list.
     * @return Filtered list of products needing restock.
     */
    private LinkedList<ProductData> filterRestockItems(LinkedList<ProductData> fullList) {
        LinkedList<ProductData> restockItems = new LinkedList<>();
        for (ProductData product : fullList) {
            if (product.getProductRestockValue() < 45) {
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

        // Table Model
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Select", "Product ID", "Name", "Current Qty", "Restock Value", "Incoming Qty"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return (columnIndex == 0) ? Boolean.class : super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0 || column == 5; // Only checkboxes and incoming quantity are editable
            }
        };

        // Populate Table
        for (ProductData product : productList) {
            model.addRow(new Object[]{false, product.getProductID(), product.getProductName(), product.getProductQuantity(), product.getProductRestockValue(), 0});
        }

        restockTable = new JTable(model);
        restockTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        restockTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(restockTable);

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
     * Handles the restocking process.
     *
     * @param evt The action event from the button click.
     */
    private void restockItems(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        boolean updated = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            boolean selected = (boolean) model.getValueAt(i, 0);
            int incomingQty = (int) model.getValueAt(i, 5);

            if (selected && incomingQty > 0) {
                String productId = (String) model.getValueAt(i, 1);

                for (ProductData product : productList) {
                    if (product.getProductID().equals(productId)) {
                        product.setProductQuantity(product.getProductQuantity() + incomingQty);
                        updated = true;
                        break;
                    }
                }
            }
        }

        if (updated) {
            DataHandling.saveInventoryChanges(productList, filePath);
            JOptionPane.showMessageDialog(this, "Restocking successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No items selected or invalid quantities!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
