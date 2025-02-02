package datamine_gui;

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

        jLabelSubtotal1 = new JLabel();
        receiptPanel = new JPanel();
        titleLabel = new JLabel();
        locationLabel = new JLabel();
        receiptIDTxtField = new JLabel();
        itemLabel = new JLabel();
        quantityLabel = new JLabel();
        priceLabel = new JLabel();
        receiptScrollPane = new JScrollPane();
        receiptTxtArea = new JTextArea();
        totalSaleLabel = new JLabel();
        vatableLabel = new JLabel();
        vatLabel = new JLabel();
        totalAmountLabel = new JLabel();
        dividerLabel = new JLabel();
        cashLabel = new JLabel();
        changeLabel = new JLabel();
        subTotalLabel = new JLabel();
        taxLabel = new JLabel();
        vatAmountLabel = new JLabel();
        totalLabel = new JLabel();
        cashAmountLabel = new JLabel();
        changeAmountLabel = new JLabel();
        salesInvoiceLabel = new JLabel();
        dateLabel = new JLabel();
        currentDataLabel = new JLabel();
        logoLabel = new JLabel();

        jLabelSubtotal1.setText("subtotal");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setResizable(false);

        receiptPanel.setBackground(new Color(255, 255, 255));
        receiptPanel.setBorder(BorderFactory.createLineBorder(new Color(51, 51, 51), 2));
        receiptPanel.setForeground(new Color(51, 51, 51));

        titleLabel.setFont(new Font("Bookman Old Style", 1, 18)); // NOI18N
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("DataMine");

        locationLabel.setForeground(new Color(51, 51, 51));
        locationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        locationLabel.setText("Legazpi City, Albay, Philippines");

        receiptIDTxtField.setForeground(new Color(51, 51, 51));
        receiptIDTxtField.setHorizontalAlignment(SwingConstants.CENTER);
        receiptIDTxtField.setText("0415-777-452");

        itemLabel.setForeground(new Color(51, 51, 51));
        itemLabel.setText("Item");

        quantityLabel.setForeground(new Color(51, 51, 51));
        quantityLabel.setText("Quantity");

        priceLabel.setForeground(new Color(51, 51, 51));
        priceLabel.setText("Price");

        receiptScrollPane.setForeground(new Color(51, 51, 51));

        receiptTxtArea.setEditable(false);
        receiptTxtArea.setBackground(new Color(255, 255, 255));
        receiptTxtArea.setColumns(20);
        receiptTxtArea.setForeground(new Color(51, 51, 51));
        receiptTxtArea.setLineWrap(true);
        receiptTxtArea.setRows(5);
        receiptTxtArea.setBorder(null);
        receiptTxtArea.setDisabledTextColor(new Color(51, 51, 51));
        receiptTxtArea.setEnabled(false);
        receiptScrollPane.setViewportView(receiptTxtArea);

        totalSaleLabel.setForeground(new Color(51, 51, 51));
        totalSaleLabel.setText("Total Sale");

        vatableLabel.setForeground(new Color(51, 51, 51));
        vatableLabel.setText("VATable (v) :");

        vatLabel.setForeground(new Color(51, 51, 51));
        vatLabel.setText("VAT :");

        totalAmountLabel.setForeground(new Color(51, 51, 51));
        totalAmountLabel.setText("Total Amount :");

        dividerLabel.setForeground(new Color(51, 51, 51));
        dividerLabel.setText("-------------------------------------------------------------------------------");

        cashLabel.setForeground(new Color(51, 51, 51));
        cashLabel.setText("Cash :");

        changeLabel.setForeground(new Color(51, 51, 51));
        changeLabel.setText("Change :");

        subTotalLabel.setForeground(new Color(51, 51, 51));
        subTotalLabel.setText("subtotal");

        taxLabel.setForeground(new Color(51, 51, 51));
        taxLabel.setText("tax");

        vatAmountLabel.setForeground(new Color(51, 51, 51));
        vatAmountLabel.setText("vat");

        totalLabel.setForeground(new Color(51, 51, 51));
        totalLabel.setText("total");

        cashAmountLabel.setForeground(new Color(51, 51, 51));
        cashAmountLabel.setText("cash");

        changeAmountLabel.setForeground(new Color(51, 51, 51));
        changeAmountLabel.setText("change");

        salesInvoiceLabel.setForeground(new Color(51, 51, 51));
        salesInvoiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        salesInvoiceLabel.setText("This serves as a Sales Invoice.");

        dateLabel.setForeground(new Color(51, 51, 51));
        dateLabel.setText("Date:");

        currentDataLabel.setForeground(new Color(51, 51, 51));
        currentDataLabel.setText("date");

        logoLabel.setIcon(new ImageIcon(getClass().getResource("/datamine_main/datamineEmblem.png"))); // NOI18N

        GroupLayout receiptPanelLayout = new GroupLayout(receiptPanel);
        receiptPanel.setLayout(receiptPanelLayout);
        receiptPanelLayout.setHorizontalGroup(
            receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(itemLabel)
                .addGap(126, 126, 126)
                .addComponent(quantityLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(priceLabel)
                .addGap(63, 63, 63))
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addComponent(vatableLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(taxLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(receiptIDTxtField, GroupLayout.PREFERRED_SIZE, 372, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 11, Short.MAX_VALUE))
                            .addComponent(receiptScrollPane))
                        .addGap(24, 24, 24))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(vatLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vatAmountLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(totalSaleLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(subTotalLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(cashLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cashAmountLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(totalAmountLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(totalLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(receiptPanelLayout.createSequentialGroup()
                                        .addComponent(dateLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(currentDataLabel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dividerLabel))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(receiptPanelLayout.createSequentialGroup()
                                .addComponent(changeLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(changeAmountLabel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(locationLabel))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(titleLabel))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(logoLabel))
                    .addGroup(receiptPanelLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(salesInvoiceLabel, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        receiptPanelLayout.setVerticalGroup(
            receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(receiptPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptIDTxtField)
                .addGap(36, 36, 36)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(priceLabel)
                    .addComponent(itemLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(receiptScrollPane, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(vatableLabel)
                    .addComponent(taxLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(totalSaleLabel)
                    .addComponent(subTotalLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(vatLabel)
                    .addComponent(vatAmountLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dividerLabel)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(totalAmountLabel)
                    .addComponent(totalLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cashLabel)
                    .addComponent(cashAmountLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(changeLabel)
                    .addComponent(changeAmountLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(receiptPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(currentDataLabel))
                .addGap(18, 18, 18)
                .addComponent(salesInvoiceLabel)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(receiptPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(receiptPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel cashAmountLabel;
    private JLabel cashLabel;
    private JLabel changeAmountLabel;
    private JLabel changeLabel;
    private JLabel currentDataLabel;
    private JLabel dateLabel;
    private JLabel dividerLabel;
    private JLabel itemLabel;
    private JLabel jLabelSubtotal1;
    private JLabel locationLabel;
    private JLabel logoLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel receiptIDTxtField;
    private JPanel receiptPanel;
    private JScrollPane receiptScrollPane;
    private JTextArea receiptTxtArea;
    private JLabel salesInvoiceLabel;
    private JLabel subTotalLabel;
    private JLabel taxLabel;
    private JLabel titleLabel;
    private JLabel totalAmountLabel;
    private JLabel totalLabel;
    private JLabel totalSaleLabel;
    private JLabel vatAmountLabel;
    private JLabel vatLabel;
    private JLabel vatableLabel;
    // End of variables declaration//GEN-END:variables
}