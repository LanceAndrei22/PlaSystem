package plasystem_gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * A graphical user interface (GUI) window for displaying the history of transactions.
 * This class provides a table of transaction records with options to view details, delete records,
 * search, refresh, and export reports.
 */
public class TransactionHistoryGUI extends JFrame {
    /** The TransactionDataManager instance for managing transaction data operations. */
    private TransactionDataManager transactionDataModel;
    /** The list of TransactionData objects retrieved from the database. */
    private List<TransactionData> transactionList;
    /** The list tracking open child GUI windows. */
    private final List<JFrame> childGUIs = new ArrayList<>();
    /** The map tracking active GUI instances to ensure single-instance behavior. */
    private final Map<Class<? extends JFrame>, JFrame> activeGUIs = new HashMap<>();
    
    /**
     * Default constructor that initializes the TransactionHistoryGUI.
     * Centers the window and sets up the form components.
     */
    public TransactionHistoryGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the TransactionHistoryGUI with a provided TransactionDataManager.
     * Populates the table with transaction data and applies table formatting.
     *
     * @param transactionDataManager The TransactionDataManager instance for database operations
     */
    public TransactionHistoryGUI(TransactionDataManager transactionDataManager) {
        // Assign the transaction data manager
        this.transactionDataModel = transactionDataManager;
        // Load the transaction list from the data manager
        this.transactionList = transactionDataManager.getTransactionList();
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Populate the table with transaction data
        updateTable();
        // Apply table renderer for formatting (667 is the table width)
        new TransactionHistoryTableRenderer(transHistorytbl, 667);
    }
    
    /**
     * Adds a child GUI to the tracking list.
     *
     * @param child The JFrame to add as a child GUI
     */
    public void addChildGUI(JFrame child) {
        // Add the child GUI to the tracking list
        childGUIs.add(child);
    }

    /**
     * Removes a child GUI from the tracking list.
     *
     * @param child The JFrame to remove from the child GUI list
     */
    public void removeChildGUI(JFrame child) {
        // Remove the child GUI from the tracking list
        childGUIs.remove(child);
    }
    
