package plasystem_gui;

import java.awt.HeadlessException;
import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

/**
 * A graphical user interface (GUI) window for restocking products.
 * Displays products needing restock, allows selection and quantity input, and processes restocking operations.
 */
public class RestockProductGUI extends JFrame {
    /** The ProductDataManager instance for managing product data operations. */
    private ProductDataManager productDataModel;
    /** The RestockDataManager instance for managing restock data operations. */
    private RestockDataManager restockDataModel;
    /** The parent MainProgramGUI instance for updating the product table after restocking. */
    private MainProgramGUI parentGUI;
    /** The ErrorValueHandling instance for validating input data. */
    private ErrorValueHandling dataValidator;
    /** The TableRowSorter for sorting and filtering the restock product table. */
    private TableRowSorter<DefaultTableModel> tableSorter;
    
    /**
     * Default constructor that initializes the RestockProductGUI.
     * Centers the window and sets up the form components.
     */
    public RestockProductGUI(){
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the RestockProductGUI with necessary dependencies.
     * Sets up the form components, centers the window, and initializes the table with product data.
     *
     * @param mainGUI The parent MainProgramGUI to update its table
     * @param productDataManager The ProductDataManager for product data operations
     * @param restockDataManager The RestockDataManager for restock data operations
     */
    public RestockProductGUI(MainProgramGUI mainGUI, ProductDataManager productDataManager, RestockDataManager restockDataManager) {
        // Assign the parent GUI for table updates
        this.parentGUI = mainGUI;
        // Assign the product data manager
        this.productDataModel = productDataManager;
        // Assign the restock data manager
        this.restockDataModel = restockDataManager;
        // Initialize the data validator
        this.dataValidator = new ErrorValueHandling();
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Set up the table with product data
        initializeTable();
    }
    
    /**
     * Initializes the restock product table and its sorter.
     */
    private void initializeTable() {
        // Populate the table with products needing restock
        populateTable();
        // Apply custom renderer for column alignment
        applyTableRenderer();
        // Initialize the table sorter
        tableSorter = new TableRowSorter<>((DefaultTableModel) restockProductTbl.getModel());
        // Apply the sorter to the table
        restockProductTbl.setRowSorter(tableSorter);
    }
    
    /**
     * Populates the table with products where quantity is less than or equal to the restock value.
     */
    private void populateTable() {
        // Get the table model
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        // Clear existing rows
        restockProductTblModel.setRowCount(0);
        // Get the product list from the data manager
        List<ProductData> productList = productDataModel.getList();
        // Iterate through the product list
        for (ProductData product : productList) {
            // Check if the product needs restocking
            if (product.getProductQuantity() <= product.getProductRestockValue()) {
                // Add the product to the table
                restockProductTblModel.addRow(new Object[]{
                    false, // Select checkbox
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue(),
                    0 // Incoming quantity
                });
            }
        }
    }
    
    /**
     * Applies a custom table renderer to align columns appropriately.
     */
    private void applyTableRenderer() {
        // Apply the custom renderer to the table
        new RestockTableRenderer(restockProductTbl);
    }
    
    /**
     * Refreshes the table with the latest data, resetting selections and quantities.
     */
    private void refreshTable() {
        // Repopulate the table with current data
        populateTable();
        // Reapply the custom renderer
        applyTableRenderer();
        // Reapply the current search filter if the search field is not empty
        if (!searchTxtField.getText().trim().isEmpty()) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * Validates and processes restocking for selected products as a single restock event.
     */
    private void restockItems() {
        // Get the table model
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        // List to store items to restock
        List<Map<String, Object>> items = new ArrayList<>();
        
        // Validate all selected items
        for (int i = 0; i < restockProductTblModel.getRowCount(); i++) {
            // Check if the item is selected
            boolean selected = (Boolean) restockProductTblModel.getValueAt(i, 0);
            if (selected) {
                // Get the incoming quantity
                Object incomingQtyObj = restockProductTblModel.getValueAt(i, 5);
                // Validate that the quantity is not empty
                if (incomingQtyObj == null || incomingQtyObj.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "' cannot be empty.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Convert the quantity to string
                String incomingQtyStr = incomingQtyObj.toString();
                // Validate that the quantity is a valid integer
                if (!dataValidator.isInteger(incomingQtyStr)) {
                    JOptionPane.showMessageDialog(this,
                        "Invalid incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "'. Must be a non-negative integer.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Parse the quantity
                int incomingQty = Integer.parseInt(incomingQtyStr);
                // Validate that the quantity is positive
                if (incomingQty <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "Incoming quantity for product '" + restockProductTblModel.getValueAt(i, 2) + "' must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Get the product ID
                int productId = (Integer) restockProductTblModel.getValueAt(i, 1);
                // Find the corresponding product
                ProductData product = productDataModel.getList().stream()
                    .filter(p -> p.getProductId() == productId)
                    .findFirst()
                    .orElse(null);
                // Validate that the product exists
                if (product == null) {
                    JOptionPane.showMessageDialog(this,
                        "Product with ID " + productId + " not found.",
                        "Restock Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Create a map for the item
                Map<String, Object> item = new HashMap<>();
                item.put("product", product);
                item.put("quantity", incomingQty);
                // Add the item to the list
                items.add(item);
            }
        }

        // Check if any items were selected
        if (items.isEmpty()) {
            // Display error message if no items are selected
            JOptionPane.showMessageDialog(this,
                "No items selected or no valid quantities provided.",
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Process restocking
        try {
            // Attempt to restock multiple products
            boolean restockSuccess = restockDataModel.restockMultipleProducts(items);
            if (restockSuccess) {
                // Refresh the table
                refreshTable();
                // Update the parent GUI's product table
                parentGUI.updateProductTable();
                // Display success message
                JOptionPane.showMessageDialog(this,
                    "Restocking successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display error message if restocking fails
                JOptionPane.showMessageDialog(this,
                    "Restocking failed. Please check the inputs and try again.",
                    "Restock Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            // Display error message for any exceptions
            JOptionPane.showMessageDialog(this,
                "Error during restocking: " + e.getMessage(),
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Disposes of the window.
     */
    @Override
    public void dispose() {
        // Call the superclass dispose method to close the window
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
    
    /**
     * Handles the action when the "Cancel" button is clicked.
     * Closes the window without saving changes.
     *
     * @param evt The ActionEvent triggered by clicking the "Cancel" button
     */
    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // Close the window
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    /**
     * Handles the action when the "Restock Selected" button is clicked.
     * Validates selections and processes restocking after user confirmation.
     *
     * @param evt The ActionEvent triggered by clicking the "Restock Selected" button
     */
    private void restockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockBtnActionPerformed
        // Get the table model
        DefaultTableModel restockProductTblModel = (DefaultTableModel) restockProductTbl.getModel();
        // Check for selected items
        boolean hasSelectedItems = false;
        for (int i = 0; i < restockProductTblModel.getRowCount(); i++) {
            if ((Boolean) restockProductTblModel.getValueAt(i, 0)) {
                hasSelectedItems = true;
                break;
            }
        }

        // Validate that at least one item is selected
        if (!hasSelectedItems) {
            // Display error message if no items are selected
            JOptionPane.showMessageDialog(this,
                "No items selected for restocking.",
                "Restock Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt user to confirm restocking
        int response = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to restock the selected items?",
            "Confirm Restock",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        // Proceed with restocking if user confirms
        if (response == JOptionPane.YES_OPTION) {
            // Process the restocking operation
            restockItems();
        }
    }//GEN-LAST:event_restockBtnActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the restock product table based on the search text and selected column.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Retrieve and trim the search text
        String searchText = searchTxtField.getText().trim();
        // Get the selected column name for searching
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) restockProductTbl.getModel();
        // Find the index of the selected column
        int columnIndex = model.findColumn(columnNameToSearch);

        // Validate the column index
        if (columnIndex == -1) {
            // Display error message if the column is invalid
            JOptionPane.showMessageDialog(this,
                "Invalid column selected for search.",
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Apply the row filter
        if (searchText.isEmpty()) {
            // Clear the filter if the search text is empty
            tableSorter.setRowFilter(null);
        } else {
            // Apply a case-insensitive regex filter to the selected column
            tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased
    
    /**
     * Handles the action when the "Refresh" button is clicked.
     * Updates the table with the latest data.
     *
     * @param evt The ActionEvent triggered by clicking the "Refresh" button
     */
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // Refresh the table with the latest data
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
