package plasystem_gui;

import plasystem_functions.UserAccountManager;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LaunchPanelGUI extends javax.swing.JFrame {
    
    private UserAccountManager loginAccount;
    
    public LaunchPanelGUI() {
        initComponents(); // Initialize GUI components
        setLocationRelativeTo(null); // Set the location of the frame to the center of the screen
        
        // Initialize the UserAccountManager and load user accounts
        loginAccount = new UserAccountManager();
        
        // Add KeyListeners for Enter key action
        addEnterKeyListener(UsernameTextField);
        addEnterKeyListener(PasswordTextField);
    }
    
    private void addEnterKeyListener(JTextField textField) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    launchButtonActionPerformed(null); // Simulate button click when Enter is pressed
                }
            }
        });
    }
    
    private void launchMainProgram() {
        JFrame mainProgram = new MainProgramGUI(); // Create an instance of the main program GUI
        mainProgram.setVisible(true); // Set the main program frame visible
        this.dispose(); // Dispose of the current frame (LaunchPanelGUI)
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        PasswordTextField = new javax.swing.JPasswordField();
        VersionTextField = new javax.swing.JLabel();
        launchButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        PasswordLabel = new javax.swing.JLabel();
        UsernameTextField = new javax.swing.JTextField();
        UsernameLabel = new javax.swing.JLabel();
        Background_Design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        VersionTextField.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        VersionTextField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VersionTextField.setText("version 2.0.0");

        launchButton.setBackground(new java.awt.Color(0, 153, 255));
        launchButton.setForeground(new java.awt.Color(255, 255, 255));
        launchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/launchbtn_icon.png"))); // NOI18N
        launchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        launchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                launchButtonActionPerformed(evt);
            }
        });

        exitButton.setBackground(new java.awt.Color(255, 102, 102));
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/exitbtn_icon.png"))); // NOI18N
        exitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exitButton.setInheritsPopupMenu(true);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        PasswordLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PasswordLabel.setText(" Password :");

        UsernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        UsernameLabel.setText("Username :");

        Background_Design.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Background_Design.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/launchpanel_mainmenu.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(203, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(VersionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(235, 235, 235))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(UsernameLabel)
                            .addComponent(PasswordLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(UsernameTextField)
                            .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(220, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Background_Design, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1089, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(374, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(UsernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UsernameLabel))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PasswordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PasswordLabel))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(launchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)))
                .addComponent(VersionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(Background_Design)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void launchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_launchButtonActionPerformed
        String username = UsernameTextField.getText().trim();
        String password = new String(PasswordTextField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password.");
            return;
        }

        if (loginAccount.validateUserLogin(username, password)) {
            launchMainProgram();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }//GEN-LAST:event_launchButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0); // Close the application
    }//GEN-LAST:event_exitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Background_Design;
    private javax.swing.JLabel PasswordLabel;
    private javax.swing.JPasswordField PasswordTextField;
    private javax.swing.JLabel UsernameLabel;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JLabel VersionTextField;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton launchButton;
    // End of variables declaration//GEN-END:variables
}
