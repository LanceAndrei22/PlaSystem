package plasystem_gui;

import plasystem_functions.ProductDataManager;
import plasystem_functions.ProductData;
import javax.swing.*;
import javax.swing.table.*;
import java.util.LinkedList;

public class RestockGUI extends javax.swing.JFrame {

    private ProductDataManager dataHandler;
    private JTable mainTable;
    private String filePath;
    private LinkedList<ProductData> productList;
    
    public RestockGUI(){
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public RestockGUI(LinkedList<ProductData> productList1, JTable plasystemTbl, String filePath) {
        this.filePath = filePath;
        this.dataHandler = new ProductDataManager(filePath);
        this.mainTable = plasystemTbl;
        this.productList = dataHandler.getList(); // Load full product list
        initComponents();
        setLocationRelativeTo(null);
        populateTable();
    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) restockTable.getModel();
        model.setRowCount(0);
        for (ProductData product : productList) {
            if (product.getProductQuantity() < product.getProductRestockValue()) {
                model.addRow(new Object[]{false, product.getProductID(), product.getProductName(), 
                    product.getProductQuantity(), product.getProductRestockValue(), 0});
            }
        }
    }
    
    private void restockItems() {
        DefaultTableModel restockModel = (DefaultTableModel) restockTable.getModel();
        LinkedList<ProductData> fullList = dataHandler.getList();
        boolean updated = false;

        for (int i = 0; i < restockModel.getRowCount(); i++) {
            boolean selected = (Boolean) restockModel.getValueAt(i, 0);
            int incomingQty = (Integer) restockModel.getValueAt(i, 5);
            if (selected && incomingQty > 0) {
                String productId = (String) restockModel.getValueAt(i, 1);
                for (ProductData product : fullList) {
                    if (product.getProductID().equals(productId)) {
                        product.setProductQuantity(product.getProductQuantity() + incomingQty);
                        updated = true;
                        break;
                    }
                }
            }
        }

        if (updated) {
            if (ProductDataManager.saveInventoryChanges(fullList, filePath)) {
                DefaultTableModel mainModel = (DefaultTableModel) mainTable.getModel();
                mainModel.setRowCount(0);
                for (ProductData product : fullList) {
                    mainModel.addRow(new Object[]{
                        product.getProductID(), product.getProductName(), product.getProductBrand(),
                        product.getProductSize(), product.getProductType(), product.getProductPrice(),
                        product.getProductQuantity(), product.getProductRestockValue()
                    });
                }
                JOptionPane.showMessageDialog(this, "Restocking successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error saving changes!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No items selected or invalid quantities!", "Error", JOptionPane.ERROR_MESSAGE);
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

        labelPane = new javax.swing.JPanel();
        Titlelabel = new javax.swing.JLabel();
        restockScrollPane = new javax.swing.JScrollPane();
        restockTable = new javax.swing.JTable();
        cancelBtn = new javax.swing.JButton();
        restockBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        labelPane.setBackground(new java.awt.Color(0, 204, 51));

        Titlelabel.setBackground(new java.awt.Color(0, 204, 51));
        Titlelabel.setForeground(new java.awt.Color(0, 204, 51));
        Titlelabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titlelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/restocktitle.png"))); // NOI18N

        restockScrollPane.setBorder(null);

        restockTable.setAutoCreateRowSorter(true);
        restockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Select", "Product ID", "Name", "Current Quantity", "Restock Value", "Incoming Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        restockScrollPane.setViewportView(restockTable);

        javax.swing.GroupLayout labelPaneLayout = new javax.swing.GroupLayout(labelPane);
        labelPane.setLayout(labelPaneLayout);
        labelPaneLayout.setHorizontalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titlelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(restockScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        labelPaneLayout.setVerticalGroup(
            labelPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
        );

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        restockBtn.setText("Restock Selected");
        restockBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restockBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(restockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addGap(182, 182, 182))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restockBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        dispose(); // Close the current window (the frame)
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void restockBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restockBtnActionPerformed
        restockItems();
    }//GEN-LAST:event_restockBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titlelabel;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JPanel labelPane;
    private javax.swing.JButton restockBtn;
    private javax.swing.JScrollPane restockScrollPane;
    private javax.swing.JTable restockTable;
    // End of variables declaration//GEN-END:variables
}
