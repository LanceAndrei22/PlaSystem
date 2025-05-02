package plasystem_gui;

import plasystem_functions.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javax.swing.table.*;
import java.util.Timer;
import java.util.TimerTask;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A graphical user interface (GUI) window for handling product transactions.
 * This class allows users to select products, specify quantities, calculate totals,
 * process payments, and generate receipts.
 */
public class TransactionGUI extends JFrame {
    /** The ProductDataManager instance for managing product data operations. */
    private ProductDataManager productDataModel;
    /** The TransactionDataManager instance for managing transaction data operations. */
    private TransactionDataManager transactionDataModel;
    /** The ErrorValueHandling instance for validating input data. */
    private ErrorValueHandling dataValidator;
    /** The parent MainProgramGUI instance for updating the product table after transactions. */
    private MainProgramGUI parentGUI;
    /** The list of TransactionItemData objects representing items in the current transaction. */
    private List<TransactionItemData> transactionItems;
    /** The total purchase amount for the current transaction. */
    private double totalPurchase;
    /** The Timer for periodically refreshing the product selection table. */
    private Timer refreshTimer;
    /** Flag indicating whether the transaction has been submitted. */
    private boolean isTransactionSubmitted;
    
    /**
     * Default constructor that initializes the TransactionGUI.
     * Centers the window and sets up the form components.
     */
    public TransactionGUI(){
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the TransactionGUI with necessary dependencies.
     * Sets up the form components, centers the window, and initializes the GUI state.
     *
     * @param parentGUI The parent MainProgramGUI to update its table
     * @param productDataManager The ProductDataManager for product data operations
     * @param transactionDataManager The TransactionDataManager for transaction data operations
     */
    public TransactionGUI(MainProgramGUI parentGUI, ProductDataManager productDataManager, TransactionDataManager transactionDataManager){
        // Assign the parent GUI for table updates
        this.parentGUI = parentGUI;
        // Assign the product data manager
        this.productDataModel = productDataManager;
        // Assign the transaction data manager
        this.transactionDataModel = transactionDataManager;
        // Initialize the data validator
        this.dataValidator = new ErrorValueHandling();
        // Initialize the transaction items list
        this.transactionItems = new LinkedList<>();
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Set up the GUI state
        initializeGUI();
        // Start the table refresh timer
        startTableRefresh();
    }
    
    /**
     * Initializes the GUI components and their states.
     */
    private void initializeGUI() {
        // Set the date field with the current formatted date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTxtField.setText(now.format(formatter));
        // Disable the date field
        dateTxtField.setEnabled(false);
        // Disable the add button initially
        addBtn.setEnabled(false);
        // Disable the total amount field
        totalAmountTxtField.setEnabled(false);
        // Disable the product name field
        prodNameTxtField.setEnabled(false);
        // Make the item price field non-editable
        itemPriceTxtField.setEditable(false);
        // Disable the date field
        dateTxtField.setEnabled(false);
        // Disable the cart table
        cartTbl.setEnabled(false);
        // Disable the print receipt button initially
        printReceiptBtn.setEnabled(false);
        // Clear the transaction items list
        transactionItems.clear();
        // Clear the cart table
        ((DefaultTableModel) cartTbl.getModel()).setRowCount(0);
        // Reset the total purchase amount
        totalPurchase = 0.0;
        // Clear the total amount field
        totalAmountTxtField.setText("");

        // Set up the quantity spinner with a model
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 0, 1);
        quantityPicker.setModel(spinnerModel);
        // Disable the quantity spinner initially
        quantityPicker.setEnabled(false);
        // Add a change listener to prevent negative values
        quantityPicker.addChangeListener(e -> {
            // Get the current spinner value
            int value = (int) quantityPicker.getValue();
            // Reset to 0 if negative
            if (value < 0) {
                quantityPicker.setValue(0);
            }
        });

        // Add a selection listener to the product selection table
        productSelectionTbl.getSelectionModel().addListSelectionListener(e -> {
            // Check if the selection is stable and a row is selected
            if (!e.getValueIsAdjusting() && productSelectionTbl.getSelectedRow() != -1) {
                // Create a selector for the selected row
                ProductRowSelector selector = new ProductRowSelector(productSelectionTbl);
                // Get the selected product data
                ProductData selectedProduct = selector.getProductData();
                if (selector.getRow() != -1) {
                    // Update the product ID field
                    prodIDTxtField.setText(String.valueOf(selectedProduct.getProductId()));
                    // Clear other fields until verified
                    prodNameTxtField.setText("");
                    itemPriceTxtField.setText("");
                    ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
                    quantityPicker.setValue(0);
                    quantityPicker.setEnabled(false);
                    addBtn.setEnabled(false);
                } else {
                    // Clear fields if no valid row is selected
                    prodIDTxtField.setText("");
                    prodNameTxtField.setText("");
                    itemPriceTxtField.setText("");
                    ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
                    quantityPicker.setValue(0);
                    quantityPicker.setEnabled(false);
                    addBtn.setEnabled(false);
                }
            }
        });

        // Populate the product selection table
        populateProductSelectionTable();
    }
    
