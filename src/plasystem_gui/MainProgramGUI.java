package plasystem_gui;

import plasystem_functions.ProductDataManager;
import plasystem_functions.DatabaseFileChooser;
import plasystem_functions.ProductRowSelector;
import plasystem_functions.ProductData;
import plasystem_functions.TableAlignmentRenderer;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainProgramGUI class represents the main window of the program displaying game data.
 * Initializes and populates the main table with game data upon program start.
 */
public class MainProgramGUI extends JFrame {
    /**
     * The file path retrieved from the database file chooser to access game data.
     * It will be set once and used throughout the program.
     */
    String filePath = DatabaseFileChooser.getFilePath();
    
    // Instance of ProductDataManager to manage data operations
    ProductDataManager dataHandling = new ProductDataManager(filePath);
    
    // List of ProductData retrieved from the file
    LinkedList<ProductData> productList = dataHandling.getList();
    
    /**
     * Constructor initializing the Main Program GUI.
     * Populates the table with existing game data upon program start.
     */
    public MainProgramGUI() {
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        
        /** 
         *  If the Main Program is opened, data will be automatically added to the table
         */
        if (productList != null && !productList.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) plasystemTbl.getModel();
            model.setRowCount(0);

            for (ProductData element : productList) {
                Object[] rowData = {
                    element.getProductID(),
                    element.getProductName(),
                    element.getProductBrand(),
                    element.getProductSize(),
                    element.getProductType(),
                    element.getProductPrice(),
                    element.getProductQuantity(),
                    element.getProductRestockValue() // Ensure Restock Value is added
                };
                model.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database is empty");
        }
        
        new TableAlignmentRenderer(plasystemTbl, 5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_Button_Panel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        transactBtn = new javax.swing.JButton();
        restockBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lowstockBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        plasystemTblScrollPane = new javax.swing.JScrollPane();
        plasystemTbl = new javax.swing.JTable();
        dataPanel = new javax.swing.JPanel();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        Secondary_Buttons = new javax.swing.JPanel();
        userAccountsBtn = new javax.swing.JButton();
        printInventoryBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        transactHistoryBtn = new javax.swing.JButton();
        restockHistoryBtn = new javax.swing.JButton();
        backgroundDesign = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1586, 799));
        setSize(new java.awt.Dimension(1568, 799));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Main_Button_Panel.setBackground(new java.awt.Color(0, 153, 255));
        Main_Button_Panel.setForeground(new java.awt.Color(51, 153, 255));
        Main_Button_Panel.setFocusable(false);
        Main_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/addinventory_button.png"))); // NOI18N
        addBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addBtn.setContentAreaFilled(false);
        addBtn.setInheritsPopupMenu(true);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        Main_Button_Panel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 162, 128, 121));

        transactBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/transactbutton.png"))); // NOI18N
        transactBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        transactBtn.setContentAreaFilled(false);
        transactBtn.setInheritsPopupMenu(true);
        transactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactBtnActionPerformed(evt);
            }
        });
        Main_Button_Panel.add(transactBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 301, 124, 127));

        restockBtn.setBackground(new java.awt.Color(51, 204, 0));
        restockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        restockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restock_button.png"))); // NOI18N
        restockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        restockBtn.setContentAreaFilled(false);
        restockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        restockBtn.setInheritsPopupMenu(true);
        restockBtn.setOpaque(true);
        restockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockBtnActionPerformed(evt);
            }
        });
        Main_Button_Panel.add(restockBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 465, 124, 104));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("|");
        Main_Button_Panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 316, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("|");
        Main_Button_Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 465, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("|");
        Main_Button_Panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 176, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("|");
        Main_Button_Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 314, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("|");
        Main_Button_Panel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 465, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("|");
        Main_Button_Panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 178, -1, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Main_Button_Panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 144, 37, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Main_Button_Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 968, 37, -1));

        jLabel10.setBackground(new java.awt.Color(51, 102, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/plasystem.png"))); // NOI18N
        jLabel10.setInheritsPopupMenu(false);
        jLabel10.setOpaque(true);
        Main_Button_Panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 318, 138));

        lowstockBtn.setBackground(new java.awt.Color(51, 204, 0));
        lowstockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lowstockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/lowstockbutton.png"))); // NOI18N
        lowstockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lowstockBtn.setContentAreaFilled(false);
        lowstockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lowstockBtn.setInheritsPopupMenu(true);
        lowstockBtn.setOpaque(true);
        lowstockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowstockBtnActionPerformed(evt);
            }
        });
        Main_Button_Panel.add(lowstockBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 596, 124, 127));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("|");
        Main_Button_Panel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 615, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("|");
        Main_Button_Panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 616, -1, -1));

        getContentPane().add(Main_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 913));
        setBackground(Color. BLUE);

        mainPanel.setMinimumSize(new java.awt.Dimension(3000, 920));
        mainPanel.setPreferredSize(new java.awt.Dimension(3000, 913));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        plasystemTbl.setAutoCreateRowSorter(true);
        plasystemTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Brand", "Size", "Type", "Price", "Quantity", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        plasystemTbl.getTableHeader().setReorderingAllowed(false);
        plasystemTblScrollPane.setViewportView(plasystemTbl);
        if (plasystemTbl.getColumnModel().getColumnCount() > 0) {
            plasystemTbl.getColumnModel().getColumn(0).setPreferredWidth(25);
            plasystemTbl.getColumnModel().getColumn(4).setPreferredWidth(20);
            plasystemTbl.getColumnModel().getColumn(5).setPreferredWidth(25);
            plasystemTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        mainPanel.add(plasystemTblScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1200, 655));

        dataPanel.setOpaque(false);
        dataPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        dataPanel.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, -1, -1));

        deleteBtn.setBackground(new java.awt.Color(255, 102, 102));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        dataPanel.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 6, -1, -1));

        mainPanel.add(dataPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, 29));

        searchPanel.setOpaque(false);
        searchPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });
        searchPanel.add(searchTxtField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 6, 132, -1));

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Size", "Brand", "Type", "Price", "Quantity" }));
        searchPanel.add(searchPrmtrBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 6, 90, -1));

        mainPanel.add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        Secondary_Buttons.setOpaque(false);
        Secondary_Buttons.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userAccountsBtn.setText("👤 User Accounts");
        userAccountsBtn.setInheritsPopupMenu(true);
        userAccountsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAccountsBtnActionPerformed(evt);
            }
        });
        Secondary_Buttons.add(userAccountsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(899, 6, -1, 46));

        printInventoryBtn.setText("🖨️ Print Inventory");
        printInventoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printInventoryBtnActionPerformed(evt);
            }
        });
        Secondary_Buttons.add(printInventoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1034, 6, 170, 46));

        logoutBtn.setBackground(new java.awt.Color(255, 102, 102));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("↩ Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        Secondary_Buttons.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 138, 46));

        transactHistoryBtn.setText("🔄 Transaction History");
        transactHistoryBtn.setInheritsPopupMenu(true);
        transactHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactHistoryBtnActionPerformed(evt);
            }
        });
        Secondary_Buttons.add(transactHistoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 6, -1, 46));

        restockHistoryBtn.setText("🔄 Restock History");
        restockHistoryBtn.setInheritsPopupMenu(true);
        restockHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockHistoryBtnActionPerformed(evt);
            }
        });
        Secondary_Buttons.add(restockHistoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 6, -1, 46));

        mainPanel.add(Secondary_Buttons, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 720, 1250, -1));

        backgroundDesign.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/bg_mainprogram.png"))); // NOI18N
        backgroundDesign.setText("designLabel");
        backgroundDesign.setMaximumSize(new java.awt.Dimension(3000, 1207));
        backgroundDesign.setMinimumSize(new java.awt.Dimension(3000, 1207));
        backgroundDesign.setPreferredSize(new java.awt.Dimension(3000, 1207));
        mainPanel.add(backgroundDesign, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1410, 920));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, -7, 1390, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method triggered when the 'Transact' button is clicked in the GUI.
     * Initializes and opens a new TransactionGUI to start a transaction process.
     * Passes the gameList, file path, and main data table to the TransactionGUI.
     * @param evt Action event generated by the button click
     */
    private void transactBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactBtnActionPerformed
        /*
        TransactionGUI transactPanel = new TransactionGUI(productList, filePath, plasystemTbl);
        transactPanel.setVisible(true);
        transactPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        */
    }//GEN-LAST:event_transactBtnActionPerformed
    
    /**
     * Method triggered when the 'Add' button is clicked in the GUI.
     * Initializes and opens a new AddDataGUI to add new game data.
     * Passes the gameList, main data table, and file path to the AddDataGUI.
     * @param evt Action event generated by the button click
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        JFrame addProductPanel = new AddProductGUI(productList, plasystemTbl, filePath);
        addProductPanel.setVisible(true);
        addProductPanel.pack();
        addProductPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Method triggered when the 'Edit' button is clicked in the GUI.
     * Retrieves data from the selected row in the main table and opens the EditDataGUI for editing.
     * @param evt Action event generated by the button click
     */
    private void editBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // Get the index of the selected row in the main table
        int editRow = plasystemTbl.getSelectedRow();
        editRow = plasystemTbl.convertRowIndexToModel(editRow);
        
        // Check if a row is selected (row index starts from 0, -1 means no selection)
        if (editRow != -1) {
            // Create an instance of ProductRowSelector to extract data from the selected row
            ProductRowSelector rowSelector = new ProductRowSelector(plasystemTbl);
        
            EditProductGUI editProductPanel = new EditProductGUI(
             editRow, 
             filePath, 
             productList, 
             plasystemTbl,
            // Retrieve data using ProductRowSelector getters
            rowSelector.getTblProductID(),
            rowSelector.getTblName(),
            rowSelector.getTblBrand(),
            rowSelector.getTblSize(),
            rowSelector.getTblType(),
            rowSelector.getTblPrice(),
            rowSelector.getQuantity(),
            rowSelector.getRestockValue() // Update the selected row index
            );

            // Set properties for the editProductPanel frame
            editProductPanel.setVisible(true);
            editProductPanel.pack();
            editProductPanel.setLocationRelativeTo(null);
            editProductPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    /**
     * Method triggered when the 'Delete' button is clicked in the GUI.
     * Deletes the selected row from the main 
     * @param evt Action event generated by the button click
     */
    private void deleteBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // Check if a row is selected in the main table
        if(plasystemTbl.getSelectedRow() != -1){
            // Delete data corresponding to the selected row
            dataHandling.deleteData(plasystemTbl, productList, plasystemTbl.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed
    
    /**
     * Method triggered when text is typed into the search text field.
     * Filters rows in the main table based on the entered search text.
     * @param evt Key event generated when a key is released in the search text field
     */
    private void searchTxtFieldKeyReleased(KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        DefaultTableModel model = (DefaultTableModel) plasystemTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        plasystemTbl.setRowSorter(sorter);
        
        // Get the selected column name from the search parameter box
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString(); // Replace "ColumnName" with the actual column name
        int columnIndex = model.findColumn(columnNameToSearch);
        
        // Apply a row filter based on the entered search text for the selected column
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    private void restockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockBtnActionPerformed
        /*
        JFrame restock = new RestockGUI(productList, plasystemTbl, filePath);
        restock.setVisible(true);
        restock.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        */
    }//GEN-LAST:event_restockBtnActionPerformed

    private void lowstockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowstockBtnActionPerformed
        JFrame lowstockPanel = new LowStockGUI(productList, filePath);
        lowstockPanel.setVisible(true);
        lowstockPanel.pack();
        lowstockPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_lowstockBtnActionPerformed

    private void userAccountsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAccountsBtnActionPerformed
        JFrame userAccountsPanel = new UserAccountsGUI();
        userAccountsPanel.setVisible(true);
        userAccountsPanel.pack();
        userAccountsPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_userAccountsBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        JFrame launchPanel = new LaunchPanelGUI(); // Create an instance of the main program GUI
        launchPanel.setVisible(true); // Set the main program frame visible
        launchPanel.pack();
        this.dispose(); // Dispose of the current frame (LaunchPanelGUI)
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void transactHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactHistoryBtnActionPerformed
        JFrame transactionHistoryPanel = new TransactionHistoryGUI();
        transactionHistoryPanel.setVisible(true);
        transactionHistoryPanel.pack();
        transactionHistoryPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_transactHistoryBtnActionPerformed

    private void restockHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockHistoryBtnActionPerformed
        JFrame restockHistoryPanel = new RestockHistoryGUI();
        restockHistoryPanel.setVisible(true);
        restockHistoryPanel.pack();
        restockHistoryPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_restockHistoryBtnActionPerformed

    private void printInventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printInventoryBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_printInventoryBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Main_Button_Panel;
    private javax.swing.JPanel Secondary_Buttons;
    private javax.swing.JButton addBtn;
    private javax.swing.JLabel backgroundDesign;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton lowstockBtn;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable plasystemTbl;
    private javax.swing.JScrollPane plasystemTblScrollPane;
    private javax.swing.JButton printInventoryBtn;
    private javax.swing.JButton restockBtn;
    private javax.swing.JButton restockHistoryBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JButton transactBtn;
    private javax.swing.JButton transactHistoryBtn;
    private javax.swing.JButton userAccountsBtn;
    // End of variables declaration//GEN-END:variables
}
