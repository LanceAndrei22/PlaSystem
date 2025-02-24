package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.DataHandling;
import plasystem_functions.RandomIDGenerator;
import plasystem_functions.GameData;
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
    LinkedList<GameData> gameList;
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
     * @param gameList   The list of GameData to be updated.
     * @param tableData  The JTable to display and modify data.
     * @param filePath   The path to the file storing the game data.
     */
    public AddDataGUI(LinkedList<GameData> gameList, JTable tableData, String filePath) {
        initComponents(); // Initialize components defined in the form
        setLocationRelativeTo(null); // Set the frame to appear in the center of the screen
        
        // Assign provided parameters to class attributes
        this.gameList = gameList;
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
        genreTxtField = new javax.swing.JTextField();
        platformTxtField = new javax.swing.JTextField();
        nameLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        platformLabel = new javax.swing.JLabel();
        publisherTxtField = new javax.swing.JTextField();
        yearTxtField = new javax.swing.JTextField();
        priceTxtField = new javax.swing.JTextField();
        publisherLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        quantityLabel = new javax.swing.JLabel();
        nameTxtField = new javax.swing.JTextField();
        productIDLabel = new javax.swing.JLabel();
        quantityPicker = new javax.swing.JSpinner();
        cancelBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        productIDTxtField.setEditable(false);

        nameLabel.setText("NAME");

        genreLabel.setText("GENRE");

        platformLabel.setText("PLATFORM");

        publisherLabel.setText("PUBLISHER");

        priceLabel.setText("PRICE");

        yearLabel.setText("YEAR");

        quantityLabel.setText("QUANTITY");

        productIDLabel.setText("PRODUCT ID");

        javax.swing.GroupLayout textFieldsPanelLayout = new javax.swing.GroupLayout(textFieldsPanel);
        textFieldsPanel.setLayout(textFieldsPanelLayout);
        textFieldsPanelLayout.setHorizontalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(publisherLabel)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(genreLabel)
                            .addComponent(platformLabel))
                        .addGap(20, 20, 20)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(publisherTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                            .addComponent(nameTxtField)
                            .addComponent(genreTxtField)
                            .addComponent(platformTxtField))))
                .addGap(31, 31, 31)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(priceLabel)
                    .addComponent(productIDLabel)
                    .addComponent(yearLabel)
                    .addComponent(quantityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(productIDTxtField)
                    .addComponent(priceTxtField)
                    .addComponent(yearTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productIDLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(genreLabel)
                            .addComponent(priceLabel))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(platformLabel)
                            .addComponent(platformTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearLabel)
                            .addComponent(yearTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(publisherLabel)
                    .addComponent(publisherTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityLabel))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        cancelBtn.setText("CANCEL");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        addBtn.setText("ADD");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        titleLabel.setText("Add Data");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

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
            String tempGenre = genreTxtField.getText();
            String tempConsole = platformTxtField.getText();
            String tempYear = yearTxtField.getText();
            String tempPublisher = publisherTxtField.getText();

            if (tempName.isEmpty() || tempGenre.isEmpty() || tempConsole.isEmpty() || tempYear.isEmpty() || tempPublisher.isEmpty() || tempPrice.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: No Inputs Detected", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                double validPrice = 0;
                int validYear = 0;
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

                // Check if year is a valid integer
                if (isDataValid.isInteger(tempYear) && tempYear.length() == 4) {
                    validYear = Integer.parseInt(tempYear);
                } else {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Year Input. Please enter a valid year with exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Check if Quantity is valid aka not 0 or negative
                if (quantityValue <= 0) {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Quantity Input. Must have at least 1.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                if (isValid) {
                    // Add the new data using the DataHandling object
                    addHandling.addNewData(gameList, 
                                           tableData, 
                                           newProductID, 
                                           quantityValue, 
                                           validPrice, 
                                           tempName, 
                                           tempGenre, 
                                           tempConsole, 
                                           validYear, 
                                           tempPublisher);
                    
                    // Reset text fields and generate a new product ID
                    priceTxtField.setText("");
                    yearTxtField.setText("");
                    genreTxtField.setText("");
                    platformTxtField.setText("");
                    publisherTxtField.setText("");
                    nameTxtField.setText("");
                    newProductID = prodId.generateProductID();
                    productIDTxtField.setText(newProductID);
                    quantityPicker.setValue(0);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JTextField genreTxtField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel platformLabel;
    private javax.swing.JTextField platformTxtField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JLabel productIDLabel;
    private javax.swing.JTextField productIDTxtField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JTextField publisherTxtField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTxtField;
    // End of variables declaration//GEN-END:variables
}