package plasystem_gui;

import plasystem_functions.*;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainProgramGUI class represents the main window of the program displaying product data.
 */
public class MainProgramGUI extends JFrame {
    // Instance of ProductDataManager to manage data operations
    private final ProductDataManager productDataModel = new ProductDataManager();
    
    // List of ProductData retrieved from the database
    private final List<ProductData> productList = productDataModel.getList();
    
    // Instance of RestockDataManager
    private final RestockDataManager restockDataModel = new RestockDataManager(productDataModel);
    
    // Instance of TransactionDataManager
    private final TransactionDataManager transactionDataModel = new TransactionDataManager(productDataModel);
    
    // Instance of UserAccountDataManager
    private UserAccountDataManager userAccountDataModel;
    
    // List to track open child GUIs
    private final List<JFrame> childGUIs = new ArrayList<>();
    
    // Map to track instances of active GUIs
    private final Map<Class<? extends JFrame>, JFrame> activeGUIs = new HashMap<>();
    
    
    /**
     * Default constructor for the MainProgramGUI.
     */
    public MainProgramGUI() {
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
    }
    
    /**
     * Constructor initializing the Main Program GUI.
     * Populates the table with existing game data upon program start.
     * @param userAccountDataHandling 
     */
    public MainProgramGUI(UserAccountDataManager userAccountDataHandling) {
        this.userAccountDataModel = userAccountDataHandling;
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        
        // Populate the table with product data from the database
        productDataModel.updateTable(productTbl);
        
        // Apply dynamic column formatting and sizing
        new ProductTableRenderer(productTbl, productList, 1200); // 1200 is the table width
        
        if (productList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database is empty", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Update greetings label with username and userRole
        String username = userAccountDataHandling.getLoggedInUsername();
        String userRole = userAccountDataHandling.getLoggedInRole();
        if (username != null && userRole != null) {
            greetingsLabel.setText("Welcome To PlaSystem! " + username + ", " + userRole);
        }
        
        enableRoleControl();
    }
        
    // Method to add a child GUI to the tracking list
    public void addChildGUI(JFrame child) {
        childGUIs.add(child);
    }

    // Method to remove a child GUI from the tracking list
    public void removeChildGUI(JFrame child) {
        childGUIs.remove(child);
    }
    
    /**
     * Launches or focuses a single instance of a GUI.
     *
     * @param guiClass The class of the GUI to launch.
     * @param creator  A lambda to create a new instance of the GUI if needed.
     * @return The GUI instance.
     */
    private <T extends JFrame> T launchSingleInstance(Class<T> guiClass, Supplier<T> creator) {
        JFrame existingInstance = activeGUIs.get(guiClass);
        if (existingInstance != null && !existingInstance.isDisplayable()) {
            activeGUIs.remove(guiClass); // Remove disposed instance
            existingInstance = null;
        }

        if (existingInstance != null) {
            JOptionPane.showMessageDialog(
                existingInstance,
                "Only one instance can be present.",
                "Instance Warning",
                JOptionPane.WARNING_MESSAGE
            );
            existingInstance.requestFocus();
            existingInstance.setVisible(true);
            return guiClass.cast(existingInstance);
        }

        T newInstance = creator.get();
        newInstance.setVisible(true);
        newInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newInstance.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                activeGUIs.remove(guiClass);
                removeChildGUI(newInstance);
            }
        });
        activeGUIs.put(guiClass, newInstance);
        addChildGUI(newInstance);
        return newInstance;
    }
    
    // Method to refresh the table after updates
    public void updateProductTable() {
        productDataModel.updateTable(productTbl);
        
        // Apply dynamic column formatting and sizing
        new ProductTableRenderer(productTbl, productList, 1200); // 1200 is the table width
        
        // Reapply the current filter if any
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    // Method to do Role Based Access Control
    private void enableRoleControl() {
        // Apply userRole-based access control
        RoleBasedAccessControl.applyRolePermissions(this, userAccountDataModel.getLoggedInRole());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainBtnPanel = new javax.swing.JPanel();
        addProductBtn = new javax.swing.JButton();
        transactionBtn = new javax.swing.JButton();
        restockProductBtn = new javax.swing.JButton();
        barDesign4 = new javax.swing.JLabel();
        barDesign7 = new javax.swing.JLabel();
        barDesign2 = new javax.swing.JLabel();
        barDesign3 = new javax.swing.JLabel();
        barDesign6 = new javax.swing.JLabel();
        barDesign1 = new javax.swing.JLabel();
        logoBackground = new javax.swing.JLabel();
        lowStockBtn = new javax.swing.JButton();
        barDesign9 = new javax.swing.JLabel();
        barDesign8 = new javax.swing.JLabel();
        mainInfoPanel = new javax.swing.JPanel();
        productTblScrollPane = new javax.swing.JScrollPane();
        productTbl = new javax.swing.JTable();
        editDeleteProductPanel = new javax.swing.JPanel();
        editProductBtn = new javax.swing.JButton();
        deleteProductBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        secondaryBtnPanel = new javax.swing.JPanel();
        userAccountsBtn = new javax.swing.JButton();
        exportInventoryBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        transactHistoryBtn = new javax.swing.JButton();
        restockHistoryBtn = new javax.swing.JButton();
        greetingsLabel = new javax.swing.JLabel();
        mainBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1586, 799));
        setSize(new java.awt.Dimension(1568, 799));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mainBtnPanel.setBackground(new java.awt.Color(0, 153, 255));
        mainBtnPanel.setForeground(new java.awt.Color(51, 153, 255));
        mainBtnPanel.setFocusable(false);
        mainBtnPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addProductBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addProductBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/addinventory_button.png"))); // NOI18N
        addProductBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addProductBtn.setContentAreaFilled(false);
        addProductBtn.setInheritsPopupMenu(true);
        addProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductBtnActionPerformed(evt);
            }
        });
        mainBtnPanel.add(addProductBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 162, 128, 121));

        transactionBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/transactbutton.png"))); // NOI18N
        transactionBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        transactionBtn.setContentAreaFilled(false);
        transactionBtn.setInheritsPopupMenu(true);
        transactionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactionBtnActionPerformed(evt);
            }
        });
        mainBtnPanel.add(transactionBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 301, 124, 127));

        restockProductBtn.setBackground(new java.awt.Color(51, 204, 0));
        restockProductBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        restockProductBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restock_button.png"))); // NOI18N
        restockProductBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        restockProductBtn.setContentAreaFilled(false);
        restockProductBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        restockProductBtn.setInheritsPopupMenu(true);
        restockProductBtn.setOpaque(true);
        restockProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockProductBtnActionPerformed(evt);
            }
        });
        mainBtnPanel.add(restockProductBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 465, 124, 104));

        barDesign4.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign4.setForeground(new java.awt.Color(255, 255, 255));
        barDesign4.setText("|");
        mainBtnPanel.add(barDesign4, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 316, -1, -1));

        barDesign7.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign7.setForeground(new java.awt.Color(255, 255, 255));
        barDesign7.setText("|");
        mainBtnPanel.add(barDesign7, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 465, -1, -1));

        barDesign2.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign2.setForeground(new java.awt.Color(255, 255, 255));
        barDesign2.setText("|");
        mainBtnPanel.add(barDesign2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 176, -1, -1));

        barDesign3.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign3.setForeground(new java.awt.Color(255, 255, 255));
        barDesign3.setText("|");
        mainBtnPanel.add(barDesign3, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 314, -1, -1));

        barDesign6.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign6.setForeground(new java.awt.Color(255, 255, 255));
        barDesign6.setText("|");
        mainBtnPanel.add(barDesign6, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 465, -1, -1));

        barDesign1.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign1.setForeground(new java.awt.Color(255, 255, 255));
        barDesign1.setText("|");
        mainBtnPanel.add(barDesign1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 178, -1, -1));

        logoBackground.setBackground(new java.awt.Color(51, 102, 255));
        logoBackground.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/plasystem.png"))); // NOI18N
        logoBackground.setInheritsPopupMenu(false);
        logoBackground.setOpaque(true);
        mainBtnPanel.add(logoBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 318, 138));

        lowStockBtn.setBackground(new java.awt.Color(51, 204, 0));
        lowStockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lowStockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/lowstockbutton.png"))); // NOI18N
        lowStockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lowStockBtn.setContentAreaFilled(false);
        lowStockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lowStockBtn.setInheritsPopupMenu(true);
        lowStockBtn.setOpaque(true);
        lowStockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowStockBtnActionPerformed(evt);
            }
        });
        mainBtnPanel.add(lowStockBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(76, 596, 124, 127));

        barDesign9.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign9.setForeground(new java.awt.Color(255, 255, 255));
        barDesign9.setText("|");
        mainBtnPanel.add(barDesign9, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 615, -1, -1));

        barDesign8.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        barDesign8.setForeground(new java.awt.Color(255, 255, 255));
        barDesign8.setText("|");
        mainBtnPanel.add(barDesign8, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 616, -1, -1));

        getContentPane().add(mainBtnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 913));
        setBackground(Color. BLUE);

        mainInfoPanel.setMinimumSize(new java.awt.Dimension(3000, 920));
        mainInfoPanel.setPreferredSize(new java.awt.Dimension(3000, 913));
        mainInfoPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        productTbl.setAutoCreateRowSorter(true);
        productTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Brand", "Size", "Type", "Price", "Quantity", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
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
        productTbl.getTableHeader().setReorderingAllowed(false);
        productTblScrollPane.setViewportView(productTbl);
        if (productTbl.getColumnModel().getColumnCount() > 0) {
            productTbl.getColumnModel().getColumn(0).setPreferredWidth(15);
            productTbl.getColumnModel().getColumn(4).setPreferredWidth(20);
            productTbl.getColumnModel().getColumn(5).setPreferredWidth(25);
            productTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        mainInfoPanel.add(productTblScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1200, 655));

        editDeleteProductPanel.setOpaque(false);
        editDeleteProductPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        editProductBtn.setText("Edit");
        editProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editProductBtnActionPerformed(evt);
            }
        });
        editDeleteProductPanel.add(editProductBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 6, -1, -1));

        deleteProductBtn.setBackground(new java.awt.Color(255, 102, 102));
        deleteProductBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteProductBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteProductBtn.setText("Delete");
        deleteProductBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProductBtnActionPerformed(evt);
            }
        });
        editDeleteProductPanel.add(deleteProductBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 6, -1, -1));

        mainInfoPanel.add(editDeleteProductPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, -1, 29));

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

        mainInfoPanel.add(searchPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        secondaryBtnPanel.setOpaque(false);
        secondaryBtnPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userAccountsBtn.setText("👤 User Accounts");
        userAccountsBtn.setInheritsPopupMenu(true);
        userAccountsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAccountsBtnActionPerformed(evt);
            }
        });
        secondaryBtnPanel.add(userAccountsBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(899, 6, -1, 46));

        exportInventoryBtn.setText("🖨️ Export Inventory");
        exportInventoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportInventoryBtnActionPerformed(evt);
            }
        });
        secondaryBtnPanel.add(exportInventoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1034, 6, 170, 46));

        logoutBtn.setBackground(new java.awt.Color(255, 102, 102));
        logoutBtn.setForeground(new java.awt.Color(255, 255, 255));
        logoutBtn.setText("↩ Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });
        secondaryBtnPanel.add(logoutBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 138, 46));

        transactHistoryBtn.setText("🔄 Transaction History");
        transactHistoryBtn.setInheritsPopupMenu(true);
        transactHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactHistoryBtnActionPerformed(evt);
            }
        });
        secondaryBtnPanel.add(transactHistoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 6, -1, 46));

        restockHistoryBtn.setText("🔄 Restock History");
        restockHistoryBtn.setInheritsPopupMenu(true);
        restockHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockHistoryBtnActionPerformed(evt);
            }
        });
        secondaryBtnPanel.add(restockHistoryBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(597, 6, -1, 46));

        greetingsLabel.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        greetingsLabel.setForeground(new java.awt.Color(255, 255, 255));
        greetingsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        greetingsLabel.setText("Welcome To PlaSystem! Username, Role");
        secondaryBtnPanel.add(greetingsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 440, 50));

        mainInfoPanel.add(secondaryBtnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 720, 1250, -1));

        mainBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/bg_mainprogram.png"))); // NOI18N
        mainBackground.setText("designLabel");
        mainBackground.setMaximumSize(new java.awt.Dimension(3000, 1207));
        mainBackground.setMinimumSize(new java.awt.Dimension(3000, 1207));
        mainBackground.setPreferredSize(new java.awt.Dimension(3000, 1207));
        mainInfoPanel.add(mainBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1410, 920));

        getContentPane().add(mainInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, -7, 1390, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method triggered when the 'Transact' button is clicked in the GUI.
     * Initializes and opens a new TransactionGUI to start a transaction process.
     * Passes the gameList, file path, and main data table to the TransactionGUI.
     * @param evt Action event generated by the button click
     */
    private void transactionBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactionBtnActionPerformed
        launchSingleInstance(TransactionGUI.class, () -> {
            TransactionGUI transGUI = new TransactionGUI(this, productDataModel, transactionDataModel);
            transGUI.pack();
            transGUI.setLocationRelativeTo(null);
            transGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return transGUI;
        });
    }//GEN-LAST:event_transactionBtnActionPerformed
    
    /**
     * Method triggered when the 'Add' button is clicked in the GUI.
     * Initializes and opens a new AddDataGUI to add new game data.
     * Passes the gameList, main data table, and file path to the AddDataGUI.
     * @param evt Action event generated by the button click
     */
    private void addProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addProductBtnActionPerformed
        launchSingleInstance(AddProductGUI.class, () -> {
            AddProductGUI addGUI = new AddProductGUI(this, productDataModel);
            addGUI.pack();
            addGUI.setLocationRelativeTo(null);
            addGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return addGUI;
        });
    }//GEN-LAST:event_addProductBtnActionPerformed
    
    /**
     * Method triggered when the 'Edit' button is clicked in the GUI.
     * Retrieves data from the selected row in the main table and opens the EditDataGUI for editing.
     * @param evt Action event generated by the button click
     */
    private void editProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editProductBtnActionPerformed
        int editRow = productTbl.getSelectedRow();
        if (editRow != -1) {
            ProductRowSelector rowSelector = new ProductRowSelector(productTbl);
            ProductData product = rowSelector.getProductData();

            launchSingleInstance(EditProductGUI.class, () -> {
                EditProductGUI editGUI = new EditProductGUI(this, productDataModel, product, editRow);
                editGUI.pack();
                editGUI.setLocationRelativeTo(null);
                editGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                return editGUI;
            });
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editProductBtnActionPerformed
    
    /**
     * Method triggered when the 'Delete' button is clicked in the GUI.
     * Deletes the selected row from the main 
     * @param evt Action event generated by the button click
     */
    private void deleteProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_deleteProductBtnActionPerformed
        int selectedRow = productTbl.getSelectedRow();
        if (selectedRow != -1) {
            ProductRowSelector rowSelector = new ProductRowSelector(productTbl);
            int productId = rowSelector.getProductData().getProductId();
            String productName = rowSelector.getProductData().getProductName();

            int confirmDelete = JOptionPane.showConfirmDialog(null, 
                "Do you really want to delete the product '" + productName + "'?",
                "Delete", 
                JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                boolean deleteSuccess = productDataModel.deleteProduct(productId);
                if (deleteSuccess) {
                    productDataModel.updateTable(productTbl);
                    JOptionPane.showMessageDialog(null, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteProductBtnActionPerformed
    
    /**
     * Method triggered when text is typed into the search text field.
     * Filters rows in the main table based on the entered search text.
     * @param evt Key event generated when a key is released in the search text field
     */
    private void searchTxtFieldKeyReleased(KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        DefaultTableModel prodTblModel = (DefaultTableModel) productTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(prodTblModel);
        productTbl.setRowSorter(sorter);
        
        // Get the selected column name from the search parameter box
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString(); // Replace "ColumnName" with the actual column name
        int columnIndex = prodTblModel.findColumn(columnNameToSearch);
        
        // Apply a row filter based on the entered search text for the selected column
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    private void restockProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockProductBtnActionPerformed
        launchSingleInstance(RestockProductGUI.class, () -> {
            RestockProductGUI restockGUI = new RestockProductGUI(this, productDataModel, restockDataModel);
            restockGUI.pack();
            restockGUI.setLocationRelativeTo(null);
            restockGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return restockGUI;
        });
    }//GEN-LAST:event_restockProductBtnActionPerformed

    private void lowStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowStockBtnActionPerformed
        launchSingleInstance(LowStockGUI.class, () -> {
            LowStockGUI lowStockGUI = new LowStockGUI(productDataModel);
            lowStockGUI.pack();
            lowStockGUI.setLocationRelativeTo(null);
            lowStockGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return lowStockGUI;
        });
    }//GEN-LAST:event_lowStockBtnActionPerformed

    private void userAccountsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAccountsBtnActionPerformed
        launchSingleInstance(UserAccountsGUI.class, () -> {
            UserAccountsGUI userAccGUI = new UserAccountsGUI(userAccountDataModel);
            userAccGUI.pack();
            userAccGUI.setLocationRelativeTo(null);
            userAccGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return userAccGUI;
        });
    }//GEN-LAST:event_userAccountsBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // Open LaunchPanelGUI
        JFrame launchPanel = new LaunchPanelGUI();
        launchPanel.setVisible(true);
        launchPanel.pack();
        launchPanel.setLocationRelativeTo(null);

        // Close all child GUIs
        for (JFrame child : new ArrayList<>(childGUIs)) {
            child.dispose();
        }
        childGUIs.clear();

        // Dispose of MainProgramGUI
        this.dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void transactHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactHistoryBtnActionPerformed
        launchSingleInstance(TransactionHistoryGUI.class, () -> {
            TransactionHistoryGUI transHistoryGUI = new TransactionHistoryGUI(transactionDataModel);
            transHistoryGUI.pack();
            transHistoryGUI.setLocationRelativeTo(null);
            transHistoryGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return transHistoryGUI;
        });
    }//GEN-LAST:event_transactHistoryBtnActionPerformed

    private void restockHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockHistoryBtnActionPerformed
        launchSingleInstance(RestockHistoryGUI.class, () -> {
            RestockHistoryGUI restockHistoryGUI = new RestockHistoryGUI(restockDataModel);
            restockHistoryGUI.pack();
            restockHistoryGUI.setLocationRelativeTo(null);
            restockHistoryGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return restockHistoryGUI;
        });
    }//GEN-LAST:event_restockHistoryBtnActionPerformed

    private void exportInventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportInventoryBtnActionPerformed
        InventoryReportGenerator reportGenerator = new InventoryReportGenerator(productList);
        reportGenerator.generateReport(this);
    }//GEN-LAST:event_exportInventoryBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton addProductBtn;
    public javax.swing.JLabel barDesign1;
    public javax.swing.JLabel barDesign2;
    public javax.swing.JLabel barDesign3;
    public javax.swing.JLabel barDesign4;
    public javax.swing.JLabel barDesign6;
    public javax.swing.JLabel barDesign7;
    public javax.swing.JLabel barDesign8;
    public javax.swing.JLabel barDesign9;
    public javax.swing.JButton deleteProductBtn;
    public javax.swing.JPanel editDeleteProductPanel;
    public javax.swing.JButton editProductBtn;
    public javax.swing.JButton exportInventoryBtn;
    public javax.swing.JLabel greetingsLabel;
    public javax.swing.JLabel logoBackground;
    public javax.swing.JButton logoutBtn;
    public javax.swing.JButton lowStockBtn;
    public javax.swing.JLabel mainBackground;
    public javax.swing.JPanel mainBtnPanel;
    public javax.swing.JPanel mainInfoPanel;
    public javax.swing.JTable productTbl;
    public javax.swing.JScrollPane productTblScrollPane;
    public javax.swing.JButton restockHistoryBtn;
    public javax.swing.JButton restockProductBtn;
    public javax.swing.JPanel searchPanel;
    public javax.swing.JComboBox<String> searchPrmtrBox;
    public javax.swing.JTextField searchTxtField;
    public javax.swing.JPanel secondaryBtnPanel;
    public javax.swing.JButton transactHistoryBtn;
    public javax.swing.JButton transactionBtn;
    public javax.swing.JButton userAccountsBtn;
    // End of variables declaration//GEN-END:variables
}
