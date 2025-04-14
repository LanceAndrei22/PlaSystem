package plasystem_gui;

import javax.swing.*;
import java.awt.*;

public class ReceiptInvoiceGUI extends JFrame {
    
    /**
     * Default constructor for the ReceiptInvoiceGUI.
     */
    public ReceiptInvoiceGUI() {
        initComponents(); // Initialize GUI components
    }

    /**
     * Sets the receipt list text in the GUI.
     *
     * @param receiptText The text to be displayed in the receipt list.
     */
    public void setReceiptList(String receiptText) {
        receiptTxtArea.setText(receiptText); // Set the receipt text in the text area
    }

    /**
     * Sets the cash amount in the GUI.
     *
     * @param moneyAmount The cash amount to be displayed.
     */
    public void setCashAmount(Double moneyAmount) {
        cashAmountLabel.setText("" + String.format("₱%.2f", moneyAmount)); // Display the cash amount in the label
    }

    /**
     * Sets the Vat-able sales label in the GUI.
     *
     * @param vatableSales The Vat-able sales amount to be displayed.
     */
    public void setVATableSalesLabel(double vatableSales) {
        taxLabel.setText(" " + String.format("₱%.2f", vatableSales)); // Display vatable sales in the tax label
        subTotalLabel.setText(" " + String.format("₱%.2f", vatableSales)); // Display vatable sales in the subtotal label
    }
    
    /**
     * Sets the VAT amount and total amount in the GUI.
     *
     * @param vatableSales The Vat-able sales amount for calculating VAT and total amount.
     */
    public void setVATAmount(double vatableSales) {
        double vat = vatableSales * 0.12; // Calculate VAT (12% of vatable sales)
        double totalAmount = vatableSales + vat; // Calculate total amount (vatable sales + VAT)
        
        vatAmountLabel.setText(" " +String.format("₱%.2f", vat)); // Display VAT amount in the label
        totalLabel.setText(String.format("₱%.2f", totalAmount)); // Display total amount in the label
    }
    
    /**
     * Sets the change amount in the GUI.
     *
     * @param change The change amount to be displayed.
     */
    public void setChangeAmount(double change) {
        changeAmountLabel.setText(String.format("₱%.2f", change)); // Display the change amount in the label
    }
    
    /**
     * Sets the date of the transaction in the GUI.
     *
     * @param dateOfTransaction The date of the transaction to be displayed.
     */
    public void setDateOfTransaction(String dateOfTransaction) {
        currentDataLabel.setText(" " + dateOfTransaction); // Display the transaction date in the label
    }
    
    /**
     * Sets the receipt ID in the GUI.
     *
     * @param receiptID The receipt ID to be displayed.
     */
    public void setReceiptID(String receiptID){
        receiptIDTxtField.setText(receiptID); // Display the receipt ID in the text field
    }
    
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
        totalSaleLabel = new javax.swing.JLabel();
        vatableLabel = new javax.swing.JLabel();
        vatLabel = new javax.swing.JLabel();
        totalAmountLabel = new javax.swing.JLabel();
        dividerLabel = new javax.swing.JLabel();
        cashLabel = new javax.swing.JLabel();
        changeLabel = new javax.swing.JLabel();
        subTotalLabel = new javax.swing.JLabel();
        taxLabel = new javax.swing.JLabel();
        vatAmountLabel = new javax.swing.JLabel();
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
        receiptIDTxtField.setText("0415-777-452");

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

        totalSaleLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalSaleLabel.setText("Total Sale");

        vatableLabel.setForeground(new java.awt.Color(51, 51, 51));
        vatableLabel.setText("VATable (v) :");

        vatLabel.setForeground(new java.awt.Color(51, 51, 51));
        vatLabel.setText("VAT :");

        totalAmountLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalAmountLabel.setText("Total Amount :");

        dividerLabel.setForeground(new java.awt.Color(51, 51, 51));
        dividerLabel.setText("-------------------------------------------------------------------------------");

        cashLabel.setForeground(new java.awt.Color(51, 51, 51));
        cashLabel.setText("Cash :");

        changeLabel.setForeground(new java.awt.Color(51, 51, 51));
        changeLabel.setText("Change :");

        subTotalLabel.setForeground(new java.awt.Color(51, 51, 51));
        subTotalLabel.setText("subtotal");

        taxLabel.setForeground(new java.awt.Color(51, 51, 51));
        taxLabel.setText("tax");

        vatAmountLabel.setForeground(new java.awt.Color(51, 51, 51));
        vatAmountLabel.setText("vat");

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
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/SOFTWARE (1000 x 500 px) (4).png"))); // NOI18N

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
                        .addComponent(vatableLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(taxLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(receiptIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(receiptScrollPane))
                        .addGap(24, 24, 24))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(vatLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vatAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(totalSaleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(cashLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cashAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(totalAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(receiptPanelLayout.createSequentialGroup()
                                        .addComponent(dateLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(currentDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dividerLabel))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(changeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(changeAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
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
                .addComponent(receiptScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatableLabel)
                    .addComponent(taxLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSaleLabel)
                    .addComponent(subTotalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vatLabel)
                    .addComponent(vatAmountLabel))
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
                    .addComponent(currentDataLabel))
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
    private javax.swing.JLabel subTotalLabel;
    private javax.swing.JLabel taxLabel;
    private javax.swing.JLabel totalAmountLabel;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalSaleLabel;
    private javax.swing.JLabel vatAmountLabel;
    private javax.swing.JLabel vatLabel;
    private javax.swing.JLabel vatableLabel;
    // End of variables declaration//GEN-END:variables
}
