package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.ProductDataManager;
import plasystem_functions.ProductData;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * GUI for editing existing data in the application.
 */
public class EditProductGUI extends JFrame {
    private MainProgramGUI parent;
    private ProductDataManager dataHandling;
    private ProductData product;
    private final ErrorValueHandling isDataValid = new ErrorValueHandling();
    
    
    /**
     * Default constructor initializing the EditProductGUI.
     */
     public EditProductGUI(){
        initComponents(); // Initialize components defined in the form
        setLocationRelativeTo(null); // Set the frame to appear in the center of the screen
     }
     
    /**
     * Constructor to initialize EditProductGUI with existing product data.
     *
     * @param parent       The parent MainProgramGUI to refresh the table.
     * @param dataHandling The ProductDataManager to handle database operations.
     * @param product      The ProductData object to edit.
     * @param selectedRow  The index of the selected row in the table.
     */
    public EditProductGUI(MainProgramGUI parent, ProductDataManager dataHandling, ProductData product, int selectedRow) {
        this.parent = parent;
        this.dataHandling = dataHandling;
        this.product = product;
        initComponents();
        setLocationRelativeTo(null);

        // Disable product ID field
        productIDTxtField.setEnabled(false);
        
        // Populate fields with product data
        productIDTxtField.setText(String.valueOf(product.getProductId()));
        nameTxtField.setText(product.getProductName());
        sizeTxtField.setText(product.getProductSize());
        brandTxtField.setText(product.getProductBrand());
        typeTxtField.setText(product.getProductType());
        priceTxtField.setText(String.valueOf(product.getProductPrice()));
        quantityPicker.setValue(product.getProductQuantity());
        restockValuePicker.setValue(product.getProductRestockValue());

        // Ensure no negative values in spinners
        quantityPicker.addChangeListener((ChangeEvent e) -> {
            int value = (int) quantityPicker.getValue();
            if (value < 0) {
                quantityPicker.setValue(0);
            }
        });

        restockValuePicker.addChangeListener((ChangeEvent e) -> {
            int value = (int) restockValuePicker.getValue();
            if (value < 0) {
                restockValuePicker.setValue(0);
            }
        });
        
        // Unregister from parent when window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                parent.removeChildGUI(EditProductGUI.this);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldsPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JTextField();
        prodIDLabel = new javax.swing.JLabel();
        productIDTxtField = new javax.swing.JTextField();
        genreLabel = new javax.swing.JLabel();
        sizeTxtField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceTxtField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        restockValuePicker = new javax.swing.JSpinner();
        typeTxtField = new javax.swing.JTextField();
        brandTxtField = new javax.swing.JTextField();
        platformLabel = new javax.swing.JLabel();
        publisherLabel = new javax.swing.JLabel();
        resotckLabel = new javax.swing.JLabel();
        quantityPicker = new javax.swing.JSpinner();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        titleTabel = new javax.swing.JLabel();
        Design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        textFieldsPanel.setForeground(new java.awt.Color(0, 0, 0));
        textFieldsPanel.setPreferredSize(new java.awt.Dimension(528, 306));

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(0, 0, 0));
        nameLabel.setText("NAME");

        prodIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        prodIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        prodIDLabel.setText("PROD ID");

        productIDTxtField.setEditable(false);

        genreLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        genreLabel.setForeground(new java.awt.Color(0, 0, 0));
        genreLabel.setText("SIZE");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(0, 0, 0));
        priceLabel.setText("PRICE");

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantityLabel.setForeground(new java.awt.Color(0, 0, 0));
        quantityLabel.setText("QUANTITY");

        platformLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        platformLabel.setForeground(new java.awt.Color(0, 0, 0));
        platformLabel.setText("BRAND");

        publisherLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        publisherLabel.setForeground(new java.awt.Color(0, 0, 0));
        publisherLabel.setText("TYPE");

        resotckLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resotckLabel.setText("RE-STOCK VALUE");

        javax.swing.GroupLayout textFieldsPanelLayout = new javax.swing.GroupLayout(textFieldsPanel);
        textFieldsPanel.setLayout(textFieldsPanelLayout);
        textFieldsPanelLayout.setHorizontalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(textFieldsPanelLayout.createSequentialGroup()
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textFieldsPanelLayout.createSequentialGroup()
                                    .addComponent(platformLabel)
                                    .addGap(56, 56, 56))
                                .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                    .addComponent(quantityLabel)
                                    .addGap(37, 37, 37)))
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(brandTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(genreLabel)
                    .addComponent(nameLabel))
                .addGap(24, 24, 24)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel)
                            .addComponent(publisherLabel))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productIDTxtField)
                            .addComponent(priceTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(typeTxtField)))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(resotckLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restockValuePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel))
                        .addGap(18, 18, 18)
                        .addComponent(typeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreLabel)
                            .addComponent(sizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(publisherLabel))
                        .addGap(22, 22, 22)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(platformLabel)
                            .addComponent(brandTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLabel)
                            .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(restockValuePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resotckLabel)
                            .addComponent(quantityLabel)
                            .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveBtn.setText("SAVE");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        titleTabel.setFont(new java.awt.Font("Microsoft YaHei UI", 3, 15)); // NOI18N
        titleTabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleTabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/edit.png"))); // NOI18N

        Design.setBackground(new java.awt.Color(153, 204, 255));
        Design.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(titleTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 282, Short.MAX_VALUE)))
        );

        textFieldsPanel.setBackground(new Color(0, 153, 255, 100));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the action event for the save button in EditDataGUI.
     * Validates and saves changes made to the data.
     *
     * @param evt The ActionEvent triggered by clicking the save button.
     */
    private void saveBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Do you wish to save changes for " + nameTxtField.getText() + "?", 
            "Edit Product", 
            JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        String name = nameTxtField.getText().trim();
        String size = sizeTxtField.getText().trim();
        String brand = brandTxtField.getText().trim();
        String type = typeTxtField.getText().trim();
        String priceText = priceTxtField.getText().trim();
        String quantityText = String.valueOf(quantityPicker.getValue());
        String restockValueText = String.valueOf(restockValuePicker.getValue());

        // Validate inputs
        if (name.isEmpty() || size.isEmpty() || brand.isEmpty() || type.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price;
        if (!isDataValid.isDouble(priceText)) {
            JOptionPane.showMessageDialog(null, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        price = Double.parseDouble(priceText);
        if (price < 0) {
            JOptionPane.showMessageDialog(null, "Price cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isDataValid.isInteger(quantityText)) {
            JOptionPane.showMessageDialog(null, "Invalid quantity format. Must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(quantityText);
        if (quantity < 0) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isDataValid.isInteger(restockValueText)) {
            JOptionPane.showMessageDialog(null, "Invalid restock value format. Must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int restockValue = Integer.parseInt(restockValueText);
        if (restockValue < 0) {
            JOptionPane.showMessageDialog(null, "Restock value cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean success = dataHandling.updateProduct(
            product.getProductId(),
            name,
            brand,
            size,
            type,
            price,
            quantity,
            restockValue
        );

        if (success) {
            parent.refreshTable();
            JOptionPane.showMessageDialog(null, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }//GEN-LAST:event_saveBtnActionPerformed
    
    /**
     * ActionListener method triggered when the 'Cancel' button is clicked in the GUI.
     *
     * @param evt The ActionEvent captured from the GUI.
     */
    private void cancelBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose(); // Close the current window (the frame)
    }//GEN-LAST:event_cancelBtnActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JTextField brandTxtField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel platformLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JLabel prodIDLabel;
    private javax.swing.JTextField productIDTxtField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JLabel resotckLabel;
    private javax.swing.JSpinner restockValuePicker;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField sizeTxtField;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleTabel;
    private javax.swing.JTextField typeTxtField;
    // End of variables declaration//GEN-END:variables
}
