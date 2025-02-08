package plasystem_gui;

import plasystem_functions.ErrorValueHandling;
import plasystem_functions.DataHandling;
import plasystem_functions.GameData;
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
    LinkedList<GameData> list;
    ErrorValueHandling isDataValid = new ErrorValueHandling();
    
    /**
     * Default constructor for the EditDataGUI.
     */
    public EditDataGUI (){
       initComponents(); // Initialize components defined in the GUI
       setLocationRelativeTo(null); // Set the location of the window to the center of the screen
    }
    
    /**
     * Constructor with parameters to initialize EditDataGUI with existing data.
     *
     * @param selectedRow The index of the selected row.
     * @param path        The file path.
     * @param list        The LinkedList containing GameData.
     * @param TableData   The JTable containing data.
     * @param productID   The ID of the product.
     * @param quantity    The quantity of the product.
     * @param price       The price of the product.
     * @param name        The name of the product.
     * @param genre       The genre of the product.
     * @param platform    The platform(s) the product is available on.
     * @param year        The release year of the product.
     * @param publisher   The publisher of the product.
     */
    public EditDataGUI(int selectedRow, String path,LinkedList<GameData> list, JTable TableData,String productID, String quantity, String price, String name, String genre, String platform, String year, String publisher) {
        initComponents(); // Initialize components defined in the GUI
        setLocationRelativeTo(null); // Set the location of the window to the center of the screen
        
        // Assign provided parameters to class attributes
        this.TableData = TableData;
        this.path =path;
        this.selectedRow = selectedRow;
        this.list =  list;
        this.quantity = Integer.parseInt(quantity); // Parse quantity to an integer
         
        // Disable product ID box
        productIDTxtField.setEnabled(false);
        
        // Set initial values in text fields based on the data from the selected row
        productIDTxtField.setText(productID);
        quantityPicker.setValue(this.quantity);
        priceTxtField.setText(price);
        nameTxtField.setText(name);
        genreTxtField.setText(genre);
        platformTxtField.setText(platform);
        yearTxtField.setText(year);
        publisherTxtField.setText(publisher);
        
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
        genreTxtField = new javax.swing.JTextField();
        yearLabel = new javax.swing.JLabel();
        yearTxtField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceTxtField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityPicker = new javax.swing.JSpinner();
        publisherTxtField = new javax.swing.JTextField();
        platformTxtField = new javax.swing.JTextField();
        platformLabel = new javax.swing.JLabel();
        publisherLabel = new javax.swing.JLabel();
        cancelBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        titleTabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        textFieldsPanel.setPreferredSize(new java.awt.Dimension(528, 306));

        nameLabel.setText("NAME");

        prodIDLabel.setText("PROD ID");

        productIDTxtField.setEditable(false);

        genreLabel.setText("GENRE");

        yearLabel.setText("YEAR");

        priceLabel.setText("PRICE");

        quantityLabel.setText("QUANTITY");

        platformLabel.setText("PLATFORM");

        publisherLabel.setText("PUBLISHER");

        javax.swing.GroupLayout textFieldsPanelLayout = new javax.swing.GroupLayout(textFieldsPanel);
        textFieldsPanel.setLayout(textFieldsPanelLayout);
        textFieldsPanelLayout.setHorizontalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(platformLabel)
                                .addGap(18, 18, 18)
                                .addComponent(platformTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(publisherLabel)
                                .addGap(18, 18, 18)
                                .addComponent(publisherTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(genreLabel)
                                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(genreTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, textFieldsPanelLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(nameLabel))
                        .addGap(27, 27, 27)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel))))
                .addGap(18, 18, 18)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(productIDTxtField)
                        .addComponent(yearTxtField)
                        .addComponent(priceTxtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productIDTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(genreLabel)
                            .addComponent(genreTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearLabel))
                        .addGap(22, 22, 22)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(platformLabel)
                            .addComponent(platformTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLabel)
                            .addComponent(priceTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(publisherTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(publisherLabel))
                            .addGroup(textFieldsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(quantityLabel)
                                .addComponent(quantityPicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        titleTabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 15)); // NOI18N
        titleTabel.setText("Edit Data");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleTabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(titleTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

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

                // Validate and parse the Year text field
                int editedYear = 0;
                String yearText = yearTxtField.getText();
                if (!isDataValid.isInteger(yearText) || yearText.length() != 4) {
                    isValid = false;
                    JOptionPane.showMessageDialog(null, "Invalid Year Input. Please enter a valid year with exactly 4 digits.", "Error", JOptionPane.ERROR_MESSAGE);
                } 
                else {
                    editedYear = Integer.parseInt(yearText);
                }

                if (isValid) {
                    // Get edited quantity, name, genre, console, and publisher from respective fields
                    int editedQuantity = (int) quantityPicker.getValue();
                    String editedName = nameTxtField.getText();
                    String editedGenre = genreTxtField.getText();
                    String editedConsole = platformTxtField.getText();
                    String editedPublisher = publisherTxtField.getText();

                // Update the data in the list and table using DataHandling's editSelectedData method
                dataHandling.editSelectedData(
                        list,
                        TableData,
                        selectedRow, 
                        editedQuantity,
                        editedPrice,
                        editedName,
                        editedGenre, 
                        editedConsole, 
                        editedYear, 
                        editedPublisher);

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
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JTextField genreTxtField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTxtField;
    private javax.swing.JLabel platformLabel;
    private javax.swing.JTextField platformTxtField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTxtField;
    private javax.swing.JLabel prodIDLabel;
    private javax.swing.JTextField productIDTxtField;
    private javax.swing.JLabel publisherLabel;
    private javax.swing.JTextField publisherTxtField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantityPicker;
    private javax.swing.JButton saveBtn;
    private javax.swing.JPanel textFieldsPanel;
    private javax.swing.JLabel titleTabel;
    private javax.swing.JLabel yearLabel;
    private javax.swing.JTextField yearTxtField;
    // End of variables declaration//GEN-END:variables
}