package plasystem_gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import plasystem_functions.UserAccountData;
import plasystem_functions.UserAccountDataManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A graphical user interface (GUI) window for managing user accounts.
 * This class provides a table to display user accounts and buttons to add, edit, delete, and search accounts.
 */
public class UserAccountsGUI extends JFrame {
    /** The UserAccountDataManager instance for managing user account operations. */
    private UserAccountDataManager userAccountDataModel;
    /** The TableRowSorter for sorting and filtering the user accounts table. */
    private TableRowSorter<DefaultTableModel> tableSorter;
    /** The list tracking open child GUI windows. */
    private final List<JFrame> childGUIs = new ArrayList<>();
    /** The map tracking active GUI instances to ensure single-instance behavior. */
    private final Map<Class<? extends JFrame>, JFrame> activeGUIs = new HashMap<>();
    
    /**
     * Default constructor that initializes the UserAccountsGUI.
     * Centers the window and sets up the form components.
     */
    public UserAccountsGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the UserAccountsGUI with a provided UserAccountDataManager.
     * Sets up the form components, centers the window, and loads user account data into the table.
     *
     * @param userAccountDataManager The UserAccountDataManager instance for database operations
     */
    public UserAccountsGUI(UserAccountDataManager userAccountDataManager) {
        // Assign the user account data manager
        this.userAccountDataModel = userAccountDataManager;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Load user accounts from the database
        userAccountDataModel.loadUserAccounts();
        // Populate the table with user account data
        loadUserAccountsTable();
        
        // Initialize the TableRowSorter
        DefaultTableModel model = (DefaultTableModel) userAccountsTable.getModel();
        tableSorter = new TableRowSorter<>(model);
        // Apply the sorter to the table
        userAccountsTable.setRowSorter(tableSorter);
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
     * Loads user account data into the JTable.
     */
    private void loadUserAccountsTable() {
        // Get the list of user accounts
        List<UserAccountData> userList = userAccountDataModel.getUserAccounts();
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) userAccountsTable.getModel();
        // Clear existing rows
        model.setRowCount(0);

        // Iterate through the user list
        for (UserAccountData user : userList) {
            // Add each user as a new row
            model.addRow(new Object[] {
                user.getUsername(),
                user.getUserPassword(),
                user.getUserRole()
            });
        }
    }
    
