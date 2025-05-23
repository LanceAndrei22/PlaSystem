package plasystem_gui;

import javax.swing.*;

/**
 * A graphical user interface (GUI) window for displaying a receipt invoice.
 * This class provides a formatted display of transaction details, including items,
 * total amount, cash paid, change, receipt ID, and transaction date.
 */
public class ReceiptInvoiceGUI extends JFrame {
    
    /**
     * Default constructor that initializes the ReceiptInvoiceGUI.
     */
    public ReceiptInvoiceGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
    }

    /**
     * Sets the receipt list text in the GUI.
     *
     * @param receiptText The text to be displayed in the receipt list
     */
    public void setReceiptList(String receiptText) {
        // Update the text area with the provided receipt text
        receiptTxtArea.setText(receiptText);
    }

    /**
     * Sets the cash amount in the GUI.
     *
     * @param moneyAmount The cash amount to be displayed
     */
    public void setCashAmount(Double moneyAmount) {
        // Format the cash amount to two decimal places and display it in the label
        cashAmountLabel.setText(String.format("₱%.2f", moneyAmount));
    }

    /**
     * Sets the total amount in the GUI.
     *
     * @param totalAmount The total amount to be displayed
     */
    public void setTotalAmount(double totalAmount) {
        // Format the total amount to two decimal places and display it in the label
        totalLabel.setText(String.format("₱%.2f", totalAmount));
    }

    /**
     * Sets the change amount in the GUI.
     *
     * @param change The change amount to be displayed
     */
    public void setChangeAmount(double change) {
        // Format the change amount to two decimal places and display it in the label
        changeAmountLabel.setText(String.format("₱%.2f", change));
    }
    
    /**
     * Sets the date of the transaction in the GUI.
     *
     * @param dateOfTransaction The date of the transaction to be displayed
     */
    public void setDateOfTransaction(String dateOfTransaction) {
        // Display the transaction date in the label with a leading space
        currentDataLabel.setText(" " + dateOfTransaction);
    }
    
    /**
     * Sets the receipt ID in the GUI.
     *
     * @param receiptID The receipt ID to be displayed
     */
    public void setReceiptID(String receiptID){
        // Display the receipt ID in the label
        receiptIDTxtField.setText(receiptID);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelSubtotal1 = new javax.swing.JLabel();
        receiptPanel = new javax.swing.JPanel();
        locationLabel = new javax.swing.JLabel();
        receiptIDTxtField = new javax.swing.JLabel();
        itemLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        receiptScrollPane = new javax.swing.JScrollPane();
        receiptTxtArea = new javax.swing.JTextArea();
        totalAmountLabel = new javax.swing.JLabel();
        dividerLabel = new javax.swing.JLabel();
        cashLabel = new javax.swing.JLabel();
        changeLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        cashAmountLabel = new javax.swing.JLabel();
        changeAmountLabel = new javax.swing.JLabel();
        salesInvoiceLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        currentDataLabel = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();

        jLabelSubtotal1.setText("subtotal");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        receiptPanel.setBackground(new java.awt.Color(255, 255, 255));
        receiptPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51), 2));
        receiptPanel.setForeground(new java.awt.Color(51, 51, 51));

        locationLabel.setForeground(new java.awt.Color(51, 51, 51));
        locationLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        locationLabel.setText("Legazpi City, Albay, Philippines");

        receiptIDTxtField.setForeground(new java.awt.Color(51, 51, 51));
        receiptIDTxtField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        receiptIDTxtField.setText("#number");

        itemLabel.setForeground(new java.awt.Color(51, 51, 51));
        itemLabel.setText("Item");

        quantityLabel.setForeground(new java.awt.Color(51, 51, 51));
        quantityLabel.setText("Quantity");

        priceLabel.setForeground(new java.awt.Color(51, 51, 51));
        priceLabel.setText("Price");

        receiptScrollPane.setForeground(new java.awt.Color(51, 51, 51));

        receiptTxtArea.setEditable(false);
        receiptTxtArea.setBackground(new java.awt.Color(255, 255, 255));
        receiptTxtArea.setColumns(20);
        receiptTxtArea.setForeground(new java.awt.Color(51, 51, 51));
        receiptTxtArea.setLineWrap(true);
        receiptTxtArea.setRows(5);
        receiptTxtArea.setBorder(null);
        receiptTxtArea.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        receiptTxtArea.setEnabled(false);
        receiptScrollPane.setViewportView(receiptTxtArea);

        totalAmountLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalAmountLabel.setText("Total Amount :");

        dividerLabel.setForeground(new java.awt.Color(51, 51, 51));
        dividerLabel.setText("-------------------------------------------------------------------------------");

        cashLabel.setForeground(new java.awt.Color(51, 51, 51));
        cashLabel.setText("Cash :");

        changeLabel.setForeground(new java.awt.Color(51, 51, 51));
        changeLabel.setText("Change :");

        totalLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalLabel.setText("total");

        cashAmountLabel.setForeground(new java.awt.Color(51, 51, 51));
        cashAmountLabel.setText("cash");

        changeAmountLabel.setForeground(new java.awt.Color(51, 51, 51));
        changeAmountLabel.setText("change");

        salesInvoiceLabel.setForeground(new java.awt.Color(51, 51, 51));
        salesInvoiceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salesInvoiceLabel.setText("This serves as a Sales Invoice.");

        dateLabel.setForeground(new java.awt.Color(51, 51, 51));
        dateLabel.setText("Date:");

        currentDataLabel.setForeground(new java.awt.Color(51, 51, 51));
        currentDataLabel.setText("date");

        logoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/logo_1.png"))); // NOI18N

        javax.swing.GroupLayout receiptPanelLayout = new javax.swing.GroupLayout(receiptPanel);
        receiptPanel.setLayout(receiptPanelLayout);
        receiptPanelLayout.setHorizontalGroup(
            receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(itemLabel)
                .addGap(126, 126, 126)
                .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(priceLabel)
                .addGap(63, 63, 63))
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(receiptIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(receiptScrollPane))
                        .addGap(24, 24, 24))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(dateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dividerLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, receiptPanelLayout.createSequentialGroup()
                                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cashLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(changeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cashAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(changeAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(locationLabel))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(salesInvoiceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receiptPanelLayout.setVerticalGroup(
            receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptIDTxtField)
                .addGap(36, 36, 36)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(priceLabel)
                    .addComponent(itemLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dividerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalAmountLabel)
                    .addComponent(totalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashLabel)
                    .addComponent(cashAmountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(changeLabel)
                    .addComponent(changeAmountLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(currentDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(salesInvoiceLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(receiptPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(receiptPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cashAmountLabel;
    private javax.swing.JLabel cashLabel;
    private javax.swing.JLabel changeAmountLabel;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JLabel currentDataLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel dividerLabel;
    private javax.swing.JLabel itemLabel;
    private javax.swing.JLabel jLabelSubtotal1;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JLabel receiptIDTxtField;
    private javax.swing.JPanel receiptPanel;
    private javax.swing.JScrollPane receiptScrollPane;
    private javax.swing.JTextArea receiptTxtArea;
    private javax.swing.JLabel salesInvoiceLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
