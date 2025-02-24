package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.DatabaseFileChooser;
import plasystem_functions.TableRowSelector;
import plasystem_functions.TableAlignmentRenderer;
import plasystem_functions.GameData;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
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
    
    // List of GameData retrieved from the file
    LinkedList<GameData> gameList = dataHandling.getList();
    
    /**
     * Constructor initializing the Main Program GUI.
     * Populates the table with existing game data upon program start.
     */
    public MainProgramGUI() {
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        new TableAlignmentRenderer(datamineTbl); // Apply table alignment
        
        /** 
         *  If the Main Program is opened, data will be automatically added to the 
         */
        if (gameList != null && !gameList.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) datamineTbl.getModel();
            model.setRowCount(0); // Clear the existing table content
            
            // Loop through the gameList and add each element's details to the table
            for(GameData element : gameList){
                Object[] rowData = { 
                    element.getProductID(),
                    element.getQuantity(),
                    element.getPrice(),
                    element.getName(),
                    element.getGenre(),
                    element.getPlatform(),
                    element.getReleaseYear(),
                    element.getPublisher(),
                };
                model.addRow(rowData); // Add each row of data to the table model
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database is empty"); // Show a message if the database is empty
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

        menuBar1 = new java.awt.MenuBar();
        datamineTblScrollPane = new javax.swing.JScrollPane();
        datamineTbl = new javax.swing.JTable();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        dataPanel = new javax.swing.JPanel();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        transactBtn = new javax.swing.JButton();
        datamineLogoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        datamineTbl.setAutoCreateRowSorter(true);
        datamineTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Quantity", "Price", "Name", "Genre", "Platform", "Year", "Publisher"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        datamineTbl.getTableHeader().setReorderingAllowed(false);
        datamineTblScrollPane.setViewportView(datamineTbl);
        if (datamineTbl.getColumnModel().getColumnCount() > 0) {
            datamineTbl.getColumnModel().getColumn(0).setPreferredWidth(25);
            datamineTbl.getColumnModel().getColumn(1).setPreferredWidth(20);
            datamineTbl.getColumnModel().getColumn(2).setPreferredWidth(25);
            datamineTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Genre", "Platform", "Publisher", "Year", "Price", "Quantity" }));

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

        addBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datamine_main/datamineAdd.png"))); // NOI18N
        addBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        transactBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datamine_main/datamineTransact.png"))); // NOI18N
        transactBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        transactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactBtnActionPerformed(evt);
            }
        });

        datamineLogoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/datamine_main/datamineTitle.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(datamineTblScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 683, Short.MAX_VALUE)
                        .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datamineLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)
                        .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(datamineLogoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datamineTblScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method triggered when the 'Transact' button is clicked in the GUI.
     * Initializes and opens a new TransactionGUI to start a transaction process.
     * Passes the gameList, file path, and main data table to the TransactionGUI.
     * @param evt Action event generated by the button click
     */
    private void transactBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactBtnActionPerformed
        TransactionGUI transactPanel = new TransactionGUI(gameList, filePath, datamineTbl);
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
        JFrame addPanel = new AddDataGUI(gameList, datamineTbl, filePath);
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
        int selectedRow = datamineTbl.getSelectedRow();
        
        // Check if a row is selected (row index starts from 0, -1 means no selection)
        if (selectedRow != -1) {
            // Create an instance of TableRowSelector to extract data from the selected row
            TableRowSelector rowSelector = new TableRowSelector(datamineTbl);
        
            // Retrieve data using TableRowSelector getters
            String tblProductID = rowSelector.getTblProductID();
            String tblQuantity = rowSelector.getQuantity();
            String tblPrice = rowSelector.getTblPrice();
            String tblName = rowSelector.getTblName();
            String tblGenre = rowSelector.getTblGenre();
            String tblPlatform = rowSelector.getTblPlatform();
            String tblYear = rowSelector.getTblYear();
            String tblPublisher = rowSelector.getTblPublsher();
            selectedRow = rowSelector.getRow(); // Update the selected row index

            // Pass the retrieved data to the EditDataGUI for editing
            EditDataGUI edit = new EditDataGUI(
                    selectedRow, 
                    filePath, 
                    gameList, 
                    datamineTbl,
                    tblProductID,
                    tblQuantity,
                    tblPrice, 
                    tblName, 
                    tblGenre, 
                    tblPlatform, 
                    tblYear, 
                    tblPublisher
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
        if(datamineTbl.getSelectedRow() != -1){
            // Delete data corresponding to the selected row
            dataHandling.deleteData(datamineTbl, gameList, datamineTbl.getSelectedRow());
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
        DefaultTableModel model = (DefaultTableModel) datamineTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        datamineTbl.setRowSorter(sorter);
        
        // Get the selected column name from the search parameter box
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString(); // Replace "ColumnName" with the actual column name
        int columnIndex = model.findColumn(columnNameToSearch);
        
        // Apply a row filter based on the entered search text for the selected column
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JLabel datamineLogoLabel;
    private javax.swing.JTable datamineTbl;
    private javax.swing.JScrollPane datamineTblScrollPane;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JButton transactBtn;
    // End of variables declaration//GEN-END:variables
}