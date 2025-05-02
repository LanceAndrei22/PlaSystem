package plasystem_gui;

import plasystem_functions.ProductDataManager;
import plasystem_functions.ProductData;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * A graphical user interface (GUI) window for displaying products with low stock levels.
 * This class provides a table showing products whose quantity is at or below their restock value,
 * with search functionality and automatic table refresh.
 */
public class LowStockGUI extends JFrame{
    /** The TableRowSorter used to sort and filter the low stock table. */
    private TableRowSorter<DefaultTableModel> tableSorter;
    /** The list of ProductData objects containing product details. */
    private List<ProductData> productList;
    /** The Timer used to periodically refresh the low stock table. */
    private Timer refreshTimer;
    
    /**
     * Default constructor that initializes the LowStockGUI.
     * Centers the window on the screen and sets up the form components.
     */
    public LowStockGUI(){
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the LowStockGUI with product data.
     * Sets up the form components, centers the window, configures the table, and starts the refresh timer.
     *
     * @param productDataModel The ProductDataManager instance providing access to product data
     */
    public LowStockGUI(ProductDataManager productDataModel) {
        // Load the product list from the data model
        this.productList = productDataModel.getList();

        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);

        // Configure the table model and sorter
        DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
        tableSorter = new TableRowSorter<>(model);
        lowstockTable.setRowSorter(tableSorter);
        // Clear the table to prepare for data population
        model.setRowCount(0);
        
        // Align the Product ID column to the left
        leftAlignProdID();

        // Populate the table with low stock products
        refreshLowStockTable();
        // Start the timer to refresh the table periodically
        startRefreshTimer();
    }
    
    /**
     * Starts a timer to refresh the low stock table every second.
     */
    private void startRefreshTimer() {
        // Create a timer that triggers every 1000ms (1 second)
        refreshTimer = new Timer(1000, (ActionEvent e) -> {
            // Refresh the table with updated low stock data
            refreshLowStockTable();
        });
        // Start the timer
        refreshTimer.start();
    }
    
    /**
     * Stops the refresh timer and disposes of the window when closed.
     */
    @Override
    public void dispose() {
        // Check if the timer exists and stop it
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
        // Call the superclass dispose method to close the window
        super.dispose();
    }
    
    /**
     * Configures the Product ID column to be left-aligned in the table.
     */
    private void leftAlignProdID() {
        // Create a renderer for left-aligned text
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        // Set the alignment to left
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        // Apply the renderer to the Product ID column (index 0)
        lowstockTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
    }
    
    /**
     * Refreshes the low stock table by populating it with products whose quantity
     * is at or below their restock value.
     */
    private void refreshLowStockTable() {
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
        // Clear existing rows
        model.setRowCount(0);
        // Iterate through the product list
        for (ProductData product : this.productList) {
            // Check if the product's quantity is at or below its restock value
            if (product.getProductQuantity() <= product.getProductRestockValue()) {
                // Add the product to the table
                model.addRow(new Object[]{
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue()
                });
            }
        }
        // Reapply the current search filter if the search field is not empty
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lowstockScrollPane = new javax.swing.JScrollPane();
        lowstockTable = new javax.swing.JTable();
        Design = new javax.swing.JLabel();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lowstockTable.setAutoCreateRowSorter(true);
        lowstockTable.getTableHeader().setReorderingAllowed(false);
        lowstockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Quantity", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lowstockScrollPane.setViewportView(lowstockTable);

        Design.setBackground(new java.awt.Color(51, 51, 51));
        Design.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Design.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/lowstocktitle.png"))); // NOI18N
        Design.setOpaque(true);

        searchPanel.setOpaque(false);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Quantity", "Restock Value" }));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lowstockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 74, Short.MAX_VALUE)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowstockScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 370, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the key release event in the search text field.
     * Filters the table based on the search text and selected column.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Retrieve and trim the search text
        String searchText = searchTxtField.getText().trim();
        // Get the selected column name for searching
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        // Get the table model
        DefaultTableModel model = (DefaultTableModel)lowstockTable.getModel();

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

        // Apply the row filter based on the search text
        if (searchText.isEmpty()) {
            // Clear the filter if the search text is empty
            tableSorter.setRowFilter(null);
        } else {
            // Apply a case-insensitive regex filter to the selected column
            tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JScrollPane lowstockScrollPane;
    private javax.swing.JTable lowstockTable;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    // End of variables declaration//GEN-END:variables
}