    /**
     * Launches or focuses a single instance of a specified GUI.
     * Ensures only one instance of the GUI is open at a time.
     *
     * @param guiClass The class of the GUI to launch
     * @param creator A Supplier to create a new instance of the GUI if needed
     * @param <T> The type of the JFrame subclass
     * @return The launched or existing GUI instance
     */
    private <T extends JFrame> T launchSingleInstance(Class<T> guiClass, Supplier<T> creator) {
        // Check if an instance of the GUI already exists
        JFrame existingInstance = activeGUIs.get(guiClass);
        // Remove the instance if it exists but is no longer displayable
        if (existingInstance != null && !existingInstance.isDisplayable()) {
            activeGUIs.remove(guiClass);
            existingInstance = null;
        }

        // If an instance exists, focus it and show a warning
        if (existingInstance != null) {
            // Display warning message
            JOptionPane.showMessageDialog(
                existingInstance,
                "Only one instance can be present.",
                "Instance Warning",
                JOptionPane.WARNING_MESSAGE
            );
            // Bring the existing instance to the foreground
            existingInstance.requestFocus();
            // Ensure the instance is visible
            existingInstance.setVisible(true);
            return guiClass.cast(existingInstance);
        }

        // Create a new instance of the GUI
        T newInstance = creator.get();
        // Make the new instance visible
        newInstance.setVisible(true);
        // Set the default close operation to dispose
        newInstance.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Add a window listener to clean up when the window is closed
        newInstance.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Remove the GUI from the active instances map
                activeGUIs.remove(guiClass);
                // Remove the GUI from the child GUI list
                removeChildGUI(newInstance);
            }
        });
        // Add the new instance to the active GUIs map
        activeGUIs.put(guiClass, newInstance);
        // Add the new instance to the child GUI list
        addChildGUI(newInstance);
        return newInstance;
    }
    
    /**
     * Disposes of the window and closes all child GUIs.
     */
    @Override
    public void dispose() {
        // Close all child GUIs
        for (JFrame child : new ArrayList<>(childGUIs)) {
            child.dispose();
        }
        // Clear the child GUI list
        childGUIs.clear();
        // Call the superclass dispose method to close the window
        super.dispose();
    }
    
    /**
     * Updates the transaction history table with data from the transaction list.
     */
    private void updateTable() {
        // Get the table model
        DefaultTableModel transHistoryTblModel = (DefaultTableModel) transHistorytbl.getModel();
        // Clear existing rows
        transHistoryTblModel.setRowCount(0);

        // Iterate through the transaction list
        for (TransactionData transaction : transactionList) {
            // Add each transaction as a new row
            transHistoryTblModel.addRow(new Object[]{
                transaction.getTransactionId(),
                transaction.getFormattedDate().split(" ")[0], // Date (YYYY-MM-DD)
                transaction.getTransDateTime(), // Time
                transaction.getTotalAmount(),
                transaction.getPaymentAmount(),
                transaction.getChangeAmount()
            });
        }
        
        // Reapply the current search filter if the search field is not empty
        if (!searchTxtField.getText().trim().isEmpty()) {
            searchTxtFieldKeyReleased(null);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        transHistoryScrollPane = new javax.swing.JScrollPane();
        transHistorytbl = new javax.swing.JTable();
        detailsBtn = new javax.swing.JButton();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        design1 = new javax.swing.JLabel();
        deleteBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        setResizable(false);

        transHistorytbl.setAutoCreateRowSorter(true);
        transHistorytbl.getTableHeader().setReorderingAllowed(false);
        transHistorytbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Time", "Total Amount", "Payment Amount", "Change Given"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        transHistoryScrollPane.setViewportView(transHistorytbl);

        detailsBtn.setText("See Details");
        detailsBtn.setInheritsPopupMenu(true);
        detailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtnActionPerformed(evt);
            }
        });

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Year", "Month", "Day", "Date", "Total Amount", "Payment Amount", "Change Given" }));

        design1.setBackground(new java.awt.Color(255, 102, 102));
        design1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        design1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/transact_history_title.png"))); // NOI18N
        design1.setOpaque(true);

        deleteBtn.setBackground(new java.awt.Color(255, 102, 102));
        deleteBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Delete");
        deleteBtn.setInheritsPopupMenu(true);
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        refreshBtn.setText("Refresh");
        refreshBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBtnActionPerformed(evt);
            }
        });

        exportBtn.setText("🖨️ Export");
        exportBtn.setInheritsPopupMenu(true);
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(design1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(transHistoryScrollPane)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(195, 195, 195)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addGap(467, 467, 467)
                        .addComponent(detailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(design1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchTxtField)
                            .addComponent(searchPrmtrBox)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(refreshBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(3, 3, 3)
                .addComponent(transHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(detailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the action when the "See Details" button is clicked.
     * Displays the transaction items for the selected transaction in a new window.
     *
     * @param evt The ActionEvent triggered by clicking the "See Details" button
     */
    private void detailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = transHistorytbl.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a transaction to view details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view index to model index to account for sorting/filtering
        selectedRow = transHistorytbl.convertRowIndexToModel(selectedRow);
        // Get the transaction ID from the selected row
        int transactionId = (int) transHistorytbl.getModel().getValueAt(selectedRow, 0);
        // Find the corresponding TransactionData object
        TransactionData selectedTransaction = transactionList.stream()
            .filter(t -> t.getTransactionId() == transactionId)
            .findFirst()
            .orElse(null);

        // Check if the transaction data was found
        if (selectedTransaction != null) {
            // Launch or focus a single instance of THDetailsGUI
            launchSingleInstance(THDetailsGUI.class, () -> {
                // Create a new THDetailsGUI instance
                THDetailsGUI thDetailGUI = new THDetailsGUI(selectedTransaction);
                // Pack the GUI to fit its contents
                thDetailGUI.pack();
                // Center the GUI on the screen
                thDetailGUI.setLocationRelativeTo(null);
                // Set the default close operation to dispose
                thDetailGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                return thDetailGUI;
            });
        } else {
            // Display error message if transaction data is not found
            JOptionPane.showMessageDialog(this, "Transaction not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_detailsBtnActionPerformed
    
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the selected transaction after user confirmation.
     *
     * @param evt The ActionEvent triggered by clicking the "Delete" button
     */
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = transHistorytbl.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a transaction to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view index to model index
        selectedRow = transHistorytbl.convertRowIndexToModel(selectedRow);
        // Get the transaction ID from the selected row
        int transactionId = (int) transHistorytbl.getModel().getValueAt(selectedRow, 0);

        // Prompt user to confirm deletion
        int confirmDelete = JOptionPane.showConfirmDialog(this,
            "Do you really want to delete transaction ID " + transactionId + "?",
            "Delete",
            JOptionPane.YES_NO_OPTION);
        // Proceed with deletion if user confirms
        if (confirmDelete == JOptionPane.YES_OPTION) {
            // Attempt to delete the transaction from the database
            boolean success = transactionDataModel.deleteTransaction(transactionId);
            if (success) {
                // Update the table to reflect the deletion
                updateTable();
                // Display success message
                JOptionPane.showMessageDialog(this, "Transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display error message if deletion fails
                JOptionPane.showMessageDialog(this, "Failed to delete transaction.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed
    
    /**
     * Handles the action when the "Refresh" button is clicked.
     * Updates the transaction history table with the latest data.
     *
     * @param evt The ActionEvent triggered by clicking the "Refresh" button
     */
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // Refresh the table with the latest transaction data
        updateTable();
    }//GEN-LAST:event_refreshBtnActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the transaction history table based on the search text and selected parameter.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Get the table model
        DefaultTableModel transHistoryTblModel = (DefaultTableModel) transHistorytbl.getModel();
        // Create a new TableRowSorter for filtering
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(transHistoryTblModel);
        // Apply the sorter to the table
        transHistorytbl.setRowSorter(sorter);

        // Retrieve and trim the search text
        String searchText = searchTxtField.getText().trim();
        // Get the selected search parameter
        String param = searchPrmtrBox.getSelectedItem().toString();

        // Clear the filter if the search text is empty
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        // Convert month name to numeric format for Month parameter
        String filterText = param.equals("Month") ? MonthConverter.monthNameToNumeric(searchText) : searchText;

        // Map parameter to column index
        int columnIndex = switch (param) {
            case "ID" -> 0;
            case "Date", "Year", "Month", "Day" -> 1; // Date column
            case "Total Amount" -> 3;
            case "Payment Amount" -> 4;
            case "Change Given" -> 5;
            default -> throw new IllegalArgumentException("Invalid search parameter: " + param);
        };

        // Apply case-insensitive regex filter
        try {
            RowFilter<DefaultTableModel, Object> filter = switch (param) {
                case "Date" -> RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex);
                case "Year" -> RowFilter.regexFilter("(?i)^" + filterText + "-.*", columnIndex);
                case "Month" -> RowFilter.regexFilter("(?i)^\\d{4}-" + filterText + "-.*", columnIndex);
                case "Day" -> RowFilter.regexFilter("(?i)^\\d{4}-\\d{2}-" + filterText + ".*", columnIndex);
                default -> RowFilter.regexFilter("(?i)" + filterText, columnIndex);
            };
            // Apply the filter to the sorter
            sorter.setRowFilter(filter);
        } catch (Exception e) {
            // Clear the filter if an error occurs
            sorter.setRowFilter(null);
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased
    
    /**
     * Handles the action when the "Export" button is clicked.
     * Generates and exports a transaction history report.
     *
     * @param evt The ActionEvent triggered by clicking the "Export" button
     */
    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        // Create a TransactionReportGenerator with the transaction list
        TransactionReportGenerator reportGenerator = new TransactionReportGenerator(transactionList);
        // Generate and export the transaction history report
        reportGenerator.generateReport(this);
    }//GEN-LAST:event_exportBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel design1;
    private javax.swing.JButton detailsBtn;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JScrollPane transHistoryScrollPane;
    private javax.swing.JTable transHistorytbl;
    // End of variables declaration//GEN-END:variables
}