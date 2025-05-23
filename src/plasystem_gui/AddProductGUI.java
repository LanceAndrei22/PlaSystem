package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.ProductDataManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * A graphical user interface (GUI) window for adding new product data to the application.
 * This class provides input fields for product details and handles validation and submission
 * of the data to the product data model.
 */
public class AddProductGUI extends JFrame {
    /** The parent MainProgramGUI instance used to refresh the product table after adding a product. */
    private MainProgramGUI parentGUI;
    /** The ProductDataManager instance responsible for handling database operations. */
    private ProductDataManager productDataModel;
    /** The ErrorValueHandling instance used to validate input data formats and values. */
    private final ErrorValueHandling dataValidator = new ErrorValueHandling();

    /**
     * Default constructor that initializes the AddProductGUI.
     * Centers the window on the screen and sets up the form components.
     */
     public AddProductGUI(){
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
     }
     
    /**
     * Constructor that initializes the AddProductGUI with necessary dependencies.
     * Sets up the form components, centers the window, and adds listeners to prevent
     * negative values in quantity and restock value spinners.
     *
     * @param parent The MainProgramGUI instance to refresh the product table after adding a product
     * @param productDataManager The ProductDataManager instance to handle database operations
     */
    public AddProductGUI(MainProgramGUI parent, ProductDataManager productDataManager) {
        // Assign the parent GUI for table refresh
        this.parentGUI = parent;
        // Assign the data manager for database operations
        this.productDataModel = productDataManager;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        
        // Add listener to prevent negative values in quantity spinner
        quantityPicker.addChangeListener((ChangeEvent e) -> {
            // Get current spinner value
            int value = (int) quantityPicker.getValue();
            // Reset to 0 if negative
            if (value < 0) {
                quantityPicker.setValue(0);
            }
        });
        
        // Add listener to prevent negative values in restock value spinner
        restockValuePicker.addChangeListener((ChangeEvent e) -> {
            // Get current spinner value
            int value = (int) restockValuePicker.getValue();
            // Reset to 0 if negative
            if (value < 0) {
                restockValuePicker.setValue(0);
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
        quantityPicker = new javax.swing.JSpinner();
        publisherLabel1 = new javax.swing.JLabel();
        restockValuePicker = new javax.swing.JSpinner();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        Design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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
                            .addComponent(brandTxtField)))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(quantityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)))
                .addGap(0, 19, Short.MAX_VALUE)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(publisherLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(restockValuePicker, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(priceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textFieldsPanelLayout.createSequentialGroup()
                        .addComponent(publisherLabel)
                        .addGap(29, 29, 29)
                        .addComponent(typeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(publisherLabel)
                    .addComponent(typeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sizeTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreLabel)
                    .addComponent(priceLabel)
                    .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brandTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(platformLabel))
                .addGap(11, 11, 11)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherLabel1)
                    .addComponent(restockValuePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/add.png"))); // NOI18N

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
     * Handles the action when the "Add" button is clicked.
     * Validates user input, confirms the addition, and adds the product to the data model.
     * Updates the parent GUI table and clears input fields upon successful addition.
     *
     * @param evt The ActionEvent triggered by clicking the "Add" button
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // Prompt user to confirm the addition of the new product
        int confirmAdd = JOptionPane.showConfirmDialog(null, "Do you want to add this product?", "Add New Product", JOptionPane.YES_NO_OPTION);
        // Exit if user does not confirm
        if (confirmAdd != JOptionPane.YES_OPTION) {
            return;
        }

        // Retrieve and trim input values from text fields and spinners
        String prodName = nameTxtField.getText().trim();
        String prodSize = sizeTxtField.getText().trim();
        String prodBrand = brandTxtField.getText().trim();
        String prodType = typeTxtField.getText().trim();
        String prodPriceText = priceTxtField.getText().trim();
        String prodQuantityText = String.valueOf(quantityPicker.getValue());
        String prodRestockValueText = String.valueOf(restockValuePicker.getValue());

        // Validate that all text fields are filled
        if (prodName.isEmpty() || prodSize.isEmpty() || prodBrand.isEmpty() || prodType.isEmpty() || prodPriceText.isEmpty()) {
            // Display error message if any field is empty
            JOptionPane.showMessageDialog(null, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate price format and value
        double prodPrice;
        if (!dataValidator.isDouble(prodPriceText)) {
            // Display error if price is not a valid double
            JOptionPane.showMessageDialog(null, "Invalid price format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        prodPrice = Double.parseDouble(prodPriceText);
        if (prodPrice < 0) {
            // Display error if price is negative
            JOptionPane.showMessageDialog(null, "Price cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate quantity format
        if (!dataValidator.isInteger(prodQuantityText)) {
            // Display error if quantity is not a valid integer
            JOptionPane.showMessageDialog(null, "Invalid quantity format. Must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int prodQuantity = Integer.parseInt(prodQuantityText);
        if (prodQuantity < 0) {
            // Display error if quantity is negative
            JOptionPane.showMessageDialog(null, "Quantity cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate restock value format
        if (!dataValidator.isInteger(prodRestockValueText)) {
            // Display error if restock value is not a valid integer
            JOptionPane.showMessageDialog(null, "Invalid restock value format. Must be an integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int prodRestockValue = Integer.parseInt(prodRestockValueText);
        if (prodRestockValue < 0) {
            // Display error if restock value is negative
            JOptionPane.showMessageDialog(null, "Restock value cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Attempt to add the product to the data model
        boolean addSuccess = productDataModel.addProduct(prodName, prodBrand, prodSize, prodType, prodPrice, prodQuantity, prodRestockValue);
        if (addSuccess) {
            // Update the parent GUI product table
            parentGUI.updateProductTable();
            // Display success message
            JOptionPane.showMessageDialog(null, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Clear all input fields
            nameTxtField.setText("");
            sizeTxtField.setText("");
            brandTxtField.setText("");
            typeTxtField.setText("");
            priceTxtField.setText("");
            quantityPicker.setValue(0);
            restockValuePicker.setValue(0);
        }
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Handles the action when the "Cancel" button is clicked.
     * Closes the current window without saving any changes.
     *
     * @param evt The ActionEvent triggered by clicking the "Cancel" button
     */
    private void cancelBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // Close the current window, discarding any input
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

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
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JLabel publisherLabel1;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JSpinner restockValuePicker;
    private javax.swing.JTextField sizeTxtField;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JTextField typeTxtField;
    // End of variables declaration//GEN-END:variables
}