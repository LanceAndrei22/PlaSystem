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
    private ErrorValueHandling errorHandler;
    private TableRowSorter<DefaultTableModel> sorter;
    
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
        this.errorHandler = new ErrorValueHandling();
        initComponents();
        setLocationRelativeTo(null);
        initializeTable();
    }
    
    /**
     * Initializes the table and sorter.
     */
    private void initializeTable() {
        populateTable();
        applyTableRenderer();
        sorter = new TableRowSorter<>((DefaultTableModel) restockTable.getModel());
        restockTable.setRowSorter(sorter);
    }
    
    /**
     * Populates the restock table with products where quantity is less than or equal to restock value.
     */
    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        model.setRowCount(0);
        List<ProductData> productList = productDataManager.getList();
        for (ProductData product : productList) {
            if (product.getProductQuantity() <= product.getProductRestockValue()) {
                model.addRow(new Object[]{
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
        new RestockTableRenderer(restockTable);
    }
    
    /**
     * Refreshes the table with the latest database state, resetting selections and quantities.
     */
    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        model.setRowCount(0); // Clear table
        List<ProductData> productList = productDataManager.getList();
        for (ProductData product : productList) {
            if (product.getProductQuantity() <= product.getProductRestockValue()) {
                model.addRow(new Object[]{
                    false, // Select
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue(),
                    0 // Incoming Qty
                });
            }
        }

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
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        List<Map<String, Object>> items = new ArrayList<>();
        
        // Validate all selected items using view indices
        for (int i = 0; i < model.getRowCount(); i++) {
            boolean selected = (Boolean) model.getValueAt(i, 0);
            if (selected) {
                Object incomingQtyObj = model.getValueAt(i, 5);
                if (incomingQtyObj == null || incomingQtyObj.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + model.getValueAt(i, 2) + "' cannot be empty.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String incomingQtyStr = incomingQtyObj.toString();
                if (!errorHandler.isInteger(incomingQtyStr)) {
                    JOptionPane.showMessageDialog(this,
                        "Invalid incoming quantity for product '" + model.getValueAt(i, 2) + "'. Must be a non-negative integer.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int incomingQty = Integer.parseInt(incomingQtyStr);
                if (incomingQty <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + model.getValueAt(i, 2) + "' must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int productId = (Integer) model.getValueAt(i, 1);
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
            boolean success = restockDataManager.restockMultipleProducts(items);
            if (success) {
                refreshTable();
                parentGUI.refreshTable();
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
        Titlelabel = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        restockBtn = new javax.swing.JButton();
        restockScrollPane = new javax.swing.JScrollPane();
        restockTable = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        refreshBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        labelPane.setBackground(new java.awt.Color(0, 204, 51));

        Titlelabel.setBackground(new java.awt.Color(0, 204, 51));
        Titlelabel.setForeground(new java.awt.Color(0, 204, 51));
        Titlelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titlelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restocktitle.png"))); // NOI18N

        javax.swing.GroupLayout labelPaneLayout = new javax.swing.GroupLayout(labelPane);
        labelPane.setLayout(labelPaneLayout);
        labelPaneLayout.setHorizontalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titlelabel, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );
        labelPaneLayout.setVerticalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
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

        restockScrollPane.setBorder(null);

        restockTable.setAutoCreateRowSorter(true);
        restockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        restockScrollPane.setViewportView(restockTable);

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
                        .addComponent(restockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE))
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
                .addComponent(restockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
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
        restockItems();
    }//GEN-LAST:event_restockBtnActionPerformed

    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        String searchText = searchTxtField.getText().trim();
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        int columnIndex = model.findColumn(columnNameToSearch);

        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(this,
                "Invalid column selected for search.",
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        refreshTable();
    }//GEN-LAST:event_refreshBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titlelabel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel labelPane;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton restockBtn;
    private javax.swing.JScrollPane restockScrollPane;
    private javax.swing.JTable restockTable;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    // End of variables declaration//GEN-END:variables
}
