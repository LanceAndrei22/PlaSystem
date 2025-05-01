package plasystem_gui;

import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

/**
 * GUI for restocking products, displaying products needing restock and allowing quantity input.
 */
public class RestockProductGUI extends javax.swing.JFrame {
    private ProductDataManager productDataManager;
    private RestockDataManager restockDataManager;
    private MainProgramGUI parentGUI;
    private ErrorValueHandling dataValidator;
    private TableRowSorter<DefaultTableModel> tableSorter;
    
    /**
     * Default constructor for the RestockProductGUI.
     */
    public RestockProductGUI(){
        initComponents();
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor for RestockProductGUI.
     *
     * @param mainGUI The parent MainProgramGUI to update its table.
     * @param productDataManager Manager for product data operations.
     * @param restockDataManager Manager for restock data operations.
     */
    public RestockProductGUI(MainProgramGUI mainGUI, ProductDataManager productDataManager, RestockDataManager restockDataManager) {
        this.parentGUI = mainGUI;
        this.productDataManager = productDataManager;
        this.restockDataManager = restockDataManager;
        this.dataValidator = new ErrorValueHandling();
        initComponents();
        setLocationRelativeTo(null);
        initializeTable();
    }
    
    /**
     * Initializes the table and tableSorter.
     */
    private void initializeTable() {
        populateTable();
        applyTableRenderer();
        tableSorter = new TableRowSorter<>((DefaultTableModel) restockProductTbl.getModel());
        restockProductTbl.setRowSorter(tableSorter);
    }
    
    /**
     * Populates the restock table with products where quantity is less than or equal to restock value.
     */
    private void populateTable() {
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        restockProductTblModel.setRowCount(0);
        List<ProductData> productList = productDataManager.getList();
        for (ProductData product : productList) {
            if (product.getProductQuantity() <= product.getProductRestockValue()) {
                restockProductTblModel.addRow(new Object[]{
                    false, // Select
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue(),
                    0 // Incoming Qty
                });
            }
        }
    }
    
    /**
     * Applies the custom table renderer for column alignment.
     */
    private void applyTableRenderer() {
        new RestockTableRenderer(restockProductTbl);
    }
    
    /**
     * Refreshes the table with the latest database state, resetting selections and quantities.
     */
    private void refreshTable() {
        populateTable();
        // Reapply renderer and search filter
        applyTableRenderer();
        if (!searchTxtField.getText().trim().isEmpty()) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * Validates and processes restocking for selected products as a single restock event.
     */
    private void restockItems() {
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        List<Map<String, Object>> items = new ArrayList<>();
        
        // Validate all selected items using view indices
        for (int i = 0; i < restockProductTblModel.getRowCount(); i++) {
            boolean selected = (Boolean) restockProductTblModel.getValueAt(i, 0);
            if (selected) {
                Object incomingQtyObj = restockProductTblModel.getValueAt(i, 5);
                if (incomingQtyObj == null || incomingQtyObj.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "' cannot be empty.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String incomingQtyStr = incomingQtyObj.toString();
                if (!dataValidator.isInteger(incomingQtyStr)) {
                    JOptionPane.showMessageDialog(this,
                        "Invalid incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "'. Must be a non-negative integer.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int incomingQty = Integer.parseInt(incomingQtyStr);
                if (incomingQty <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "' must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int productId = (Integer) restockProductTblModel.getValueAt(i, 1);
                ProductData product = productDataManager.getList().stream()
                    .filter(p -> p.getProductId() == productId)
                    .findFirst()
                    .orElse(null);
                if (product == null) {
                    JOptionPane.showMessageDialog(this,
                        "Product with ID " + productId + " not found.",
                        "Restock Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Map<String, Object> item = new HashMap<>();
                item.put("product", product);
                item.put("quantity", incomingQty);
                items.add(item);
            }
        }

        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "No items selected or no valid quantities provided.",
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Process restocking as a single event
        try {
            boolean restockSuccess = restockDataManager.restockMultipleProducts(items);
            if (restockSuccess) {
                refreshTable();
                parentGUI.updateProductTable();
                JOptionPane.showMessageDialog(this,
                    "Restocking successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Restocking failed. Please check the inputs and try again.",
                    "Restock Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error during restocking: " + e.getMessage(),
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void dispose() {
        super.dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelPane = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        restockBtn = new javax.swing.JButton();
        restockProductScrollPane = new javax.swing.JScrollPane();
        restockProductTbl = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        refreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        labelPane.setBackground(new java.awt.Color(0, 204, 51));

        titleLabel.setBackground(new java.awt.Color(0, 204, 51));
        titleLabel.setForeground(new java.awt.Color(0, 204, 51));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restocktitle.png"))); // NOI18N

        javax.swing.GroupLayout labelPaneLayout = new javax.swing.GroupLayout(labelPane);
        labelPane.setLayout(labelPaneLayout);
        labelPaneLayout.setHorizontalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        labelPaneLayout.setVerticalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
                .addGap(26, 26, 26))
        );

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        restockBtn.setText("Restock Selected");
        restockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockBtnActionPerformed(evt);
            }
        });

        restockProductScrollPane.setBorder(null);

        restockProductTbl.getTableHeader().setReorderingAllowed(false);
        restockProductTbl.setAutoCreateRowSorter(true);
        restockProductTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Select", "ID", "Name", "Current Quantity", "Restock Value", "Incoming Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        restockProductScrollPane.setViewportView(restockProductTbl);

        searchPanel.setOpaque(false);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Current Quantity", "Restock Value" }));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTxtField)
                    .addComponent(searchPrmtrBox)))
        );

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(restockProductScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(refreshBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restockProductScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void restockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockBtnActionPerformed
        // Check if any items are selected
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        boolean hasSelectedItems = false;
        for (int i = 0; i < restockProductTblModel.getRowCount(); i++) {
            if ((Boolean) restockProductTblModel.getValueAt(i, 0)) {
                hasSelectedItems = true;
                break;
            }
        }

        if (!hasSelectedItems) {
            JOptionPane.showMessageDialog(this,
                "No items selected for restocking.",
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Show confirmation dialog
        int response = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to restock the selected items?",
            "Confirm Restock",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        // Proceed with restocking only if the user clicks "Yes"
        if (response == JOptionPane.YES_OPTION) {
            restockItems();
        }
    }//GEN-LAST:event_restockBtnActionPerformed

    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        String searchText = searchTxtField.getText().trim();
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) restockProductTbl.getModel();
        int columnIndex = model.findColumn(columnNameToSearch);

        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(this,
                "Invalid column selected for search.",
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (searchText.isEmpty()) {
            tableSorter.setRowFilter(null);
        } else {
            tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        refreshTable();
    }//GEN-LAST:event_refreshBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel labelPane;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton restockBtn;
    private javax.swing.JScrollPane restockProductScrollPane;
    private javax.swing.JTable restockProductTbl;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
