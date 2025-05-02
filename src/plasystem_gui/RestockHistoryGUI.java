package plasystem_gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * A graphical user interface (GUI) window for displaying the history of restock operations.
 * This class provides a table of restock records with options to view details, delete records,
 * search, refresh, and export reports.
 */
public class RestockHistoryGUI extends JFrame {
    /** The RestockDataManager instance for managing restock data operations. */
    private RestockDataManager restockDataModel;
    /** The list of RestockData objects retrieved from the database. */
    private List<RestockData> restockList;
    /** The list tracking open child GUI windows. */
    private final List<JFrame> childGUIs = new ArrayList<>();
    /** The map tracking active GUI instances to ensure single-instance behavior. */
    private final Map<Class<? extends JFrame>, JFrame> activeGUIs = new HashMap<>();

    /**
     * Default constructor that initializes the RestockHistoryGUI.
     * Centers the window and sets up the form components.
     */
    public RestockHistoryGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    /**
     * Constructor that initializes the RestockHistoryGUI with a provided RestockDataManager.
     * Populates the table with restock data and applies table formatting.
     *
     * @param restockDataManager The RestockDataManager instance for database operations
     */
    public RestockHistoryGUI(RestockDataManager restockDataManager) {
        // Assign the restock data manager
        this.restockDataModel = restockDataManager;
        // Load the restock list from the data manager
        this.restockList = restockDataManager.getRestockList();
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Populate the table with restock data
        updateRestockTable();
        // Apply table renderer for formatting (541 is the table width)
        new RestockHistoryTableRenderer(restockHistorytbl, 541);
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
     * Updates the restock history table with data from the restock list.
     */
    private void updateRestockTable() {
        // Get the table model
        DefaultTableModel restockHistoryTblModel = (DefaultTableModel) restockHistorytbl.getModel();
        // Clear existing rows
        restockHistoryTblModel.setRowCount(0);

        // Iterate through the restock list
        for (RestockData restock : restockList) {
            // Add each restock record as a new row
            restockHistoryTblModel.addRow(new Object[]{
                restock.getRestockId(),
                restock.getRestockDateYear() + "-" + restock.getRestockDateMonth() + "-" + restock.getRestockDateDay(),
                restock.getRestockDateTime()
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

        restockHistoryScrollPane = new javax.swing.JScrollPane();
        restockHistorytbl = new javax.swing.JTable();
        exportBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        design1 = new javax.swing.JLabel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        deleteBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        detailsBtn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        restockHistorytbl.setAutoCreateRowSorter(true);
        restockHistorytbl.getTableHeader().setReorderingAllowed(false);
        restockHistorytbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Date", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        restockHistoryScrollPane.setViewportView(restockHistorytbl);

        exportBtn.setText("🖨️ Export");
        exportBtn.setInheritsPopupMenu(true);
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });

        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restockhist_title.png"))); // NOI18N

        design1.setBackground(new java.awt.Color(153, 204, 0));
        design1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        design1.setOpaque(true);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Year", "Month", "Day", "Date" }));

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

        detailsBtn1.setText("See Details");
        detailsBtn1.setInheritsPopupMenu(true);
        detailsBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(restockHistoryScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(detailsBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(design1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField)
                    .addComponent(searchPrmtrBox)
                    .addComponent(deleteBtn)
                    .addComponent(refreshBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restockHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(detailsBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(design1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 453, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the selected restock record after user confirmation.
     *
     * @param evt The ActionEvent triggered by clicking the "Delete" button
     */
    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = restockHistorytbl.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a restock to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view index to model index
        selectedRow = restockHistorytbl.convertRowIndexToModel(selectedRow);
        // Get the restock ID from the selected row
        int restockId = (int) restockHistorytbl.getModel().getValueAt(selectedRow, 0);

        // Prompt user to confirm deletion
        int confirmDelete = JOptionPane.showConfirmDialog(this,
            "Do you really want to delete restock ID " + restockId + "?",
            "Delete",
            JOptionPane.YES_NO_OPTION);
        // Proceed with deletion if user confirms
        if (confirmDelete == JOptionPane.YES_OPTION) {
            // Attempt to delete the restock from the database
            boolean deleteSuccess = restockDataModel.deleteRestock(restockId);
            if (deleteSuccess) {
                // Refresh the restock list
                restockList = restockDataModel.getRestockList();
                // Update the table to reflect the deletion
                updateRestockTable();
                // Display success message
                JOptionPane.showMessageDialog(this, "Restock deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display error message if deletion fails
                JOptionPane.showMessageDialog(this, "Failed to delete restock.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed
    
    /**
     * Handles the action when the "Refresh" button is clicked.
     * Updates the restock history table with the latest data.
     *
     * @param evt The ActionEvent triggered by clicking the "Refresh" button
     */
    private void refreshBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBtnActionPerformed
        // Refresh the table with the latest restock data
        updateRestockTable();
    }//GEN-LAST:event_refreshBtnActionPerformed
    
    /**
     * Handles the action when the "See Details" button is clicked.
     * Opens a single instance of RHDetailsGUI to display details of the selected restock.
     *
     * @param evt The ActionEvent triggered by clicking the "See Details" button
     */
    private void detailsBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsBtn1ActionPerformed
        // Get the index of the selected row
        int selectedRow = restockHistorytbl.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, "Please select a restock to view details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view index to model index
        selectedRow = restockHistorytbl.convertRowIndexToModel(selectedRow);
        // Get the restock ID from the selected row
        int restockId = (int) restockHistorytbl.getModel().getValueAt(selectedRow, 0);
        // Find the corresponding RestockData object
        RestockData selectedRestock = restockList.stream()
            .filter(r -> r.getRestockId() == restockId)
            .findFirst()
            .orElse(null);

        // Check if the restock data was found
        if (selectedRestock != null) {
            // Launch or focus a single instance of RHDetailsGUI
            launchSingleInstance(RHDetailsGUI.class, () -> {
                // Create a new RHDetailsGUI instance
                RHDetailsGUI rhDetailGUI = new RHDetailsGUI(selectedRestock);
                // Pack the GUI to fit its contents
                rhDetailGUI.pack();
                // Center the GUI on the screen
                rhDetailGUI.setLocationRelativeTo(null);
                // Set the default close operation to dispose
                rhDetailGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                return rhDetailGUI;
            });
        } else {
            // Display error message if restock data is not found
            JOptionPane.showMessageDialog(this, "Restock not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_detailsBtn1ActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the restock history table based on the search text and selected parameter.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Get the table model
        DefaultTableModel restockHistoryTblModel = (DefaultTableModel) restockHistorytbl.getModel();
        // Create a new TableRowSorter for filtering
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(restockHistoryTblModel);
        // Apply the sorter to the table
        restockHistorytbl.setRowSorter(sorter);

        // Retrieve and trim the search text
        String searchText = searchTxtField.getText().trim();
        // Get the selected search parameter
        String searchParameter = searchPrmtrBox.getSelectedItem().toString();

        // Clear the filter if the search text is empty
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null);
            return;
        }

        // Convert month name to numeric format if searching by month
        String filterText = searchParameter.equals("Month") ? MonthConverter.monthNameToNumeric(searchText) : searchText;

        // Determine the column index based on the search parameter
        int columnIndex = switch (searchParameter) {
            case "ID" -> 0;
            case "Date", "Year", "Month", "Day" -> 1;
            default -> throw new IllegalArgumentException("Invalid search parameter: " + searchParameter);
        };

        try {
            // Create a row filter based on the search parameter
            RowFilter<DefaultTableModel, Object> filter = switch (searchParameter) {
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
     * Generates and exports a restock history report.
     *
     * @param evt The ActionEvent triggered by clicking the "Export" button
     */
    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        // Create a RestockReportGenerator with the restock list
        RestockReportGenerator reportGenerator = new RestockReportGenerator(restockList);
        // Generate and export the restock history report
        reportGenerator.generateReport(this);
    }//GEN-LAST:event_exportBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtn;
    private javax.swing.JLabel design1;
    private javax.swing.JButton detailsBtn1;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JScrollPane restockHistoryScrollPane;
    private javax.swing.JTable restockHistorytbl;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