    /**
     * Starts a timer to periodically refresh the product selection table with the latest data.
     */
    private void startTableRefresh() {
        // Create a daemon timer
        refreshTimer = new Timer(true);
        // Schedule a task to refresh the table every second
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Run UI updates on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> {
                    // Update the date field with the current formatted date
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    dateTxtField.setText(now.format(formatter));
                    // Refresh the product selection table
                    populateProductSelectionTable();
                });
            }
        }, 0, 1000);
    }
    
    /**
     * Stops the table refresh timer and cleans up resources when the window is closed.
     */
    @Override
    public void dispose() {
        // Stop the refresh timer if it exists
        if (refreshTimer != null) {
            refreshTimer.cancel();
        }
        // Clear the transaction items list
        transactionItems.clear();
        // Clear the cart table
        DefaultTableModel model = (DefaultTableModel) cartTbl.getModel();
        model.setRowCount(0);
        // Reset the total purchase amount
        totalPurchase = 0.00;
        // Clear the total amount field if it exists
        if (totalAmountTxtField != null) {
            totalAmountTxtField.setText("");
        }
        // Call the superclass dispose method to close the window
        super.dispose();
    }
    
    /**
     * Populates the product selection table with all products from the database.
     */
    private void populateProductSelectionTable() {
        // Get the table model
        DefaultTableModel prodSelectionTblModel = (DefaultTableModel) productSelectionTbl.getModel();
        // Clear existing rows
        prodSelectionTblModel.setRowCount(0);
        // Get the product list from the data manager
        List<ProductData> productList = productDataModel.getList();
        // Iterate through the product list
        for (ProductData product : productList) {
            // Add each product as a new row
            prodSelectionTblModel.addRow(new Object[] {
                product.getProductId(),
                product.getProductName(),
                product.getProductBrand(),
                product.getProductSize(),
                product.getProductType(),
                product.getProductPrice(),
                product.getProductQuantity(),
                product.getProductRestockValue()
            });
        }
        
        // Apply the product table renderer with a table width of 752 pixels
        new ProductTableRenderer(productSelectionTbl, productDataModel.getList(), 752);
        // Apply custom rendering to the cart table
        CartTableRenderer.applyCartTableRendering(cartTbl);
        
        // Reapply the current search filter if the search field is not empty
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * Clears the cart, transaction items, and input fields.
     */
    private void clearCartAndFields() {
        // Clear the transaction items list
        transactionItems.clear();
        // Clear the cart table
        DefaultTableModel model = (DefaultTableModel) cartTbl.getModel();
        model.setRowCount(0);
        // Reset the total purchase amount
        totalPurchase = 0.0;
        // Clear the total amount field
        totalAmountTxtField.setText("");
        // Enable the product ID field
        prodIDTxtField.setEnabled(true);
        // Clear the product ID field
        prodIDTxtField.setText("");
        // Clear the product name field
        prodNameTxtField.setText("");
        // Clear the item price field
        itemPriceTxtField.setText("");
        // Reset the quantity spinner
        ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
        quantityPicker.setValue(0);
        // Disable the quantity spinner
        quantityPicker.setEnabled(false);
        // Disable the add button
        addBtn.setEnabled(false);
        // Clear the product selection table selection
        productSelectionTbl.clearSelection();
    }

    /**
     * Resets the GUI to its initial state, clearing all fields and enabling controls.
     */
    private void resetGUI() {
        // Clear the cart and fields
        clearCartAndFields();
        // Clear the payment amount field
        paymentAmountTxtField.setText("");
        // Enable the submit button
        submitBtn.setEnabled(true);
        // Enable the payment amount field
        paymentAmountTxtField.setEnabled(true);
        // Enable the verify button
        verifyBtn.setEnabled(true);
        // Enable the clear button
        clearBtn.setEnabled(true);
        // Disable the print receipt button
        printReceiptBtn.setEnabled(false);
        // Reset the cancel button text
        cancelBtn.setText("CANCEL");
        // Reset the transaction submitted flag
        isTransactionSubmitted = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transactPanel = new javax.swing.JPanel();
        prodIDLabel = new javax.swing.JLabel();
        itemPriceLabel = new javax.swing.JLabel();
        paymentAmountLabel = new javax.swing.JLabel();
        itemPriceTxtField = new javax.swing.JTextField();
        prodIDTxtField = new javax.swing.JTextField();
        dateTxtField = new javax.swing.JTextField();
        dateLabel = new javax.swing.JLabel();
        verifyBtn = new javax.swing.JButton();
        totalAmountLabel = new javax.swing.JLabel();
        totalAmountTxtField = new javax.swing.JTextField();
        paymentAmountTxtField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityPicker = new javax.swing.JSpinner();
        addBtn = new javax.swing.JButton();
        prodNameLabel = new javax.swing.JLabel();
        prodNameTxtField = new javax.swing.JTextField();
        clearBtn = new javax.swing.JButton();
        cartScrollPane = new javax.swing.JScrollPane();
        cartTbl = new javax.swing.JTable();
        productSelectionScrollPane = new javax.swing.JScrollPane();
        productSelectionTbl = new javax.swing.JTable();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        titleLabel = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        printReceiptBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        transactPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        prodIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        prodIDLabel.setText("Product ID:");

        itemPriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        itemPriceLabel.setText("Item Price:");

        paymentAmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        paymentAmountLabel.setText("Payment Amount:");

        itemPriceTxtField.setEditable(false);

        dateTxtField.setEditable(false);

        dateLabel.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        dateLabel.setText("Date of Transaction:");

        verifyBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        verifyBtn.setText("VERIFY PRODUCT");
        verifyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifyBtnActionPerformed(evt);
            }
        });

        totalAmountLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        totalAmountLabel.setText("Total:");

        totalAmountTxtField.setEditable(false);

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        quantityLabel.setText("Quantity:");

        addBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        prodNameLabel.setText("Product Name:");

        prodNameTxtField.setEditable(false);

        clearBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        clearBtn.setText("CLEAR");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        cartTbl.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        cartTbl.setForeground(new java.awt.Color(0, 0, 0));
        cartTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAME", "QUANTITY", "PRICE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cartTbl.getTableHeader().setReorderingAllowed(false);
        cartScrollPane.setViewportView(cartTbl);
        if (cartTbl.getColumnModel().getColumnCount() > 0) {
            cartTbl.getColumnModel().getColumn(1).setPreferredWidth(20);
            cartTbl.getColumnModel().getColumn(2).setPreferredWidth(25);
        }

        productSelectionTbl.setAutoCreateRowSorter(true);
        productSelectionTbl.setModel(new javax.swing.table.DefaultTableModel(
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
        productSelectionTbl.getTableHeader().setReorderingAllowed(false);
        productSelectionScrollPane.setViewportView(productSelectionTbl);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Size", "Brand", "Type", "Price", "Quantity" }));

        javax.swing.GroupLayout transactPanelLayout = new javax.swing.GroupLayout(transactPanel);
        transactPanel.setLayout(transactPanelLayout);
        transactPanelLayout.setHorizontalGroup(
            transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transactPanelLayout.createSequentialGroup()
                        .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(530, 530, 530))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, transactPanelLayout.createSequentialGroup()
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(productSelectionScrollPane)
                            .addGroup(transactPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(transactPanelLayout.createSequentialGroup()
                                            .addComponent(paymentAmountLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(paymentAmountTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, transactPanelLayout.createSequentialGroup()
                                            .addComponent(verifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(12, 12, 12)
                                            .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(transactPanelLayout.createSequentialGroup()
                                            .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(prodNameLabel)
                                                .addComponent(itemPriceLabel)
                                                .addComponent(quantityLabel))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(itemPriceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(prodNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(transactPanelLayout.createSequentialGroup()
                                        .addComponent(prodIDLabel)
                                        .addGap(61, 61, 61)
                                        .addComponent(prodIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(transactPanelLayout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addComponent(totalAmountLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalAmountTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(transactPanelLayout.createSequentialGroup()
                                        .addComponent(dateLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cartScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(42, 42, 42))))
        );
        transactPanelLayout.setVerticalGroup(
            transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productSelectionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(dateTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prodIDLabel)
                    .addComponent(prodIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cartScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(transactPanelLayout.createSequentialGroup()
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prodNameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemPriceLabel)
                            .addComponent(itemPriceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantityLabel)
                            .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(paymentAmountLabel)
                            .addComponent(paymentAmountTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(verifyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalAmountLabel)
                    .addComponent(totalAmountTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 3, 15)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/transact.png"))); // NOI18N

        cancelBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        printReceiptBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        printReceiptBtn.setText("PRINT RECEIPT");
        printReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printReceiptBtnActionPerformed(evt);
            }
        });

        submitBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        submitBtn.setText("SUBMIT");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        design.setBackground(new java.awt.Color(255, 153, 153));
        design.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(transactPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(printReceiptBtn)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(design, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(transactPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(submitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(printReceiptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(design, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 598, Short.MAX_VALUE)))
        );

        transactPanel.setBackground(new Color(255, 102, 102, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the action when the "Print Receipt" button is clicked.
     * Constructs and displays a receipt based on the transaction data.
     *
     * @param evt The ActionEvent triggered by clicking the "Print Receipt" button
     */
    private void printReceiptBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_printReceiptBtnActionPerformed
        // Create a new receipt GUI
        ReceiptInvoiceGUI receipt = new ReceiptInvoiceGUI();
        // Build the receipt content
        StringBuilder content = new StringBuilder();
        for (TransactionItemData item : transactionItems) {
            // Truncate and format the item name
            String itemName = item.getTI_productName().trim().replace(" ", "");
            itemName = itemName.length() > 14 ? itemName.substring(0, 14) : itemName;
            // Format the item details
            String formattedItem = String.format("%-35s\t     %-10s\t                  %-10s\n",
                itemName, item.getTI_buyQuantity(),
                String.format("₱%.2f", item.getTI_totalPrice()));
            content.append(formattedItem);
        }
        // Set the receipt content
        receipt.setReceiptList(content.toString());

        // Get and set the cash amount
        double cashAmount = Double.parseDouble(paymentAmountTxtField.getText());
        receipt.setCashAmount(cashAmount);

        // Round and set the total purchase amount
        double roundedTotalPurchase = new BigDecimal(totalPurchase)
            .setScale(2, RoundingMode.HALF_UP).doubleValue();
        receipt.setTotalAmount(roundedTotalPurchase);

        // Calculate, round, and set the change amount
        double change = new BigDecimal(cashAmount - totalPurchase)
            .setScale(2, RoundingMode.HALF_UP).doubleValue();
        receipt.setChangeAmount(change);

        // Get and set the transaction date
        TransactionData lastTransaction = transactionDataModel.getTransactionList().get(transactionDataModel.getTransactionList().size() - 1);
        receipt.setDateOfTransaction(lastTransaction.getFormattedDate());

        // Get and set the transaction ID
        int transId = lastTransaction.getTransactionId();
        receipt.setReceiptID("#" + String.valueOf(transId));

        // Display the receipt
        receipt.setVisible(true);
        receipt.setLocationRelativeTo(null);
        receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Export the receipt as an image
        FrameExporter.exportFrameAsImage(receipt, "#" + String.valueOf(transId));

        // Reset the GUI after printing the receipt
        resetGUI();
    }//GEN-LAST:event_printReceiptBtnActionPerformed
    
    /**
     * Handles the action when the "Cancel" button is clicked.
     * Closes the window without saving changes.
     *
     * @param evt The ActionEvent triggered by clicking the "Cancel" button
     */
    private void cancelBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // Close the window
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    /**
     * Handles the action when the "Submit" button is clicked.
     * Processes the transaction submission and updates product quantities.
     *
     * @param evt The ActionEvent triggered by clicking the "Submit" button
     */
    private void submitBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // Check if the cart is empty
        if (transactionItems.isEmpty()) {
            // Display error message if no items are in the cart
            JOptionPane.showMessageDialog(null, "Transaction is Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if payment amount is provided
        if (!paymentAmountTxtField.getText().trim().isEmpty()) {
            // Get and validate payment and total amounts
            String customerMoneyStr = paymentAmountTxtField.getText().trim();
            String totalPurchaseStr = totalAmountTxtField.getText().trim();

            // Validate that both inputs are valid doubles
            if (dataValidator.isDouble(customerMoneyStr) && dataValidator.isDouble(totalPurchaseStr)) {
                double customerMoney = Double.parseDouble(customerMoneyStr);
                totalPurchase = Double.parseDouble(totalPurchaseStr);

                // Check if payment is sufficient
                if (customerMoney >= totalPurchase) {
                    // Prompt user to confirm submission
                    int confirmSubmit = JOptionPane.showConfirmDialog(null, "Do you wish to submit transaction?", "Submit", JOptionPane.YES_NO_OPTION);
                    if (confirmSubmit == JOptionPane.YES_OPTION) {
                        // Get and parse the transaction date
                        String dateStr = dateTxtField.getText();
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(dateStr, 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            String year = String.valueOf(dateTime.getYear());
                            String month = String.format("%02d", dateTime.getMonthValue());
                            String day = String.format("%02d", dateTime.getDayOfMonth());
                            String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                            // Round total and change amounts
                            double roundedTotal = new BigDecimal(totalPurchase).setScale(2, RoundingMode.HALF_UP).doubleValue();
                            double roundedChange = new BigDecimal(customerMoney - totalPurchase).setScale(2, RoundingMode.HALF_UP).doubleValue();

                            // Add the transaction to the database
                            int transId = transactionDataModel.addTransaction(
                                year,
                                month,
                                day,
                                time,
                                roundedTotal,
                                customerMoney,
                                roundedChange,
                                transactionItems
                            );

                            // Check if the transaction was added successfully
                            if (transId != -1) {
                                // Update the parent GUI's product table
                                parentGUI.updateProductTable();
                                // Refresh the product selection table
                                populateProductSelectionTable();

                                // Disable transaction-related controls
                                addBtn.setEnabled(false);
                                verifyBtn.setEnabled(false);
                                quantityPicker.setEnabled(false);
                                prodIDTxtField.setEnabled(false);
                                submitBtn.setEnabled(false);
                                printReceiptBtn.setEnabled(true);
                                clearBtn.setEnabled(true);
                                isTransactionSubmitted = true;

                                // Display success message with change amount
                                JOptionPane.showMessageDialog(null,
                                    String.format("<html>Transaction submitted successfully! <b>Change: ₱%.2f</b>. You can now print the receipt.</html>", roundedChange),
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (DateTimeException e) {
                            // Display error message for invalid date format
                            JOptionPane.showMessageDialog(null,
                                "Invalid date format. Expected: YYYY-MM-DD HH:MM:SS",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    // Display error message for insufficient payment
                    JOptionPane.showMessageDialog(null, "Input sufficient payment amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Display error message for invalid input
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Display error message for missing payment amount
            JOptionPane.showMessageDialog(null, "There is no input. Input payment amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_submitBtnActionPerformed
    
    /**
     * Handles the action when the "Clear" button is clicked.
     * Resets the transaction or clears the cart based on the transaction state.
     *
     * @param evt The ActionEvent triggered by clicking the "Clear" button
     */ 
    private void clearBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // Reset the entire GUI if the transaction is submitted
        if (isTransactionSubmitted) {
            resetGUI();
        } else {
            // Clear only the cart and fields if the transaction is not submitted
            clearCartAndFields();
        }
    }//GEN-LAST:event_clearBtnActionPerformed
    
    /**
     * Handles the action when the "Add" button is clicked.
     * Adds the selected product to the transaction cart and updates the total.
     *
     * @param evt The ActionEvent triggered by clicking the "Add" button
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Get the selected quantity
        int quantity = (int) quantityPicker.getValue();
        // Validate that the quantity is positive
        if (quantity <= 0) {
            // Display error message for invalid quantity
            JOptionPane.showMessageDialog(null, "Input Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get and validate the product ID
        int prodId;
        try {
            prodId = Integer.parseInt(prodIDTxtField.getText());
        } catch (NumberFormatException e) {
            // Display error message for invalid product ID
            JOptionPane.showMessageDialog(null, "Invalid Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Find the selected product
        ProductData selectedProduct = productDataModel.getList().stream()
            .filter(p -> p.getProductId() == prodId)
            .findFirst()
            .orElse(null);
        
        // Validate that the product exists
        if (selectedProduct == null) {
            // Display error message if the product is not found
            JOptionPane.showMessageDialog(null, "Product not found or no longer exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate that the requested quantity is available
        if (quantity > selectedProduct.getProductQuantity()) {
            // Display error message for insufficient stock
            JOptionPane.showMessageDialog(null,
                String.format("Requested quantity (%d) exceeds available stock (%d)!", quantity, selectedProduct.getProductQuantity()),
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new transaction item
        TransactionItemData item = new TransactionItemData(
            0, // Item ID is auto-incremented
            0, // Transaction ID will be set upon insertion
            selectedProduct.getProductId(),
            selectedProduct.getProductName(),
            selectedProduct.getProductBrand(),
            selectedProduct.getProductSize(),
            selectedProduct.getProductType(),
            quantity,
            selectedProduct.getProductPrice(),
            selectedProduct.getProductPrice() * quantity
        );
        // Add the item to the transaction items list
        transactionItems.add(item);

        // Update the cart table
        DefaultTableModel cartTblModel = (DefaultTableModel) cartTbl.getModel();
        cartTblModel.addRow(new Object[] {
            selectedProduct.getProductName(),
            quantity,
            selectedProduct.getProductPrice() * quantity
        });

        // Calculate and update the total purchase amount
        totalPurchase = transactionDataModel.getTotal(transactionItems);
        totalAmountTxtField.setText(String.format("%.2f", totalPurchase));

        // Clear input fields and reset controls
        prodIDTxtField.setText("");
        prodNameTxtField.setText("");
        itemPriceTxtField.setText("");
        ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
        quantityPicker.setValue(0);
        quantityPicker.setEnabled(false);
        addBtn.setEnabled(false);
        productSelectionTbl.clearSelection();
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Handles the action when the "Verify" button is clicked.
     * Verifies the product ID and displays product details if found.
     *
     * @param evt The ActionEvent triggered by clicking the "Verify" button
     */
    private void verifyBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_verifyBtnActionPerformed
        // Get and trim the product ID input
        String prodIDCheck = prodIDTxtField.getText().trim();
        // Validate that the product ID is a valid integer
        if (!dataValidator.isInteger(prodIDCheck)) {
            // Display error message for invalid product ID
            JOptionPane.showMessageDialog(null, "Invalid Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
            // Clear input fields and reset controls
            prodIDTxtField.setText("");
            prodNameTxtField.setText("");
            itemPriceTxtField.setText("");
            ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
            quantityPicker.setValue(0);
            quantityPicker.setEnabled(false);
            addBtn.setEnabled(false);
            productSelectionTbl.clearSelection();
            return;
        }

        // Parse the product ID
        int prodId = Integer.parseInt(prodIDCheck);
        // Find the product in the database
        ProductData selectedProduct = productDataModel.getList().stream()
            .filter(p -> p.getProductId() == prodId)
            .findFirst()
            .orElse(null);

        // Check if the product was found
        if (selectedProduct != null) {
            // Display success message
            JOptionPane.showMessageDialog(null,
                "Product verified and exists in the database!",
                "Verification Success",
                JOptionPane.INFORMATION_MESSAGE);
            // Update fields with product details
            prodNameTxtField.setText(selectedProduct.getProductName());
            itemPriceTxtField.setText(String.format("%.2f", selectedProduct.getProductPrice()));
            ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(selectedProduct.getProductQuantity());
            quantityPicker.setValue(0);
            // Enable the quantity spinner and add button
            quantityPicker.setEnabled(true);
            addBtn.setEnabled(true);
        } else {
            // Display error message if the product is not found
            JOptionPane.showMessageDialog(null,
                "Product not found or no longer exists!",
                "Verification Error",
                JOptionPane.ERROR_MESSAGE);
            // Clear input fields and reset controls
            prodIDTxtField.setText("");
            prodNameTxtField.setText("");
            itemPriceTxtField.setText("");
            ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
            quantityPicker.setValue(0);
            quantityPicker.setEnabled(false);
            addBtn.setEnabled(false);
            productSelectionTbl.clearSelection();
        }
    }//GEN-LAST:event_verifyBtnActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the product selection table based on the search text and selected column.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Get the table model
        DefaultTableModel prodSelectionTblModel = (DefaultTableModel) productSelectionTbl.getModel();
        // Create a new TableRowSorter for filtering
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(prodSelectionTblModel);
        // Apply the sorter to the table
        productSelectionTbl.setRowSorter(sorter);
        // Get the selected column name
        String columnName = searchPrmtrBox.getSelectedItem().toString();
        // Find the index of the selected column
        int columnIndex = prodSelectionTblModel.findColumn(columnName);
        // Apply a case-insensitive regex filter if the column is valid
        if (columnIndex >= 0) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JScrollPane cartScrollPane;
    private javax.swing.JTable cartTbl;
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTxtField;
    private javax.swing.JLabel design;
    private javax.swing.JLabel itemPriceLabel;
    private javax.swing.JTextField itemPriceTxtField;
    private javax.swing.JLabel paymentAmountLabel;
    private javax.swing.JTextField paymentAmountTxtField;
    private javax.swing.JButton printReceiptBtn;
    private javax.swing.JLabel prodIDLabel;
    private javax.swing.JTextField prodIDTxtField;
    private javax.swing.JLabel prodNameLabel;
    private javax.swing.JTextField prodNameTxtField;
    private javax.swing.JScrollPane productSelectionScrollPane;
    private javax.swing.JTable productSelectionTbl;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JTextField totalAmountTxtField;
    private javax.swing.JPanel transactPanel;
    private javax.swing.JButton verifyBtn;
    // End of variables declaration//GEN-END:variables
}