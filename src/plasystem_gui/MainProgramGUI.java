package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.DatabaseFileChooser;
import plasystem_functions.TableRowSelector;
import plasystem_functions.TableAlignmentRenderer;
import plasystem_functions.ProductData;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

/**
 * MainProgramGUI class represents the main window of the program displaying game data.
 * Initializes and populates the main table with game data upon program start.
 */
public class MainProgramGUI extends JFrame {
    /**
     * The file path retrieved from the database file chooser to access game data.
     * It will be set once and used throughout the program.
     */
    String filePath = DatabaseFileChooser.getFilePath();
    
    // Instance of DataHandling to manage data operations
    DataHandling dataHandling = new DataHandling(filePath);
    
    // List of ProductData retrieved from the file
    LinkedList<ProductData> productList = dataHandling.getList();
    
    /**
     * Constructor initializing the Main Program GUI.
     * Populates the table with existing game data upon program start.
     */
    public MainProgramGUI() {
        initComponents(); // Initialize components of the GUI
        setLocationRelativeTo(null); // Set the window to open in the center of the screen
        new TableAlignmentRenderer(plasystemTbl); // Apply table alignment
        
        /** 
         *  If the Main Program is opened, data will be automatically added to the 
         */
        if (productList != null && !productList.isEmpty()) {
            DefaultTableModel model = (DefaultTableModel) plasystemTbl.getModel();
            model.setRowCount(0);

            for (ProductData element : productList) {
                Object[] rowData = {
                    element.getProductID(),
                    element.getProductQuantity(),
                    element.getProductPrice(),
                    element.getProductName(),
                    element.getProductSize(),
                    element.getProductBrand(),
                    element.getProductType(),
                    element.getProductRestockValue() // Ensure Restock Value is added
                };
                model.addRow(rowData);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Database is empty");
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

        Main_Button_Panel = new javax.swing.JPanel();
        addBtn = new javax.swing.JButton();
        transactBtn = new javax.swing.JButton();
        rstockBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lowstockBtn = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        dataPanel = new javax.swing.JPanel();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        searchPanel = new javax.swing.JPanel();
        searchTxtField = new javax.swing.JTextField();
        searchPrmtrBox = new javax.swing.JComboBox<>();
        plasystemTblScrollPane = new javax.swing.JScrollPane();
        plasystemTbl = new javax.swing.JTable();
        Secondary_Buttons = new javax.swing.JPanel();
        Roles = new javax.swing.JButton();
        Print = new javax.swing.JButton();
        Logout = new javax.swing.JButton();
        TransactionHistory = new javax.swing.JButton();
        RestockHistory = new javax.swing.JButton();
        Background_Design = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(2600, 1300));

        Main_Button_Panel.setBackground(new java.awt.Color(0, 153, 255));
        Main_Button_Panel.setForeground(new java.awt.Color(51, 153, 255));

        addBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/addinventory_button.png"))); // NOI18N
        addBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        addBtn.setContentAreaFilled(false);
        addBtn.setInheritsPopupMenu(true);
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        transactBtn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        transactBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/transactbutton.png"))); // NOI18N
        transactBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        transactBtn.setContentAreaFilled(false);
        transactBtn.setInheritsPopupMenu(true);
        transactBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transactBtnActionPerformed(evt);
            }
        });

        rstockBtn.setBackground(new java.awt.Color(51, 204, 0));
        rstockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rstockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/restock_button.png"))); // NOI18N
        rstockBtn.setText("Re-stock");
        rstockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rstockBtn.setContentAreaFilled(false);
        rstockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rstockBtn.setInheritsPopupMenu(true);
        rstockBtn.setOpaque(true);
        rstockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rstockBtnActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("|");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("|");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("|");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("|");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("|");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("|");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel10.setBackground(new java.awt.Color(51, 102, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/plasystem.png"))); // NOI18N
        jLabel10.setInheritsPopupMenu(false);
        jLabel10.setOpaque(true);

        lowstockBtn.setBackground(new java.awt.Color(51, 204, 0));
        lowstockBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lowstockBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/lowstockbutton.png"))); // NOI18N
        lowstockBtn.setText("Re-stock");
        lowstockBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lowstockBtn.setContentAreaFilled(false);
        lowstockBtn.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        lowstockBtn.setInheritsPopupMenu(true);
        lowstockBtn.setOpaque(true);
        lowstockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lowstockBtnActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("|");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 55)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("|");

        javax.swing.GroupLayout Main_Button_PanelLayout = new javax.swing.GroupLayout(Main_Button_Panel);
        Main_Button_Panel.setLayout(Main_Button_PanelLayout);
        Main_Button_PanelLayout.setHorizontalGroup(
            Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lowstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11)))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Main_Button_PanelLayout.setVerticalGroup(
            Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(49, 49, 49)))
                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(transactBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(75, 75, 75))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Main_Button_PanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(77, 77, 77)))
                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8))
                .addGroup(Main_Button_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel11))
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel12))
                    .addGroup(Main_Button_PanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lowstockBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(245, 245, 245)
                .addComponent(jLabel3)
                .addContainerGap(239, Short.MAX_VALUE))
        );

        dataPanel.setOpaque(false);

        editBtn.setText("Edit");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dataPanelLayout = new javax.swing.GroupLayout(dataPanel);
        dataPanel.setLayout(dataPanelLayout);
        dataPanelLayout.setHorizontalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteBtn)
                .addContainerGap())
        );
        dataPanelLayout.setVerticalGroup(
            dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dataPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(dataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBtn)
                    .addComponent(deleteBtn))
                .addContainerGap())
        );

        searchPanel.setOpaque(false);

        searchTxtField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTxtFieldKeyReleased(evt);
            }
        });

        searchPrmtrBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "Size", "Brand", "Type", "Price", "Quantity" }));

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPrmtrBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        plasystemTbl.setAutoCreateRowSorter(true);
        plasystemTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Quantity", "Price", "Name", "Size", "Brand", "Type", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        plasystemTbl.getTableHeader().setReorderingAllowed(false);
        plasystemTblScrollPane.setViewportView(plasystemTbl);
        if (plasystemTbl.getColumnModel().getColumnCount() > 0) {
            plasystemTbl.getColumnModel().getColumn(0).setPreferredWidth(25);
            plasystemTbl.getColumnModel().getColumn(1).setPreferredWidth(20);
            plasystemTbl.getColumnModel().getColumn(2).setPreferredWidth(25);
            plasystemTbl.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        Secondary_Buttons.setOpaque(false);

        Roles.setText("👤 User Roles");
        Roles.setInheritsPopupMenu(true);
        Roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RolesActionPerformed(evt);
            }
        });

        Print.setText("🖨️ Print Inventory");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        Logout.setBackground(new java.awt.Color(255, 102, 102));
        Logout.setForeground(new java.awt.Color(255, 255, 255));
        Logout.setText("↩ Logout");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        TransactionHistory.setText("🔄 Transaction History");
        TransactionHistory.setInheritsPopupMenu(true);
        TransactionHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransactionHistoryActionPerformed(evt);
            }
        });

        RestockHistory.setText("🔄 Restock History");
        RestockHistory.setInheritsPopupMenu(true);
        RestockHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestockHistoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Secondary_ButtonsLayout = new javax.swing.GroupLayout(Secondary_Buttons);
        Secondary_Buttons.setLayout(Secondary_ButtonsLayout);
        Secondary_ButtonsLayout.setHorizontalGroup(
            Secondary_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Secondary_ButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(RestockHistory)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TransactionHistory)
                .addGap(18, 18, 18)
                .addComponent(Roles, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Print)
                .addContainerGap())
        );
        Secondary_ButtonsLayout.setVerticalGroup(
            Secondary_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Secondary_ButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Secondary_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RestockHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Print, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Roles, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TransactionHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_main/bg_mainprogram.png"))); // NOI18N

        Background_Design.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout Background_DesignLayout = new javax.swing.GroupLayout(Background_Design);
        Background_Design.setLayout(Background_DesignLayout);
        Background_DesignLayout.setHorizontalGroup(
            Background_DesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Background_DesignLayout.setVerticalGroup(
            Background_DesignLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Main_Button_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(512, 512, 512)
                        .addComponent(dataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(plasystemTblScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Secondary_Buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Background_Design))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plasystemTblScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Secondary_Buttons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(Main_Button_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Background_Design, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        setBackground(Color. BLUE);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Method triggered when the 'Transact' button is clicked in the GUI.
     * Initializes and opens a new TransactionGUI to start a transaction process.
     * Passes the gameList, file path, and main data table to the TransactionGUI.
     * @param evt Action event generated by the button click
     */
    private void transactBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_transactBtnActionPerformed
        TransactionGUI transactPanel = new TransactionGUI(productList, filePath, plasystemTbl);
        transactPanel.setVisible(true);
        transactPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_transactBtnActionPerformed

    
    /**
     * Method triggered when the 'Add' button is clicked in the GUI.
     * Initializes and opens a new AddDataGUI to add new game data.
     * Passes the gameList, main data table, and file path to the AddDataGUI.
     * @param evt Action event generated by the button click
     */
    private void addBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        JFrame addPanel = new AddDataGUI(productList, plasystemTbl, filePath);
        addPanel.setVisible(true);
        addPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_addBtnActionPerformed
    
    /**
     * Method triggered when the 'Edit' button is clicked in the GUI.
     * Retrieves data from the selected row in the main table and opens the EditDataGUI for editing.
     * @param evt Action event generated by the button click
     */
    private void editBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        // Get the index of the selected row in the main table
        int selectedRow = plasystemTbl.getSelectedRow();
        
        // Check if a row is selected (row index starts from 0, -1 means no selection)
        if (selectedRow != -1) {
            // Create an instance of TableRowSelector to extract data from the selected row
            TableRowSelector rowSelector = new TableRowSelector(plasystemTbl);
        
            EditDataGUI edit = new EditDataGUI(
             selectedRow, 
             filePath, 
             productList, 
             plasystemTbl,
            // Retrieve data using TableRowSelector getters
            rowSelector.getTblProductID(),
            rowSelector.getQuantity(),
            rowSelector.getTblPrice(),
            rowSelector.getTblName(),
            rowSelector.getTblSize(),
            rowSelector.getTblBrand(),
            rowSelector.getTblType(),
            rowSelector.getRestockValue() // Update the selected row index
            );

            // Set properties for the edit frame
            edit.setVisible(true);
            edit.pack();
            edit.setLocationRelativeTo(null);
            edit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_editBtnActionPerformed
    
    /**
     * Method triggered when the 'Delete' button is clicked in the GUI.
     * Deletes the selected row from the main 
     * @param evt Action event generated by the button click
     */
    private void deleteBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // Check if a row is selected in the main table
        if(plasystemTbl.getSelectedRow() != -1){
            // Delete data corresponding to the selected row
            dataHandling.deleteData(plasystemTbl, productList, plasystemTbl.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteBtnActionPerformed
    
    /**
     * Method triggered when text is typed into the search text field.
     * Filters rows in the main table based on the entered search text.
     * @param evt Key event generated when a key is released in the search text field
     */
    private void searchTxtFieldKeyReleased(KeyEvent evt) {//GEN-FIRST:event_searchTxtFieldKeyReleased
        DefaultTableModel model = (DefaultTableModel) plasystemTbl.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        plasystemTbl.setRowSorter(sorter);
        
        // Get the selected column name from the search parameter box
        String columnNameToSearch = searchPrmtrBox.getSelectedItem().toString(); // Replace "ColumnName" with the actual column name
        int columnIndex = model.findColumn(columnNameToSearch);
        
        // Apply a row filter based on the entered search text for the selected column
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTxtField.getText(), columnIndex));
    }//GEN-LAST:event_searchTxtFieldKeyReleased

    private void rstockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rstockBtnActionPerformed
        JFrame panel = new RestockGUI(productList, plasystemTbl, filePath);
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_rstockBtnActionPerformed

    private void lowstockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lowstockBtnActionPerformed
        JFrame panel = new LowStockGUI(productList, filePath);
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_lowstockBtnActionPerformed

    private void RolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RolesActionPerformed
        JFrame panel = new UserRolesGUI();
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_RolesActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintActionPerformed

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        launchLaunchPanel();
    }//GEN-LAST:event_LogoutActionPerformed

    private void TransactionHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransactionHistoryActionPerformed
        JFrame panel = new TransactionHistoryGUI();
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_TransactionHistoryActionPerformed

    private void RestockHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RestockHistoryActionPerformed
        JFrame panel = new RestockHistoryGUI();
        panel.setVisible(true);
        panel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_RestockHistoryActionPerformed
    private void launchLaunchPanel() {
        JFrame launchPanel = new LaunchPanelGUI(); // Create an instance of the main program GUI
        launchPanel.setVisible(true); // Set the main program frame visible
        this.dispose(); // Dispose of the current frame (LaunchPanelGUI)
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Background_Design;
    private javax.swing.JButton Logout;
    private javax.swing.JPanel Main_Button_Panel;
    private javax.swing.JButton Print;
    private javax.swing.JButton RestockHistory;
    private javax.swing.JButton Roles;
    private javax.swing.JPanel Secondary_Buttons;
    private javax.swing.JButton TransactionHistory;
    private javax.swing.JButton addBtn;
    private javax.swing.JPanel dataPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton lowstockBtn;
    private javax.swing.JTable plasystemTbl;
    private javax.swing.JScrollPane plasystemTblScrollPane;
    private javax.swing.JButton rstockBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JComboBox<String> searchPrmtrBox;
    private javax.swing.JTextField searchTxtField;
    private javax.swing.JButton transactBtn;
    // End of variables declaration//GEN-END:variables
}