    /**
     * Refreshes the user accounts table with the latest data.
     */
    public void refreshTable() {
        // Reload the table with current user account data
        loadUserAccountsTable();
        // Reapply the current search filter if the search field is not empty
        if (searchTxtField.getText().trim().length() > 0) {
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

        userAccScrollPane = new javax.swing.JScrollPane();
        userAccountsTable = new javax.swing.JTable();
        Design = new javax.swing.JLabel();
        editUserActBtn = new javax.swing.JButton();
        addUserActBtn = new javax.swing.JButton();
        deleteUserActBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        userAccountsTable.setAutoCreateRowSorter(true);
        userAccountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Password", "Role"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        userAccountsTable.getTableHeader().setReorderingAllowed(false);
        userAccScrollPane.setViewportView(userAccountsTable);

        Design.setBackground(new java.awt.Color(255, 204, 102));
        Design.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Design.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/userrolestitle.png"))); // NOI18N
        Design.setOpaque(true);

        editUserActBtn.setText("Edit");
        editUserActBtn.setInheritsPopupMenu(true);
        editUserActBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUserActBtnActionPerformed(evt);
            }
        });

        addUserActBtn.setText("Add");
        addUserActBtn.setInheritsPopupMenu(true);
        addUserActBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActBtnActionPerformed(evt);
            }
        });

        deleteUserActBtn.setBackground(new java.awt.Color(255, 102, 102));
        deleteUserActBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteUserActBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteUserActBtn.setText("Delete");
        deleteUserActBtn.setInheritsPopupMenu(true);
        deleteUserActBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserActBtnActionPerformed(evt);
            }
        });

        searchPanel.setOpaque(false);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Username", "Password", "Role" }));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userAccScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editUserActBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addUserActBtn))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteUserActBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteUserActBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userAccScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editUserActBtn)
                    .addComponent(addUserActBtn))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 446, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Handles the action when the "Add" button is clicked.
     * Opens a single instance of the AddUserAccountGUI to add a new user account.
     *
     * @param evt The ActionEvent triggered by clicking the "Add" button
     */
    private void addUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserActBtnActionPerformed
        // Launch or focus a single instance of AddUserAccountGUI
        launchSingleInstance(AddUserAccountGUI.class, () -> {
            // Create a new AddUserAccountGUI instance
            AddUserAccountGUI addUserActGUI = new AddUserAccountGUI(this, userAccountDataModel);
            // Pack the GUI to fit its contents
            addUserActGUI.pack();
            // Center the GUI on the screen
            addUserActGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            addUserActGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return addUserActGUI;
        });
    }//GEN-LAST:event_addUserActBtnActionPerformed
    
    /**
     * Handles the action when the "Delete" button is clicked.
     * Deletes the selected user account after confirmation, preventing self-deletion by admin.
     *
     * @param evt The ActionEvent triggered by clicking the "Delete" button
     */
    private void deleteUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = userAccountsTable.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, 
                "Please select a user to delete.",
                "Selection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view row index to model row index to handle sorting
        int modelRow = userAccountsTable.convertRowIndexToModel(selectedRow);

        // Get the username from the selected row (column 0)
        String username = (String) userAccountsTable.getModel().getValueAt(modelRow, 0);

        // Prevent admin from deleting their own account
        if ("admin".equals(userAccountDataModel.getLoggedInRole()) && 
            username.equals(userAccountDataModel.getLoggedInUsername())) {
            // Display error message for self-deletion attempt
            JOptionPane.showMessageDialog(this, 
                "You cannot delete your own account.",
                "Permission Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt user to confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete the user '" + username + "'?",
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        // Proceed with deletion if user confirms
        if (confirm == JOptionPane.YES_OPTION) {
            // Attempt to delete the user account
            boolean success = userAccountDataModel.deleteUserAccount(username);
            if (success) {
                // Display success message
                JOptionPane.showMessageDialog(this, 
                    "User account deleted successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                // Refresh the table
                refreshTable();
            }
        }
    }//GEN-LAST:event_deleteUserActBtnActionPerformed
    
    /**
     * Handles the action when the "Edit" button is clicked.
     * Opens a single instance of the EditUserAccountGUI for the selected user, preventing self-editing by admin.
     *
     * @param evt The ActionEvent triggered by clicking the "Edit" button
     */
    private void editUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserActBtnActionPerformed
        // Get the index of the selected row
        int selectedRow = userAccountsTable.getSelectedRow();
        // Check if a row is selected
        if (selectedRow == -1) {
            // Display error message if no row is selected
            JOptionPane.showMessageDialog(this, 
                "Please select a user to edit.",
                "Selection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view row index to model row index to handle sorting
        int modelRow = userAccountsTable.convertRowIndexToModel(selectedRow);

        // Get the username from the selected row
        String username = (String) userAccountsTable.getModel().getValueAt(modelRow, 0);

        // Prevent admin from editing their own account
        if ("admin".equals(userAccountDataModel.getLoggedInRole()) && 
            username.equals(userAccountDataModel.getLoggedInUsername())) {
            // Display error message for self-editing attempt
            JOptionPane.showMessageDialog(this, 
                "You cannot edit your own account.",
                "Permission Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Find the selected user's data
        UserAccountData selectedUser = userAccountDataModel.getUserAccounts()
            .stream()
            .filter(user -> user.getUsername().equals(username))
            .findFirst()
            .orElse(null);

        // Validate that the user data was found
        if (selectedUser == null) {
            // Display error message if user data is not found
            JOptionPane.showMessageDialog(this, 
                "User data not found.",
                "Data Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Launch or focus a single instance of EditUserAccountGUI
        launchSingleInstance(EditUserAccountGUI.class, () -> {
            // Create a new EditUserAccountGUI instance
            EditUserAccountGUI editUserActGUI = new EditUserAccountGUI(this, userAccountDataModel, selectedUser);
            // Pack the GUI to fit its contents
            editUserActGUI.pack();
            // Center the GUI on the screen
            editUserActGUI.setLocationRelativeTo(null);
            // Set the default close operation to dispose
            editUserActGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            return editUserActGUI;
        });
    }//GEN-LAST:event_editUserActBtnActionPerformed
    
    /**
     * Handles the key release event in the search text field.
     * Filters the user accounts table based on the search text and selected column.
     *
     * @param evt The KeyEvent triggered by releasing a key in the search text field
     */
    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        // Retrieve and trim the search text
        String searchText = searchTxtField.getText().trim();
        // Get the selected column name for searching
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        // Get the table model
        DefaultTableModel model = (DefaultTableModel) userAccountsTable.getModel();
        
        // Find the index of the selected column
        int columnIndex = model.findColumn(columnNameToSearch);
        
        // Validate the column index
        if (columnIndex == -1) {
            // Display error message if the column is invalid
            JOptionPane.showMessageDialog(this, 
                "Invalid column selected for search.",
                "Search Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Apply the row filter
        if (searchText.isEmpty()) {
            // Clear the filter if the search text is empty
            tableSorter.setRowFilter(null);
        } else {
            // Apply a case-insensitive regex filter to the selected column
            tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JButton addUserActBtn;
    private javax.swing.JButton deleteUserActBtn;
    private javax.swing.JButton editUserActBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JScrollPane userAccScrollPane;
    private javax.swing.JTable userAccountsTable;
    // End of variables declaration//GEN-END:variables
}
