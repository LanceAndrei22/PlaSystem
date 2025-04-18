package plasystem_gui;

import plasystem_functions.UserAccount;
import plasystem_functions.UserAccountManager;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserAccountsGUI extends javax.swing.JFrame {
    
    private TableRowSorter<DefaultTableModel> sorter; // Store the sorter for reuse

    public UserAccountsGUI() {
        initComponents();
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        loadUserAccounts();
        
        // Initialize the TableRowSorter
        DefaultTableModel model = (DefaultTableModel) UserAccountsTable.getModel();
        sorter = new TableRowSorter<>(model);
        UserAccountsTable.setRowSorter(sorter);
    }
    
    // Method to load user accounts into the JTable
    private void loadUserAccounts() {
        // Fetch all user accounts from the database
        UserAccountManager userAccountManager = new UserAccountManager();
        userAccountManager.loadUserAccounts();  // Load user accounts from DB

        List<UserAccount> userList = userAccountManager.getUserAccounts();  // Get the list of user accounts
        DefaultTableModel model = (DefaultTableModel) UserAccountsTable.getModel();
        model.setRowCount(0); // Clear existing rows

        // Add each user to the table
        for (UserAccount user : userList) {
            model.addRow(new Object[] {
                user.getUsername(),
                user.getUserPassword(),
                user.getUserRole()
            });
        }
    }
    
    // Method to refresh the table
    public void refreshTable() {
        loadUserAccounts();
        // Reapply the current filter if any
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
        UserAccountsTable = new javax.swing.JTable();
        Design = new javax.swing.JLabel();
        editUserActBtn = new javax.swing.JButton();
        addUserActBtn = new javax.swing.JButton();
        deleteUserActBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        UserAccountsTable.setAutoCreateRowSorter(true);
        UserAccountsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        UserAccountsTable.getTableHeader().setReorderingAllowed(false);
        userAccScrollPane.setViewportView(UserAccountsTable);

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

    private void addUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserActBtnActionPerformed
        JFrame panel = new AddUserAccountGUI(this);
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_addUserActBtnActionPerformed

    private void deleteUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserActBtnActionPerformed
        int selectedRow = UserAccountsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a user to delete.",
                "Selection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view row index to model row index to handle sorting
        int modelRow = UserAccountsTable.convertRowIndexToModel(selectedRow);

        // Get the username from the selected row (column 0)
        String username = (String) UserAccountsTable.getModel().getValueAt(modelRow, 0);

        // Confirm deletion
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete the user '" + username + "'?",
            "Confirm Delete", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            UserAccountManager manager = new UserAccountManager();
            boolean success = manager.deleteUserAccount(username);
            if (success) {
                JOptionPane.showMessageDialog(this, 
                    "User account deleted successfully!",
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                refreshTable(); // Refresh the table
            }
        }
    }//GEN-LAST:event_deleteUserActBtnActionPerformed

    private void editUserActBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUserActBtnActionPerformed
        int selectedRow = UserAccountsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Please select a user to edit.",
                "Selection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Convert view row index to model row index to handle sorting
        int modelRow = UserAccountsTable.convertRowIndexToModel(selectedRow);

        // Get user details from the selected row
        String username = (String) UserAccountsTable.getModel().getValueAt(modelRow, 0);
        String password = (String) UserAccountsTable.getModel().getValueAt(modelRow, 1);
        String role = (String) UserAccountsTable.getModel().getValueAt(modelRow, 2);

        // Launch EditUserAccountGUI with the selected user's details
        EditUserAccountGUI panel = new EditUserAccountGUI(this, username, password, role);
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_editUserActBtnActionPerformed

    private void searchTxtFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        String searchText = searchTxtField.getText().trim();
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) UserAccountsTable.getModel();
        
        // Find the column index
        int columnIndex = model.findColumn(columnNameToSearch);
        
        if (columnIndex == -1) {
            JOptionPane.showMessageDialog(this, 
                "Invalid column selected for search.",
                "Search Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Apply the row filter
        if (searchText.isEmpty()) {
            sorter.setRowFilter(null); // Clear filter if search text is empty
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, columnIndex));
        }
    }//GEN-LAST:event_searchTxtFieldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JTable UserAccountsTable;
    private javax.swing.JButton addUserActBtn;
    private javax.swing.JButton deleteUserActBtn;
    private javax.swing.JButton editUserActBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JScrollPane userAccScrollPane;
    // End of variables declaration//GEN-END:variables
}
