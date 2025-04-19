package plasystem_gui;

import plasystem_functions.ProductDataManager;
import plasystem_functions.ProductData;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LowStockGUI extends javax.swing.JFrame{
    
    private TableRowSorter<DefaultTableModel> sorter; // Store the sorter for reuse
    private ProductDataManager dataHandler;
    private List<ProductData> productList;
    private MainProgramGUI parent;
    private Timer refreshTimer; // Timer for dynamic updates
    
    public LowStockGUI(){
        initComponents();
        setLocationRelativeTo(null);
    }

    public LowStockGUI(MainProgramGUI parent, ProductDataManager dataHandler) {
        this.parent = parent;
        this.dataHandler = dataHandler;
        this.productList = dataHandler.getList(); // Load product list

        initComponents();
        setLocationRelativeTo(null);

        DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
        sorter = new TableRowSorter<>(model);
        lowstockTable.setRowSorter(sorter);
        model.setRowCount(0); // Clear table
        
        leftAlignProdID();

        refreshLowStockTable();
        startRefreshTimer();
        
        // Unregister from parent when window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.removeChildGUI(LowStockGUI.this);
            }
        });
    }
    
    // Start a timer to refresh the table every 5 seconds
    private void startRefreshTimer() {
        refreshTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshLowStockTable();
            }
        });
        refreshTimer.start();
    }
    
    // Stop the timer when the window is closed
    @Override
    public void dispose() {
        if (refreshTimer != null) {
            refreshTimer.stop();
        }
        super.dispose();
    }
    
    private void leftAlignProdID() {
        // Left-align the Product ID column (index 0)
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(JLabel.LEFT);
        lowstockTable.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
    }
    
    private void refreshLowStockTable() {
        // Refresh productList from ProductDataManager
        this.productList = dataHandler.getList();
        DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
        model.setRowCount(0); // Clear table
        for (ProductData product : this.productList) {
            if (product.getProductQuantity() < product.getProductRestockValue()) {
                model.addRow(new Object[]{
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue()
                });
            }
        }
        // Reapply the current filter if any
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
    }
            
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

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Quantity", "Restock Value", "Quantity" }));

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

    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        String searchText = searchTxtField.getText().trim();
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel)lowstockTable.getModel();

        // Find the column index
        int columnIndex = model.findColumn(columnNameToSearch);

        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(this,
                "Invalid column selected for search.",
                "Search Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Apply the row filter
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null); // Clear filter if search text is empty
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
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