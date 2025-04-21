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
 * Represents a GUI for handling transactions.
 */
public class TransactionGUI extends JFrame {
    // Attributes for handling data
    private List<ProductData> productList;
    private TransactionDataManager transactionManager;
    private ErrorValueHandling isDataValid;
    private JTable mainTable;
    private List<TransactionItemData> transactionItems;
    private double totalPurchase;
    private ProductDataManager productDataManager;
    private Timer refreshTimer;
    private boolean isTransactionSubmitted;
    
    /**
     * Default constructor for the EditDataGUI.
     */
    public TransactionGUI(){
        initComponents(); // Initialize GUI components
        setLocationRelativeTo(null); // Set the frame's location to the center of the screen
    }
    
    /**
     * Creates a new instance of TransactionGUI with specific parameters.
     *
     * @param productList The list of ProductData for transaction handling.
     * @param mainTable   The JTable for displaying main product data.
     */
    public TransactionGUI(List<ProductData> productList, JTable mainTable){
        this.productList = productList;
        this.mainTable = mainTable;
        this.transactionManager = new TransactionDataManager();
        this.isDataValid = new ErrorValueHandling();
        this.transactionItems = new LinkedList<>();
        this.productDataManager = new ProductDataManager();
        initComponents();
        setLocationRelativeTo(null);
        initializeGUI();
        startTableRefresh();
    }
    
    private void initializeGUI() {
        // Set date field with formatted current date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTxtField.setText(now.format(formatter));
        dateTxtField.setEnabled(false);
        
        addBtn.setEnabled(false);
        totalAmountTxtField.setEnabled(false);
        prodNameTxtField.setEnabled(false);
        itemPriceTxtField.setEditable(false);
        dateTxtField.setEnabled(false);
        cartTable.setEnabled(false);
        printReceiptBtn.setEnabled(false);
        transactionItems.clear();
        ((DefaultTableModel) cartTable.getModel()).setRowCount(0);
        totalPurchase = 0.0;
        totalAmountTxtField.setText("");

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 0, 1);
        quantityPicker.setModel(spinnerModel);
        quantityPicker.setEnabled(false);
        quantityPicker.addChangeListener(e -> {
            int value = (int) quantityPicker.getValue();
            if (value < 0) {
                quantityPicker.setValue(0);
            }
        });

