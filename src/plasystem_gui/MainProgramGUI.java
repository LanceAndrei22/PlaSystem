package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.DatabaseFileChooser;
import plasystem_functions.TableRowSelector;
import plasystem_functions.TableAlignmentRenderer;
import plasystem_functions.ProductData;
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
    
    // Instance of DataHandling to manage data operations
    DataHandling dataHandling = new DataHandling(filePath);
    
    // List of ProductData retrieved from the file
    LinkedList<ProductData> productList = dataHandling.getList();
    
    /**
     * Constructor initializing the Main Program GUI.
     * Populates the table with existing game data upon program start.
     */
    public MainProgramGUI() {
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        new TableAlignmentRenderer(plasystemTbl); // Apply table alignment
        
        /** 
         *  If the Main Program is opened, data will be automatically added to the 
         */
        if (productList != null && !productList.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) plasystemTbl.getModel();
            model.setRowCount(0);

            for (ProductData element : productList) {
                Object[] rowData = {
                    element.getProductID(),
                    element.getProductQuantity(),
                    element.getProductPrice(),
                    element.getProductName(),
                    element.getProductSize(),
                    element.getProductBrand(),
                    element.getProductType(),
                    element.getProductRestockValue() // Ensure Restock Value is added
                };
                model.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database is empty");
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

        plasystemTblScrollPane = new javax.swing.JScrollPane();
        plasystemTbl = new javax.swing.JTable();
        dataPanel = new javax.swing.JPanel();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        transactBtn = new javax.swing.JButton();
        rstockBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1210, 650));
        setSize(new java.awt.Dimension(2600, 1300));

        plasystemTbl.setAutoCreateRowSorter(true);
        plasystemTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Quantity", "Price", "Name", "Size", "Brand", "Type", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
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
            plasystemTbl.getColumnModel().getColumn(1).setPreferredWidth(20);
            plasystemTbl.getColumnModel().getColumn(2).setPreferredWidth(25);
            plasystemTbl.getColumnModel().getColumn(2).setHeaderValue("Price");
            plasystemTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        dataPanel.setOpaque(false);

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn)
                .addContainerGap())
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(deleteBtn))
                .addContainerGap())
        );

        searchPanel.setOpaque(false);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Size", "Brand", "Type", "Price", "Quantity" }));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/SOFTWARE (1000 x 500 px) (11).png"))); // NOI18N

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1209, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel1.getAccessibleContext().setAccessibleName("");

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setForeground(new java.awt.Color(51, 153, 255));

        addBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/SOFTWARE (500 x 500 px) (1).png"))); // NOI18N
        addBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addBtn.setContentAreaFilled(false);
        addBtn.setInheritsPopupMenu(true);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        transactBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/SOFTWARE (500 x 500 px) (2).png"))); // NOI18N
        transactBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        transactBtn.setContentAreaFilled(false);
        transactBtn.setInheritsPopupMenu(true);
        transactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactBtnActionPerformed(evt);
            }
        });

        rstockBtn.setBackground(new java.awt.Color(51, 204, 0));
        rstockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rstockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/restock_button.png"))); // NOI18N
        rstockBtn.setText("Re-stock");
        rstockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rstockBtn.setContentAreaFilled(false);
        rstockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rstockBtn.setInheritsPopupMenu(true);
        rstockBtn.setOpaque(true);
        rstockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rstockBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(">");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(">");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText(">");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("<");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("<");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("<");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setBackground(new java.awt.Color(51, 102, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/plasystem.png"))); // NOI18N
        jLabel10.setInheritsPopupMenu(false);
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))))
                .addContainerGap(64, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(49, 49, 49)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(77, 77, 77)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGap(399, 399, 399)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(462, 462, 462)
                        .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(plasystemTblScrollPane))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDesktopPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plasystemTblScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1162, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        setBackground(Color. BLUE);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method triggered when the 'Transact' button is clicked in the GUI.
     * Initializes and opens a new TransactionGUI to start a transaction process.
     * Passes the gameList, file path, and main data table to the TransactionGUI.
     * @param evt Action event generated by the button click
     */
    private void transactBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactBtnActionPerformed
        TransactionGUI transactPanel = new TransactionGUI(productList, filePath, plasystemTbl);
        transactPanel.setVisible(true);
        transactPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_transactBtnActionPerformed

    
    /**
     * Method triggered when the 'Add' button is clicked in the GUI.
     * Initializes and opens a new AddDataGUI to add new game data.
     * Passes the gameList, main data table, and file path to the AddDataGUI.
     * @param evt Action event generated by the button click
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        JFrame addPanel = new AddDataGUI(productList, plasystemTbl, filePath);
        addPanel.setVisible(true);
        addPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Method triggered when the 'Edit' button is clicked in the GUI.
     * Retrieves data from the selected row in the main table and opens the EditDataGUI for editing.
     * @param evt Action event generated by the button click
     */
    private void editBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // Get the index of the selected row in the main table
        int selectedRow = plasystemTbl.getSelectedRow();
        
        // Check if a row is selected (row index starts from 0, -1 means no selection)
        if (selectedRow != -1) {
            // Create an instance of TableRowSelector to extract data from the selected row
            TableRowSelector rowSelector = new TableRowSelector(plasystemTbl);
        
            EditDataGUI edit = new EditDataGUI(
             selectedRow, 
             filePath, 
             productList, 
             plasystemTbl,
            // Retrieve data using TableRowSelector getters
            rowSelector.getTblProductID(),
            rowSelector.getQuantity(),
            rowSelector.getTblPrice(),
            rowSelector.getTblName(),
            rowSelector.getTblSize(),
            rowSelector.getTblBrand(),
            rowSelector.getTblType(),
            rowSelector.getRestockValue() // Update the selected row index
            );

            // Set properties for the edit frame
            edit.setVisible(true);
            edit.pack();
            edit.setLocationRelativeTo(null);
            edit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    private void rstockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rstockBtnActionPerformed
        JFrame panel = new RestockGUI(productList, plasystemTbl, filePath);
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_rstockBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable plasystemTbl;
    private javax.swing.JScrollPane plasystemTblScrollPane;
    private javax.swing.JButton rstockBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JButton transactBtn;
    // End of variables declaration//GEN-END:variables
}
