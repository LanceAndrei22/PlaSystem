package datamine_gui;

import datamine_functions.*;
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

        textFieldsPanel = new JPanel();
        nameLabel = new JLabel();
        nameTxtField = new JTextField();
        prodIDLabel = new JLabel();
        productIDTxtField = new JTextField();
        genreLabel = new JLabel();
        genreTxtField = new JTextField();
        yearLabel = new JLabel();
        yearTxtField = new JTextField();
        priceLabel = new JLabel();
        priceTxtField = new JTextField();
        quantityLabel = new JLabel();
        quantityPicker = new JSpinner();
        publisherTxtField = new JTextField();
        platformTxtField = new JTextField();
        platformLabel = new JLabel();
        publisherLabel = new JLabel();
        cancelBtn = new JButton();
        saveBtn = new JButton();
        titleTabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        textFieldsPanel.setBorder(BorderFactory.createTitledBorder(""));
        textFieldsPanel.setPreferredSize(new Dimension(528, 306));

        nameLabel.setText("NAME");

        prodIDLabel.setText("PROD ID");

        productIDTxtField.setEditable(false);

        genreLabel.setText("GENRE");

        yearLabel.setText("YEAR");

        priceLabel.setText("PRICE");

        quantityLabel.setText("QUANTITY");

        platformLabel.setText("PLATFORM");

        publisherLabel.setText("PUBLISHER");

        GroupLayout textFieldsPanelLayout = new GroupLayout(textFieldsPanel);
        textFieldsPanel.setLayout(textFieldsPanelLayout);
        textFieldsPanelLayout.setHorizontalGroup(
            textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(platformLabel)
                                .addGap(18, 18, 18)
                                .addComponent(platformTxtField, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(publisherLabel)
                                .addGap(18, 18, 18)
                                .addComponent(publisherTxtField, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(quantityLabel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                .addComponent(genreLabel)
                                .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(genreTxtField, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(GroupLayout.Alignment.LEADING, textFieldsPanelLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(nameTxtField, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE))))
                            .addComponent(nameLabel))
                        .addGap(27, 27, 27)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(yearLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel))))
                .addGap(18, 18, 18)
                .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(productIDTxtField)
                        .addComponent(yearTxtField)
                        .addComponent(priceTxtField, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        textFieldsPanelLayout.setVerticalGroup(
            textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(textFieldsPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(productIDTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGroup(textFieldsPanelLayout.createSequentialGroup()
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(prodIDLabel))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(genreLabel)
                            .addComponent(genreTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearLabel))
                        .addGap(22, 22, 22)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(platformLabel)
                            .addComponent(platformTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceLabel)
                            .addComponent(priceTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(publisherTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(publisherLabel))
                            .addGroup(textFieldsPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(quantityLabel)
                                .addComponent(quantityPicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("Save");
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        titleTabel.setFont(new Font("Segoe UI Semibold", 0, 15)); // NOI18N
        titleTabel.setText("Edit Data");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(titleTabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(330, 330, 330)
                                .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(textFieldsPanel, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(titleTabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldsPanel, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
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
    private JButton cancelBtn;
    private JLabel genreLabel;
    private JTextField genreTxtField;
    private JLabel nameLabel;
    private JTextField nameTxtField;
    private JLabel platformLabel;
    private JTextField platformTxtField;
    private JLabel priceLabel;
    private JTextField priceTxtField;
    private JLabel prodIDLabel;
    private JTextField productIDTxtField;
    private JLabel publisherLabel;
    private JTextField publisherTxtField;
    private JLabel quantityLabel;
    private JSpinner quantityPicker;
    private JButton saveBtn;
    private JPanel textFieldsPanel;
    private JLabel titleTabel;
    private JLabel yearLabel;
    private JTextField yearTxtField;
    // End of variables declaration//GEN-END:variables
}