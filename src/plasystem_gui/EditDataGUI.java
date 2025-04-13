package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.DataHandling;
import plasystem_functions.ProductData;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * GUI for editing existing data in the application.
 */
public class EditDataGUI extends JFrame {
    // Attributes for handling data
    private JTable TableData;
    private String path;
    private Integer selectedRow;
    private int quantity;
    private int restockValue;
    LinkedList<ProductData> list;
    ErrorValueHandling isDataValid = new ErrorValueHandling();
    
    /**
     * Constructor with parameters to initialize EditDataGUI with existing data.
     *
     * @param selectedRow The index of the selected row.
     * @param path        The file path.
     * @param list        The LinkedList containing ProductData.
     * @param TableData   The JTable containing data.
     * @param productID   The ID of the product.
     * @param quantity    The quantity of the product.
     * @param price       The price of the product.
     * @param name        The name of the product.
     * @param size       The size of the product.
     * @param brand    The brand(s) the product is available on.
     * @param type   The type of the product.
     * @param restockValue
     */
    public EditDataGUI(int selectedRow, String path, LinkedList<ProductData> list, JTable TableData,String productID, String quantity, String price, String name, String size, String brand, String type, String restockValue) {
        initComponents(); // Initialize components defined in the GUI
        
        setLocationRelativeTo(null); // Set the location of the window to the center of the screen
        
        // Assign provided parameters to class attributes
        this.TableData = TableData;
        this.path =path;
        this.selectedRow = selectedRow;
        this.list =  list;
        this.quantity = Integer.parseInt(quantity); // Parse quantity to an integer
        this.restockValue = Integer.parseInt(restockValue);
         
        // Disable product ID box
        productIDTxtField.setEnabled(false);
        
        // Set initial values in text fields based on the data from the selected row
        productIDTxtField.setText(productID);
        quantityPicker.setValue(this.quantity);
        priceTxtField.setText(price);
        nameTxtField.setText(name);
        sizeTxtField.setText(size);
        brandTxtField.setText(brand);
        typeTxtField.setText(type);
        restockValueTxtField.setText(restockValue);
        
        // Ensure quantity value is non-negative
        quantityPicker.addChangeListener((ChangeEvent e) -> {
            int value = (int) quantityPicker.getValue();
            if (value < 0) {
                quantityPicker.setValue(0); // Set the value to 0 if it's negative
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
        quantityPicker = new javax.swing.JSpinner();
        typeTxtField = new javax.swing.JTextField();
        brandTxtField = new javax.swing.JTextField();
        platformLabel = new javax.swing.JLabel();
        publisherLabel = new javax.swing.JLabel();
        restockValueTxtField = new javax.swing.JTextField();
        publisherLabel1 = new javax.swing.JLabel();
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

        restockValueTxtField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockValueTxtFieldActionPerformed(evt);
            }
        });

        publisherLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        publisherLabel1.setText("RE-STOCK VALUE");

        javax.swing.GroupLayout textFieldsPanelLayout = new javax.swing.GroupLayout(textFieldsPanel);
        textFieldsPanel.setLayout(textFieldsPanelLayout);
        textFieldsPanelLayout.setHorizontalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(sizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(textFieldsPanelLayout.createSequentialGroup()
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(platformLabel)
                                .addComponent(publisherLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(restockValueTxtField)
                                .addComponent(brandTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
                        .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(genreLabel)
                    .addComponent(nameLabel))
                .addGap(24, 24, 24)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityLabel)
                    .addComponent(prodIDLabel)
                    .addComponent(publisherLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productIDTxtField)
                    .addComponent(priceTxtField, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeTxtField))
                .addContainerGap(10, Short.MAX_VALUE))
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
                            .addComponent(quantityLabel)
                            .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restockValueTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(publisherLabel1))))
                .addContainerGap(30, Short.MAX_VALUE))
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
        titleTabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/edit.png"))); // NOI18N

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
        // Create an instance of DataHandling class with the provided 'path'
        DataHandling dataHandling = new DataHandling(path);
        
        // Show a confirmation dialog for saving changes for the item in the 'nameTxtField'
        int confirm = JOptionPane.showConfirmDialog(null, "Do you wish to save changes for " + nameTxtField.getText() + " ?", "Edit Data", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                boolean isValid = true; // A flag to track the validity of the input data

                // Validate and parse the price text field
                double editedPrice = 0.0;
                String priceText = priceTxtField.getText();
                if (!isDataValid.isDouble(priceText)) {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Price Input. Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    editedPrice = Double.parseDouble(priceText);

                    if (editedPrice < 0) {
                        isValid = false;
                        JOptionPane.showMessageDialog(null, "Invalid Price Input. Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (isValid) {
                    // Get edited quantity, name, genre, console, and publisher from respective fields
                    int editedQuantity = (int) quantityPicker.getValue();
                    int editedRestockValue = Integer.parseInt(restockValueTxtField.getText());
                    String editedName = nameTxtField.getText();
                    String editedSize = sizeTxtField.getText();
                    String editedBrand = brandTxtField.getText();
                    String editedType = typeTxtField.getText();

                // Update the data in the list and table using DataHandling's editSelectedData method
                dataHandling.editSelectedData(list,
                        TableData,
                        selectedRow, 
                        editedQuantity,
                        editedPrice,
                        editedName,
                        editedSize, 
                        editedBrand, 
                        editedType,
                        editedRestockValue
                );

                    // Close the current edit frame
                    this.dispose();
                }
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

    private void restockValueTxtFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockValueTxtFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restockValueTxtFieldActionPerformed
    
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
    private javax.swing.JLabel publisherLabel1;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JTextField restockValueTxtField;
    private javax.swing.JButton saveBtn;
    private javax.swing.JTextField sizeTxtField;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleTabel;
    private javax.swing.JTextField typeTxtField;
    // End of variables declaration//GEN-END:variables
}
