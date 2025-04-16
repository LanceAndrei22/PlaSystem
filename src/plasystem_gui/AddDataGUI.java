package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.DataHandling;
import plasystem_functions.RandomIDGenerator;
import plasystem_functions.ProductData;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A GUI window allowing users to add new data to the application.
 */
public class AddDataGUI extends JFrame {
    // Attributes for handling data
    LinkedList<ProductData> productList;
    private String filePath;
    private JTable tableData;
    private String newProductID;
    RandomIDGenerator prodId = new RandomIDGenerator();
    ErrorValueHandling isDataValid = new ErrorValueHandling();

    /**
     * Default constructor initializing the AddDataGUI.
     */
     public AddDataGUI(){
        initComponents(); // Initialize components defined in the form
        setLocationRelativeTo(null); // Set the frame to appear in the center of the screen
     }
     
    /**
     * Constructor initializing the AddDataGUI with necessary data.
     *
     * @param productList   The list of ProductData to be updated.
     * @param tableData  The JTable to display and modify data.
     * @param filePath   The path to the file storing the game data.
     */
    public AddDataGUI(LinkedList<ProductData> productList, JTable tableData, String filePath) {
        initComponents(); // Initialize components defined in the form
        
        setLocationRelativeTo(null); // Set the frame to appear in the center of the screen
        
        // Assign provided parameters to class attributes
        this.productList = productList;
        this.tableData = tableData;
        this.filePath = filePath;
        
        productIDTxtField.setEnabled(false); // Disable the product ID text field
    
        newProductID = prodId.generateProductID(); // Generate a new product ID
        productIDTxtField.setText(newProductID); // Set the generated product ID in the text field
         
        // Ensure no negative value is selected in the quantity picker
        quantityPicker.addChangeListener((ChangeEvent e) -> {
            int value = (int) quantityPicker.getValue();
            if (value < 0) {
                quantityPicker.setValue(0); // Set the value to 0 if it's negative
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textFieldsPanel = new javax.swing.JPanel();
        productIDTxtField = new javax.swing.JTextField();
        sizeTxtField = new javax.swing.JTextField();
        brandTxtField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        platformLabel = new javax.swing.JLabel();
        typeTxtField = new javax.swing.JTextField();
        priceTxtField = new javax.swing.JTextField();
        publisherLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JTextField();
        productIDLabel = new javax.swing.JLabel();
        quantityPicker = new javax.swing.JSpinner();
        restockValueTxtField = new javax.swing.JTextField();
        publisherLabel1 = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        Design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        productIDTxtField.setEditable(false);

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLabel.setText("NAME");

        genreLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        genreLabel.setText("SIZE");

        platformLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        platformLabel.setText("BRAND");

        publisherLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        publisherLabel.setText("TYPE");

        priceLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceLabel.setText("PRICE");

        quantityLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quantityLabel.setText("QUANTITY");

        productIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        productIDLabel.setText("PRODUCT ID");

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
                .addGap(22, 22, 22)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(genreLabel)
                            .addComponent(platformLabel))
                        .addGap(20, 20, 20)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTxtField)
                            .addComponent(sizeTxtField)
                            .addComponent(brandTxtField))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(quantityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)))
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel)
                    .addComponent(productIDLabel)
                    .addComponent(publisherLabel)
                    .addComponent(publisherLabel1))
                .addGap(19, 19, 19)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(typeTxtField)
                    .addComponent(productIDTxtField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(priceTxtField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restockValueTxtField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(productIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(typeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel)
                            .addComponent(productIDLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genreLabel)
                            .addComponent(priceLabel))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(platformLabel)
                            .addComponent(brandTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(publisherLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantityLabel)
                        .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restockValueTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(publisherLabel1)))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        cancelBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 3, 15)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/add.png"))); // NOI18N

        Design.setBackground(new java.awt.Color(255, 255, 204));
        Design.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 291, Short.MAX_VALUE)))
        );

        textFieldsPanel.setBackground(new Color(255, 255, 51, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * ActionListener method triggered when the 'Add' button is clicked in the GUI.
     *
     * @param evt The ActionEvent captured from the GUI.
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Create a DataHandling object with the provided file path
        DataHandling addHandling = new DataHandling(filePath);
        
        // Display a confirmation dialog to add the item
        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to add this item?", "Add New Data", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Retrieve values from text fields and picker
            int quantityValue = (int) quantityPicker.getValue();
            String tempPrice = priceTxtField.getText();
            String tempName = nameTxtField.getText();
            String tempSize = sizeTxtField.getText();
            String tempBrand = brandTxtField.getText();
            String tempType = typeTxtField.getText();
            String tempRestockValue =restockValueTxtField.getText();

            if (tempName.isEmpty() || tempSize.isEmpty() || tempBrand.isEmpty() || tempType.isEmpty() || tempPrice.isEmpty() || tempRestockValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No Inputs Detected", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                double validPrice = 0;
                int validRestockValue = 0;
                boolean isValid = true;

                // Check if price is a valid double and not negative
                if (isDataValid.isDouble(tempPrice)) {
                    validPrice = Double.parseDouble(tempPrice);
                    if (validPrice <= 0) {
                        isValid = false;
                        JOptionPane.showMessageDialog(null, "Invalid Price Input. Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Price Input. Please enter a valid price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                // Check if restock value is a valid integer and not negative
                if (isDataValid.isInteger(tempRestockValue)) {
                    validRestockValue = Integer.parseInt(tempRestockValue);
                    if (validRestockValue <= 0) {
                        isValid = false;
                        JOptionPane.showMessageDialog(null, "Invalid Restock Value.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Restock Value.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                // Check if Quantity is valid aka not 0 or negative
                if (quantityValue <= 0) {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Quantity Input. Must have at least 1.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (isValid) {
                    // Add the new data using the DataHandling object
                    addHandling.addNewData(productList, 
                                           tableData, 
                                           newProductID, 
                                           quantityValue, 
                                           validPrice, 
                                           tempName, 
                                           tempSize, 
                                           tempBrand, 
                                           tempType,
                                           validRestockValue
                    );
                    
                    // Reset text fields and generate a new product ID
                    priceTxtField.setText("");
                    sizeTxtField.setText("");
                    brandTxtField.setText("");
                    typeTxtField.setText("");
                    nameTxtField.setText("");
                    newProductID = prodId.generateProductID();
                    productIDTxtField.setText(newProductID);
                    quantityPicker.setValue(0);
                    restockValueTxtField.setText("");
                }
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed
    
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
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField brandTxtField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel platformLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JLabel productIDLabel;
    private javax.swing.JTextField productIDTxtField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JLabel publisherLabel1;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JTextField restockValueTxtField;
    private javax.swing.JTextField sizeTxtField;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField typeTxtField;
    // End of variables declaration//GEN-END:variables
}