        productSelectionTbl.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && productSelectionTbl.getSelectedRow() != -1) {
                ProductRowSelector selector = new ProductRowSelector(productSelectionTbl);
                ProductData selectedProduct = selector.getProductData();
                if (selector.getRow() != -1) {
                    prodIDTxtField.setText(String.valueOf(selectedProduct.getProductId()));
                    // Clear other fields until verified
                    prodNameTxtField.setText("");
                    itemPriceTxtField.setText("");
                    ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
                    quantityPicker.setValue(0);
                    quantityPicker.setEnabled(false);
                    addBtn.setEnabled(false);
                } else {
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

        populateProductTable();
    }
    
    /**
     * Starts a timer to periodically refresh the product table with the latest data.
     */
    private void startTableRefresh() {
        refreshTimer = new Timer(true); // Daemon thread
        refreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // Set date field with formatted current date
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    dateTxtField.setText(now.format(formatter));
                    
                    productDataManager.loadProducts(); // Force reload from database
                    productList = productDataManager.getList();
                    populateProductTable();
                });
            }
        }, 0, 1000); // Refresh every 1 second
    }
    
    /**
     * Stops the table refresh timer when the GUI is closed.
     */
    @Override
    public void dispose() {
        if (refreshTimer != null) {
            refreshTimer.cancel();
        }
        // Clear transactionItems and cart table on disposal
        transactionItems.clear();
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        totalPurchase = 0.00;
        if (totalAmountTxtField != null) {
            totalAmountTxtField.setText("");
        }
        super.dispose();
    }
    
     /**
     * Populates the product table (productSelectionTbl) with all products from the list.
     */
    private void populateProductTable() {
    DefaultTableModel model = (DefaultTableModel) productSelectionTbl.getModel();
        model.setRowCount(0);
        for (ProductData product : productList) {
            model.addRow(new Object[] {
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
        
        // Apply ProductTableRenderer for productSelectionTbl with total width of 752 pixels
        new ProductTableRenderer(productSelectionTbl, productList, 752);
        // Apply custom renderer for cartTable
        CartTableRenderer.applyCartTableRendering(cartTable);
        
        // Reapply the current filter if any
        if (searchTxtField.getText().trim().length() > 0) {
            searchTxtFieldKeyReleased(null);
        }
   }
    
    private void clearCartAndFields() {
        transactionItems.clear();
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);
        totalPurchase = 0.0;
        totalAmountTxtField.setText("");
        prodIDTxtField.setEnabled(true);
        prodIDTxtField.setText("");
        prodNameTxtField.setText("");
        itemPriceTxtField.setText("");
        ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(0);
        quantityPicker.setValue(0);
        quantityPicker.setEnabled(false);
        addBtn.setEnabled(false);
        productSelectionTbl.clearSelection();
    }

    private void resetGUI() {
        clearCartAndFields();
        paymentAmountTxtField.setText("");
        submitBtn.setEnabled(true);
        paymentAmountTxtField.setEnabled(true);
        verifyBtn.setEnabled(true);
        clearBtn.setEnabled(true);
        printReceiptBtn.setEnabled(false);
        cancelBtn.setText("CANCEL");
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
        cartTable = new javax.swing.JTable();
        productSelectionScrollPane = new javax.swing.JScrollPane();
        productSelectionTbl = new javax.swing.JTable();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        titleLabel = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        printReceiptBtn = new javax.swing.JButton();
        submitBtn = new javax.swing.JButton();
        Design = new javax.swing.JLabel();

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

        cartTable.setFont(new java.awt.Font("Lucida Sans Typewriter", 0, 10)); // NOI18N
        cartTable.setForeground(new java.awt.Color(0, 0, 0));
        cartTable.setModel(new javax.swing.table.DefaultTableModel(
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
        cartTable.getTableHeader().setReorderingAllowed(false);
        cartScrollPane.setViewportView(cartTable);
        if (cartTable.getColumnModel().getColumnCount() > 0) {
            cartTable.getColumnModel().getColumn(1).setPreferredWidth(20);
            cartTable.getColumnModel().getColumn(2).setPreferredWidth(25);
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

        Design.setBackground(new java.awt.Color(255, 153, 153));
        Design.setOpaque(true);

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
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
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
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 598, Short.MAX_VALUE)))
        );

        transactPanel.setBackground(new Color(255, 102, 102, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Action performed when the "Print Receipt" button is clicked.
     * Constructs and displays a receipt based on transaction data.
     *
     * @param evt Action event triggered by the button click
     */
    private void printReceiptBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_printReceiptBtnActionPerformed
        ReceiptInvoiceGUI receipt = new ReceiptInvoiceGUI();
        StringBuilder content = new StringBuilder();
        for (TransactionItemData item : transactionItems) {
            String itemName = item.getTI_productName().trim().replace(" ", "");
            itemName = itemName.length() > 14 ? itemName.substring(0, 14) : itemName;
            String formattedItem = String.format("%-35s\t     %-10s\t                  %-10s\n",
                itemName, item.getTI_buyQuantity(),
                String.format("₱%.2f", item.getTI_totalPrice()));
            content.append(formattedItem);
        }
        receipt.setReceiptList(content.toString());

        double cashAmount = Double.parseDouble(paymentAmountTxtField.getText());
        receipt.setCashAmount(cashAmount);

        // Round totalPurchase to two decimal places
        double roundedTotalPurchase = new BigDecimal(totalPurchase)
            .setScale(2, RoundingMode.HALF_UP).doubleValue();
        receipt.setTotalAmount(roundedTotalPurchase);

        // Round change to two decimal places
        double change = new BigDecimal(cashAmount - totalPurchase)
            .setScale(2, RoundingMode.HALF_UP).doubleValue();
        receipt.setChangeAmount(change);

        // Use formatted date for receipt
        TransactionData lastTransaction = transactionManager.getTransactionList().get(
            transactionManager.getTransactionList().size() - 1);
        receipt.setDateOfTransaction(lastTransaction.getFormattedDate());

        int transId = lastTransaction.getTransactionId();
        receipt.setReceiptID(String.valueOf(transId));

        receipt.setVisible(true);
        receipt.setLocationRelativeTo(null);
        receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        FrameExporter.exportFrameAsImage(receipt, "#" + String.valueOf(transId));

        // Reset GUI after printing receipt
        resetGUI();
    }//GEN-LAST:event_printReceiptBtnActionPerformed
    
    /**
     * ActionListener method triggered when the 'Cancel' button is clicked in the GUI.
     *
     * @param evt The ActionEvent captured from the GUI.
     */
    private void cancelBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose(); // Close the current window
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    /**
     * Action performed when the "Submit" button is clicked.
     * Handles the transaction submission and quantity adjustments.
     *
     * @param evt Action event triggered by the button click
     */
    private void submitBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        if (transactionItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Transaction is Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!paymentAmountTxtField.getText().trim().isEmpty()) {
            String customerMoneyStr = paymentAmountTxtField.getText().trim();
            String totalPurchaseStr = totalAmountTxtField.getText().trim();

            if (isDataValid.isDouble(customerMoneyStr) && isDataValid.isDouble(totalPurchaseStr)) {
                double customerMoney = Double.parseDouble(customerMoneyStr);
                totalPurchase = Double.parseDouble(totalPurchaseStr);

                if (customerMoney >= totalPurchase) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Do you wish to submit transaction?", "Submit", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Split date into components
                        String dateStr = dateTxtField.getText();
                        try {
                            LocalDateTime dateTime = LocalDateTime.parse(dateStr, 
                                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            String year = String.valueOf(dateTime.getYear());
                            String month = String.format("%02d", dateTime.getMonthValue());
                            String day = String.format("%02d", dateTime.getDayOfMonth());
                            String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                            
                            // Round totalPurchase and changeAmount to two decimal places
                            double roundedTotal = new BigDecimal(totalPurchase).setScale(2, RoundingMode.HALF_UP).doubleValue();
                            double roundedChange = new BigDecimal(customerMoney - totalPurchase).setScale(2, RoundingMode.HALF_UP).doubleValue();
                            
                            int transId = transactionManager.addTransaction(
                                year,
                                month,
                                day,
                                time,
                                roundedTotal,
                                customerMoney,
                                roundedChange,
                                transactionItems
                            );

                            if (transId != -1) {
                                for (TransactionItemData item : transactionItems) {
                                    for (ProductData product : productList) {
                                        if (product.getProductId() == item.getTI_productId()) {
                                            int newQuantity = product.getProductQuantity() - item.getTI_buyQuantity();
                                            productDataManager.updateProduct(
                                                product.getProductId(),
                                                product.getProductName(),
                                                product.getProductBrand(),
                                                product.getProductSize(),
                                                product.getProductType(),
                                                product.getProductPrice(),
                                                newQuantity,
                                                product.getProductRestockValue()
                                            );
                                            break;
                                        }
                                    }
                                }

                                productDataManager.updateTable(mainTable);
                                populateProductTable();

                                // Disable controls to prevent further modifications
                                addBtn.setEnabled(false);
                                verifyBtn.setEnabled(false);
                                quantityPicker.setEnabled(false);
                                prodIDTxtField.setEnabled(false);
                                submitBtn.setEnabled(false);
                                printReceiptBtn.setEnabled(true);
                                clearBtn.setEnabled(true);
                                isTransactionSubmitted = true;

                                JOptionPane.showMessageDialog(null,
                                    String.format("<html>Transaction submitted successfully! <b>Change: ₱%.2f</b>. You can now print the receipt.</html>", roundedChange),
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            }
                        } catch (DateTimeException e) {
                            JOptionPane.showMessageDialog(null,
                                "Invalid date format. Expected: YYYY-MM-DD HH:MM:SS",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Input sufficient payment amount!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no input. Input payment amount!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_submitBtnActionPerformed
    
    /**
     * Action performed when the "Clear" button is clicked.
     * Resets the transaction and clears input fields.
     *
     * @param evt Action event triggered by the button click
     */  
    private void clearBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        if (isTransactionSubmitted) {
            resetGUI();
        } else {
            clearCartAndFields();
        }
    }//GEN-LAST:event_clearBtnActionPerformed
    
    /**
     * Action performed when the "Add" button is clicked.
     * Adds the selected item to the transaction list and updates the preview area.
     *
     * @param evt Action event triggered by the button click
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        int quantity = (int) quantityPicker.getValue();
        if (quantity <= 0) {
            JOptionPane.showMessageDialog(null, "Input Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int prodId;
        try {
            prodId = Integer.parseInt(prodIDTxtField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ProductData selectedProduct = null;
        for (ProductData product : productList) {
            if (product.getProductId() == prodId) {
                selectedProduct = product;
                break;
            }
        }
        
        if (selectedProduct == null) {
            JOptionPane.showMessageDialog(null, "Product not found or no longer exists!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (quantity > selectedProduct.getProductQuantity()) {
            JOptionPane.showMessageDialog(null,
                String.format("Requested quantity (%d) exceeds available stock (%d)!", quantity, selectedProduct.getProductQuantity()),
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
        transactionItems.add(item);

        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.addRow(new Object[] {
            selectedProduct.getProductName(),
            quantity,
            selectedProduct.getProductPrice() * quantity
        });

        totalPurchase = transactionManager.getTotal(transactionItems);
        totalAmountTxtField.setText(String.format("%.2f", totalPurchase));

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
     * Action performed when the "Verify" button is clicked.
     * Checks if the product exists and displays its details if found.
     *
     * @param evt Action event triggered by the button click
     */
    private void verifyBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_verifyBtnActionPerformed
        String prodIDCheck = prodIDTxtField.getText().trim();
        if (!isDataValid.isInteger(prodIDCheck)) {
            JOptionPane.showMessageDialog(null, "Invalid Product ID!", "Error", JOptionPane.ERROR_MESSAGE);
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

        int prodId = Integer.parseInt(prodIDCheck);
        ProductData selectedProduct = null;
        for (ProductData product : productList) {
            if (product.getProductId() == prodId) {
                selectedProduct = product;
                break;
            }
        }

        if (selectedProduct != null) {
            JOptionPane.showMessageDialog(null,
                "Product verified and exists in the database!",
                "Verification Success",
                JOptionPane.INFORMATION_MESSAGE);
            prodNameTxtField.setText(selectedProduct.getProductName());
            itemPriceTxtField.setText(String.format("%.2f", selectedProduct.getProductPrice()));
            ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(selectedProduct.getProductQuantity());
            quantityPicker.setValue(0);
            quantityPicker.setEnabled(true);
            addBtn.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null,
                "Product not found or no longer exists!",
                "Verification Error",
                JOptionPane.ERROR_MESSAGE);
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

    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        DefaultTableModel model = (DefaultTableModel) productSelectionTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productSelectionTbl.setRowSorter(sorter);
        String columnName = searchPrmtrBox.getSelectedItem().toString();
        int columnIndex = model.findColumn(columnName);
        if (columnIndex >= 0) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JScrollPane cartScrollPane;
    private javax.swing.JTable cartTable;
    private javax.swing.JButton clearBtn;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTxtField;
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