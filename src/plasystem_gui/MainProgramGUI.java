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
 * The main window of the application, displaying product data in a table and providing
 * controls for managing products, transactions, restocking, and user accounts.
 */
public class MainProgramGUI extends JFrame {
    /** The ProductDataManager instance for managing product data operations. */
    private final ProductDataManager productDataModel = new ProductDataManager();
    
    /** The list of ProductData objects retrieved from the database. */
    private final List<ProductData> productList = productDataModel.getList();
    
    /** The RestockDataManager instance for managing restock operations. */
    private final RestockDataManager restockDataModel = new RestockDataManager(productDataModel);
    
    /** The TransactionDataManager instance for managing transaction operations. */
    private final TransactionDataManager transactionDataModel = new TransactionDataManager(productDataModel);
    
    /** The UserAccountDataManager instance for managing user account operations. */
    private UserAccountDataManager userAccountDataModel;
    
    /** The list tracking open child GUI windows. */
    private final List<JFrame> childGUIs = new ArrayList<>();
    
    /** The map tracking active GUI instances to ensure single-instance behavior. */
    private final Map<Class<? extends JFrame>, JFrame> activeGUIs = new HashMap<>();
    
    /**
     * Default constructor that initializes the MainProgramGUI.
     * Centers the window and sets up the form components.
     */
    public MainProgramGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the MainProgramGUI with user account data handling.
     * Populates the product table, applies table formatting, and configures role-based access control.
     *
     * @param userAccountDataHandling The UserAccountDataManager instance for user account operations
     */
    public MainProgramGUI(UserAccountDataManager userAccountDataHandling) {
        // Assign the user account data manager
        this.userAccountDataModel = userAccountDataHandling;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        
        // Populate the product table with data from the database
        productDataModel.updateTable(productTbl);
        
        // Apply dynamic column formatting and sizing to the table
        new ProductTableRenderer(productTbl, productList, 1200);
        
        // Display a message if the database is empty
        if (productList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Database is empty", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Update the greetings label with the logged-in user's username and role
        String username = userAccountDataHandling.getLoggedInUsername();
        String userRole = userAccountDataHandling.getLoggedInRole();
        if (username != null && userRole != null) {
            greetingsLabel.setText("Welcome To PlaSystem! " + username + ", " + userRole);
        }
        
        // Apply role-based access control to enable/disable features
        enableRoleControl();
    }
        
    /**
     * Adds a child GUI to the tracking list.
     *
     * @param child The JFrame to add as a child GUI
     */
    public void addChildGUI(JFrame child) {
        // Add the child GUI to the tracking list
        childGUIs.add(child);
    }

    /**
     * Removes a child GUI from the tracking list.
     *
     * @param child The JFrame to remove from the child GUI list
     */
    public void removeChildGUI(JFrame child) {
        // Remove the child GUI from the tracking list
        childGUIs.remove(child);
    }
    
    /**
     * Launches or focuses a single instance of a specified GUI.
     * Ensures only one instance of the GUI is open at a time.
     *
     * @param guiClass The class of the GUI to launch
     * @param creator A Supplier to create a new instance of the GUI if needed
     * @param <T> The type of the JFrame subclass
     * @return The launched or existing GUI instance
     */
    private <T extends JFrame> T launchSingleInstance(Class<T> guiClass, Supplier<T> creator) {
        // Check if an instance of the GUI already exists
        JFrame existingInstance = activeGUIs.get(guiClass);
        // Remove the instance if it exists but is no longer displayable
        if (existingInstance != null && !existingInstance.isDisplayable()) {
            activeGUIs.remove(guiClass);
            existingInstance = null;
        }

        // If an instance exists, focus it and show a warning
        if (existingInstance != null) {
            JOptionPane.showMessageDialog(
                existingInstance,
                "Only one instance can be present.",
                "Instance Warning",
                JOptionPane.WARNING_MESSAGE
            );
            // Bring the existing instance to the foreground
            existingInstance.requestFocus();
            // Ensure the instance is visible
            existingInstance.setVisible(true);
            return guiClass.cast(existingInstance);
        }

        // Create a new instance of the GUI
        T newInstance = creator.get();
        // Make the new instance visible
        newInstance.setVisible(true);
        // Set the default close operation to dispose
        newInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Add a window listener to clean up when the window is closed
        newInstance.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Remove the GUI from the active instances map
                activeGUIs.remove(guiClass);
                // Remove the GUI from the child GUI list
                removeChildGUI(newInstance);
            }
        });
        // Add the new instance to the active GUIs map
        activeGUIs.put(guiClass, newInstance);
        // Add the new instance to the child GUI list
        addChildGUI(newInstance);
        return newInstance;
    }
    
    /**
     * Refreshes the product table with updated data.
     * Reapplies table formatting and any active search filters.
     */
    public void updateProductTable() {
        // Update the table with current product data
        productDataModel.updateTable(productTbl);
        
        // Reapply dynamic column formatting and sizing
        new ProductTableRenderer(productTbl, productList, 1200);
        
        // Reapply the current search filter if the search field is not empty
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * Applies role-based access control to enable or disable features based on the user's role.
     */
    private void enableRoleControl() {
        // Apply permissions based on the logged-in user's role
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
     * Handles the action when the "Transact" button is clicked.
     * Opens a single instance of the TransactionGUI for processing transactions.
     *
     * @param evt The ActionEvent triggered by clicking the "Transact" button
     */
    private void transactionBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactionBtnActionPerformed
        // Launch or focus a single instance of TransactionGUI
        launchSingleInstance(TransactionGUI.class, () -> {
            // Create a new TransactionGUI instance
            TransactionGUI transGUI = new TransactionGUI(this, productDataModel, transactionDataModel);
            // Pack the GUI to fit its contents
            transGUI.pack();
            // Center the GUI on the screen
            transGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            transGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return transGUI;
        });
    }//GEN-LAST:event_transactionBtnActionPerformed
    
    /**
     * Handles the action when the "Add Product" button is clicked.
     * Opens a single instance of the AddProductGUI for adding new products.
     *
     * @param evt The ActionEvent triggered by clicking the "Add Product" button
     */
    private void addProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addProductBtnActionPerformed
        // Launch or focus a single instance of AddProductGUI
        launchSingleInstance(AddProductGUI.class, () -> {
            // Create a new AddProductGUI instance
            AddProductGUI addGUI = new AddProductGUI(this, productDataModel);
            // Pack the GUI to fit its contents
            addGUI.pack();
            // Center the GUI on the screen
            addGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            addGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return addGUI;
        });
    }//GEN-LAST:event_addProductBtnActionPerformed
    
    /**
     * Handles the action when the "Edit" button is clicked.
     * Opens a single instance of the EditProductGUI for editing the selected product.
     *
     * @param evt The ActionEvent triggered by clicking the "Edit" button
     */
    private void editProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editProductBtnActionPerformed
        // Get the index of the selected row
        int editRow = productTbl.getSelectedRow();
        // Check if a row is selected
        if (editRow != -1) {
            // Create a ProductRowSelector to retrieve product data
            ProductRowSelector rowSelector = new ProductRowSelector(productTbl);
            // Get the selected product's data
            ProductData product = rowSelector.getProductData();

            // Launch or focus a single instance of EditProductGUI
            launchSingleInstance(EditProductGUI.class, () -> {
                // Create a new EditProductGUI instance
                EditProductGUI editGUI = new EditProductGUI(this, productDataModel, product, editRow);
                // Pack the GUI to fit its contents
                editGUI.pack();
                // Center the GUI on the screen
                editGUI.setLocationRelativeTo(null);
                // Set the default close operation to dispose
                editGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                return editGUI;
            });
        } else {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editProductBtnActionPerformed
    
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the selected product from the database after user confirmation.
     *
     * @param evt The ActionEvent triggered by clicking the "Delete" button
     */
    private void deleteProductBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_deleteProductBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = productTbl.getSelectedRow();
        // Check if a row is selected
        if (selectedRow != -1) {
            // Create a ProductRowSelector to retrieve product data
            ProductRowSelector rowSelector = new ProductRowSelector(productTbl);
            // Get the selected product's ID
            int productId = rowSelector.getProductData().getProductId();
            // Get the selected product's name
            String productName = rowSelector.getProductData().getProductName();

            // Prompt user to confirm deletion
            int confirmDelete = JOptionPane.showConfirmDialog(null, 
                "Do you really want to delete the product '" + productName + "'?",
                "Delete", 
                JOptionPane.YES_NO_OPTION);
            // Proceed with deletion if user confirms
            if (confirmDelete == JOptionPane.YES_OPTION) {
                // Attempt to delete the product from the database
                boolean deleteSuccess = productDataModel.deleteProduct(productId);
                if (deleteSuccess) {
                    // Update the table to reflect the deletion
                    productDataModel.updateTable(productTbl);
                    // Display success message
                    JOptionPane.showMessageDialog(null, "Product deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteProductBtnActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the product table based on the search text and selected column.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Get the table model
        DefaultTableModel prodTblModel = (DefaultTableModel) productTbl.getModel();
        // Create a new TableRowSorter for filtering
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(prodTblModel);
        // Apply the sorter to the table
        productTbl.setRowSorter(sorter);
        
        // Get the selected column name from the search parameter box
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        // Find the index of the selected column
        int columnIndex = prodTblModel.findColumn(columnNameToSearch);
        
        // Apply a case-insensitive regex filter to the selected column
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
    }//GEN-LAST:event_searchTxtFieldKeyReleased
    
    /**
     * Handles the action when the "Restock Product" button is clicked.
     * Opens a single instance of the RestockProductGUI for restocking products.
     *
     * @param evt The ActionEvent triggered by clicking the "Restock Product" button
     */
    private void restockProductBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockProductBtnActionPerformed
        // Launch or focus a single instance of RestockProductGUI
        launchSingleInstance(RestockProductGUI.class, () -> {
            // Create a new RestockProductGUI instance
            RestockProductGUI restockGUI = new RestockProductGUI(this, productDataModel, restockDataModel);
            // Pack the GUI to fit its contents
            restockGUI.pack();
            // Center the GUI on the screen
            restockGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            restockGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return restockGUI;
        });
    }//GEN-LAST:event_restockProductBtnActionPerformed
    
     /**
     * Handles the action when the "Low Stock" button is clicked.
     * Opens a single instance of the LowStockGUI to display low stock products.
     *
     * @param evt The ActionEvent triggered by clicking the "Low Stock" button
     */
    private void lowStockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowStockBtnActionPerformed
        // Launch or focus a single instance of LowStockGUI
        launchSingleInstance(LowStockGUI.class, () -> {
            // Create a new LowStockGUI instance
            LowStockGUI lowStockGUI = new LowStockGUI(productDataModel);
            // Pack the GUI to fit its contents
            lowStockGUI.pack();
            // Center the GUI on the screen
            lowStockGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            lowStockGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return lowStockGUI;
        });
    }//GEN-LAST:event_lowStockBtnActionPerformed
    
    /**
     * Handles the action when the "User Accounts" button is clicked.
     * Opens a single instance of the UserAccountsGUI for managing user accounts.
     *
     * @param evt The ActionEvent triggered by clicking the "User Accounts" button
     */
    private void userAccountsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAccountsBtnActionPerformed
        // Launch or focus a single instance of UserAccountsGUI
        launchSingleInstance(UserAccountsGUI.class, () -> {
            // Create a new UserAccountsGUI instance
            UserAccountsGUI userAccGUI = new UserAccountsGUI(userAccountDataModel);
            // Pack the GUI to fit its contents
            userAccGUI.pack();
            // Center the GUI on the screen
            userAccGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            userAccGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return userAccGUI;
        });
    }//GEN-LAST:event_userAccountsBtnActionPerformed
    
    /**
     * Handles the action when the "Logout" button is clicked.
     * Closes all child GUIs, opens the LaunchPanelGUI, and disposes of the current window.
     *
     * @param evt The ActionEvent triggered by clicking the "Logout" button
     */
    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // Create and display a new LaunchPanelGUI
        JFrame launchPanel = new LaunchPanelGUI();
        launchPanel.setVisible(true);
        launchPanel.pack();
        launchPanel.setLocationRelativeTo(null);

        // Close all child GUIs
        for (JFrame child : new ArrayList<>(childGUIs)) {
            child.dispose();
        }
        // Clear the child GUI list
        childGUIs.clear();

        // Dispose of the current MainProgramGUI
        this.dispose();
    }//GEN-LAST:event_logoutBtnActionPerformed
    
    /**
     * Handles the action when the "Transaction History" button is clicked.
     * Opens a single instance of the TransactionHistoryGUI to display transaction records.
     *
     * @param evt The ActionEvent triggered by clicking the "Transaction History" button
     */
    private void transactHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transactHistoryBtnActionPerformed
        // Launch or focus a single instance of TransactionHistoryGUI
        launchSingleInstance(TransactionHistoryGUI.class, () -> {
            // Create a new TransactionHistoryGUI instance
            TransactionHistoryGUI transHistoryGUI = new TransactionHistoryGUI(transactionDataModel);
            // Pack the GUI to fit its contents
            transHistoryGUI.pack();
            // Center the GUI on the screen
            transHistoryGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            transHistoryGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return transHistoryGUI;
        });
    }//GEN-LAST:event_transactHistoryBtnActionPerformed
    
    /**
     * Handles the action when the "Restock History" button is clicked.
     * Opens a single instance of the RestockHistoryGUI to display restock records.
     *
     * @param evt The ActionEvent triggered by clicking the "Restock History" button
     */
    private void restockHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockHistoryBtnActionPerformed
        // Launch or focus a single instance of RestockHistoryGUI
        launchSingleInstance(RestockHistoryGUI.class, () -> {
            // Create a new RestockHistoryGUI instance
            RestockHistoryGUI restockHistoryGUI = new RestockHistoryGUI(restockDataModel);
            // Pack the GUI to fit its contents
            restockHistoryGUI.pack();
            // Center the GUI on the screen
            restockHistoryGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            restockHistoryGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return restockHistoryGUI;
        });
    }//GEN-LAST:event_restockHistoryBtnActionPerformed
    
    /**
     * Handles the action when the "Export Inventory" button is clicked.
     * Generates and exports an inventory report.
     *
     * @param evt The ActionEvent triggered by clicking the "Export Inventory" button
     */
    private void exportInventoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportInventoryBtnActionPerformed
        // Create an InventoryReportGenerator with the product list
        InventoryReportGenerator reportGenerator = new InventoryReportGenerator(productList);
        // Generate and export the inventory report
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
