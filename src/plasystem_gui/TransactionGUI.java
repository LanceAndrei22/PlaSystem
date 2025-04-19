package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.ProductDataManager;
import plasystem_functions.RandomIDGenerator;
import plasystem_functions.TransactionHandling;
import plasystem_functions.TableAlignmentRenderer;
import plasystem_functions.FrameExporter;
import plasystem_functions.ProductData;
import plasystem_functions.ProductRowSelector;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import javax.swing.table.*;

/**
 * Represents a GUI for handling transactions.
 */
public class TransactionGUI extends JFrame {
    // Attributes for handling data
    private LinkedList<ProductData> list;
    TransactionHandling handling = new TransactionHandling();
    ErrorValueHandling isDataValid = new ErrorValueHandling();
    RandomIDGenerator receiptID = new RandomIDGenerator();
    private LinkedList<TransactionHandling> transactionList = new LinkedList<>();
    private int quantityChecker;
    private String path;
    private JTable tableData;
    private Double customerMoney;
    private Double totalPurchase;
    private ProductDataManager dataHandling;
    
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
     * @param list      The list of ProductData for transaction handling.
     * @param path      The path for file handling.
     * @param tableData The JTable for displaying transaction data.
     */    
    public TransactionGUI(LinkedList<ProductData> list,String path, JTable tableData){
        initComponents(); // Initialize GUI components
        setLocationRelativeTo(null); // Set the frame's location to the center of the screen
        this.list = list; // Set the ProductData list
        this.path = path; // Set the file path
        this.tableData = tableData; // Set the JTable for transaction data
        this.dataHandling = new ProductDataManager(path); // Instantiate ProductDataManager with the file path
        dateTxtField.setText(LocalDate.now().toString()); // Set the current date
        
        // Disable certain buttons and textfields initially
        addBtn.setEnabled(false);
        totalAmountTxtField.setEnabled(false);
        prodNameTxtField.setEnabled(false);
        itemPriceTxtField.setEnabled(false);
        dateTxtField.setEnabled(false);
        cartTable.setEnabled(false);
        printReceiptBtn.setEnabled(false);
        
        // Populate the product table with the list of products
        populateProductTable();
        
        // Add a change listener to ensure quantityPicker accepts only non-negative values
        quantityPicker.addChangeListener((ChangeEvent e) -> {
            int value = (int) quantityPicker.getValue();
            if (value < 0) {
                quantityPicker.setValue(0); // Set the value to 0 if it's negative
            }
        });
        
        // Add a selection listener so that when a row is selected, we update the prodIDTxtField using ProductRowSelector
        productSelectionTbl.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && productSelectionTbl.getSelectedRow() != -1) {
                ProductRowSelector selector = new ProductRowSelector(productSelectionTbl);
                prodIDTxtField.setText(selector.getTblProductID());
                // You could also update other fields (e.g., name, price) if needed.
            }
        });
    }
    
     /**
     * Populates the product table (plasystemTbl) with all products from the list.
     */
    private void populateProductTable() {
       // Reload the latest list from the file
       list = dataHandling.getList();
       DefaultTableModel model = (DefaultTableModel) productSelectionTbl.getModel();
       model.setRowCount(0);  // Clear any existing rows
       for (ProductData product : list) {
           Object[] rowData = {
               product.getProductID(),
               product.getProductName(),
               product.getProductBrand(),
               product.getProductSize(),
               product.getProductType(),
               product.getProductPrice(),
               product.getProductQuantity(),
               product.getProductRestockValue()
           };
           model.addRow(rowData);
        
           new TableAlignmentRenderer(productSelectionTbl, 5);
           new TableAlignmentRenderer(cartTable, 2);
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
        searchTxtField9 = new javax.swing.JTextField();
        searchPrmtrBox9 = new javax.swing.JComboBox<>();
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
        productSelectionTbl.getTableHeader().setReorderingAllowed(false);
        productSelectionScrollPane.setViewportView(productSelectionTbl);

        searchTxtField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtField9KeyReleased(evt);
            }
        });

        searchPrmtrBox9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Size", "Brand", "Type", "Price", "Quantity" }));

        javax.swing.GroupLayout transactPanelLayout = new javax.swing.GroupLayout(transactPanel);
        transactPanel.setLayout(transactPanelLayout);
        transactPanelLayout.setHorizontalGroup(
            transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(transactPanelLayout.createSequentialGroup()
                        .addComponent(searchTxtField9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPrmtrBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(cartScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(42, 42, 42))
        );
        transactPanelLayout.setVerticalGroup(
            transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(transactPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(transactPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        // Instantiate ReceiptInvoiceGUI
        ReceiptInvoiceGUI receipt = new ReceiptInvoiceGUI();
        
        // Format and set content to be displayed on the receipt
        StringBuilder content = new StringBuilder();
        for (TransactionHandling item : transactionList) {
            // Get the item name and ensure it's limited to 14 characters and without leading/trailing spaces
            String itemName = item.getName().trim().replace(" ", "");
            itemName = itemName.length() > 14 ? itemName.substring(0, 14) : itemName;

            // Format each item: name (limited to 14 characters, without spaces), quantity, and price
            String formattedItem = String.format("%-35s\t     %-10s\t                  %-10s\n",
                                                 itemName, item.getQuantity(),
                                                 String.format("₱%.2f", item.getPrice() * item.getQuantity()));
            content.append(formattedItem);
        }
        // Set the receipt list content
        receipt.setReceiptList(content.toString());
        
        // Get the cash amount from the input field
        double cashAmount = Double.parseDouble(paymentAmountTxtField.getText());
        
        // Set the cash amount in the receipt
        receipt.setCashAmount(cashAmount);
        
        // Calculate VATable Sales
        double vatableSales = totalPurchase / 1.12;
        // Set VATable Sales in the receipt
        receipt.setVATableSalesLabel(vatableSales);
        
        // Pass the VAT amount to ReceiptGUI
        receipt.setVATAmount(vatableSales); 

        // Calculate the change
        double change = cashAmount - (vatableSales + vatableSales * 0.12);
        // Display the change in the receipt
        receipt.setChangeAmount(change);
        
        // Get the date of the transaction
        String dateOfTransaction = dateTxtField.getText();
        // Set the date of the transaction in the receipt
        receipt.setDateOfTransaction(dateOfTransaction);
        
        // Generate and Set Receipt ID
        String generatedReceiptID = receiptID.generateReceiptID();
        receipt.setReceiptID(generatedReceiptID);
       
        // Display the receipt
        receipt.setVisible(true);
        receipt.setLocationRelativeTo(null);
        receipt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Export the frame as an image using FrameExporter
        FrameExporter.exportFrameAsImage(receipt, generatedReceiptID);
    }//GEN-LAST:event_printReceiptBtnActionPerformed
    
    /**
     * ActionListener method triggered when the 'Cancel' button is clicked in the GUI.
     *
     * @param evt The ActionEvent captured from the GUI.
     */
    private void cancelBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose(); // Close the current window (the frame)
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    /**
     * Action performed when the "Submit" button is clicked.
     * Handles the transaction submission and quantity adjustments.
     *
     * @param evt Action event triggered by the button click
     */
    private void submitBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // Check if the transaction list is empty
        if(transactionList.isEmpty()){
            JOptionPane.showMessageDialog(null, "Transaction is Empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Check if payment amount is provided
        if (!paymentAmountTxtField.getText().trim().isEmpty()) {
            String customerMoneyStr = paymentAmountTxtField.getText().trim();
            String totalPurchaseStr = totalAmountTxtField.getText().trim();
            
            // Validate customer money and total purchase inputs
            if (isDataValid.isDouble(customerMoneyStr) && isDataValid.isDouble(totalPurchaseStr)) {
                customerMoney = Double.parseDouble(customerMoneyStr);
                totalPurchase = Double.parseDouble(totalPurchaseStr);
                
                // Check if customer's payment is sufficient
                if (customerMoney >= totalPurchase) {
                    // Confirm transaction submission
                    int confirm = JOptionPane.showConfirmDialog(null, "Do you wish to submit transaction?", "Submit", JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Decrease the quantity of purchased items in the data
                        ProductDataManager dataHandling = new ProductDataManager(path);
                        dataHandling.decreaseQuantity(list, transactionList, tableData);
                        
                        // Update the product table dynamically using the helper method
                        populateProductTable();
                        
                        // Disable further actions and enable printing the receipt
                        addBtn.setEnabled(false);
                        submitBtn.setEnabled(false);
                        paymentAmountTxtField.setEnabled(false);
                        verifyBtn.setEnabled(false);
                        clearBtn.setEnabled(false);
                        prodIDTxtField.setEnabled(false);
                        quantityPicker.setEnabled(false);
                        printReceiptBtn.setEnabled(true);
                        cancelBtn.setText("EXIT");
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
        // Clear the transaction list and reset the transaction table
        transactionList.clear();
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0); // Clear all rows in the table
        
        // Reset input fields and text areas
        totalAmountTxtField.setText("");
        prodIDTxtField.setText("");
        itemPriceTxtField.setText("");
        prodNameTxtField.setText("");
        quantityPicker.setValue(0);
        
        // Disable the "Add" button after clearing
        addBtn.setEnabled(false);
    }//GEN-LAST:event_clearBtnActionPerformed
    
    /**
     * Action performed when the "Add" button is clicked.
     * Adds the selected item to the transaction list and updates the preview area.
     *
     * @param evt Action event triggered by the button click
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Checking if quantity is provided
        if((int) quantityPicker.getValue() == 0){
            JOptionPane.showMessageDialog(null, "Input Quantity!", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            // Creating TransactionHandling object with provided details
            TransactionHandling data = new TransactionHandling(
                prodIDTxtField.getText(),
                prodNameTxtField.getText(),
                (int) quantityPicker.getValue(),
                Double.parseDouble(itemPriceTxtField.getText())
            );

            // Adding data to the transaction list
            transactionList.add(data);
            
            // Updating the preview area in the table
            DefaultTableModel model = (DefaultTableModel) cartTable.getModel();

            // Fetching details from input fields
            String prodName = prodNameTxtField.getText();
            int quantity = (Integer) quantityPicker.getValue();
            double itemPrice = Double.parseDouble(itemPriceTxtField.getText());

            // Calculating total for this item
            double customTotal = itemPrice * quantity;

            // Creating a new row with item details
            Object[] row = new Object[] {prodName, quantity, customTotal};

            // Adding the row to the table model
            model.addRow(row);

            // Showing the total amount in the designated field
            totalAmountTxtField.setText(String.valueOf(handling.getTotal(transactionList)));

            // Resetting input fields
            prodIDTxtField.setText("");
            itemPriceTxtField.setText("");
            prodNameTxtField.setText("");
            quantityPicker.setValue(0);

            // Disabling the "Add" button
            addBtn.setEnabled(false);
        }
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Action performed when the "Verify" button is clicked.
     * Checks if the product exists and displays its details if found.
     *
     * @param evt Action event triggered by the button click
     */
    private void verifyBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_verifyBtnActionPerformed
        // Fetching the product ID from the input field
        String prodIDCheck = prodIDTxtField.getText();
        boolean check = false;
        
        // Looping through the list to find the matching product
        for(ProductData element: list){
            if(element.getProductID().equals(prodIDCheck) ){
                // If product exists, display its details
                check = true;
                itemPriceTxtField.setText(Double.toString(element.getProductPrice()));
                prodNameTxtField.setText(element.getProductName());
                quantityChecker = element.getProductQuantity();
                addBtn.setEnabled(true); // Enable the "Add" button for valid product
                break;
            }
        }

        // Set the maximum number of items they can buy based on available quantity
        ((SpinnerNumberModel) quantityPicker.getModel()).setMaximum(quantityChecker);

        // If the product doesn't exist, display an error message
        if(!check){
            JOptionPane.showMessageDialog(null, "Product not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_verifyBtnActionPerformed

    private void searchTxtField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtField9KeyReleased
        DefaultTableModel model = (DefaultTableModel) productSelectionTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        productSelectionTbl.setRowSorter(sorter);
        
        // Get the selected search parameter (which should match one of the table's column names)
        String columnName = searchPrmtrBox9.getSelectedItem().toString();
        int columnIndex = model.findColumn(columnName);
        if(columnIndex >= 0) {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField9.getText(), columnIndex));
        }
    }//GEN-LAST:event_searchTxtField9KeyReleased
      
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
    private javax.swing.JComboBox<String> searchPrmtrBox9;
    private javax.swing.JTextField searchTxtField9;
    private javax.swing.JButton submitBtn;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JTextField totalAmountTxtField;
    private javax.swing.JPanel transactPanel;
    private javax.swing.JButton verifyBtn;
    // End of variables declaration//GEN-END:variables
}
