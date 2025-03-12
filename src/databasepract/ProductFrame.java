/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package databasepract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 *
 * @author FRANCIS
 */
public class ProductFrame extends javax.swing.JFrame {
    private Connection conn;
    private DefaultTableModel tableModel;

    public ProductFrame() {
        initComponents();
        connectDatabase();
        loadProducts();
    }

    private void connectDatabase() {
        try {
            // Use fully qualified class name to avoid conflicts
            conn = java.sql.DriverManager.getConnection("jdbc:sqlite:information.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, brand TEXT, size TEXT, type TEXT, quantity INTEGER, price REAL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
private void loadProducts() {
        if (conn == null) return;  // Ensure database connection exists
        tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("brand"),
                    rs.getString("size"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addProduct(String name, String brand, String size, String type, int quantity, double price) {
        if (conn == null) return;  // Ensure database connection exists
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO products (name, brand, size, type, quantity, price) VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, name);
            pstmt.setString(2, brand);
            pstmt.setString(3, size);
            pstmt.setString(4, type);
            pstmt.setInt(5, quantity);
            pstmt.setDouble(6, price);
            pstmt.executeUpdate();
            loadProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
private void deleteProduct() {
    if (conn == null) return;  // Ensure database connection exists
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product to delete.");
        return;
    }

    int id = (int) jTable1.getValueAt(selectedRow, 0);
    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    
    if (confirm == JOptionPane.YES_OPTION) {
        try {
            conn.setAutoCommit(false); // Start transaction
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM products WHERE id = ?");
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            
            if (affectedRows > 0) {
                conn.commit(); // Commit transaction
                loadProducts();
                JOptionPane.showMessageDialog(this, "Product deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error: Product not found.");
            }
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback on error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error deleting product.");
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

private void editProduct() {
    if (conn == null) return;

    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a product to edit.");
        return;
    }

    int id = (int) jTable1.getValueAt(selectedRow, 0);
    String currentName = (String) jTable1.getValueAt(selectedRow, 1);
    String currentBrand = (String) jTable1.getValueAt(selectedRow, 2);
    String currentSize = (String) jTable1.getValueAt(selectedRow, 3);
    String currentType = (String) jTable1.getValueAt(selectedRow, 4);
    int currentQuantity = (int) jTable1.getValueAt(selectedRow, 5);
    double currentPrice = (double) jTable1.getValueAt(selectedRow, 6);

    JTextField nameField = new JTextField(currentName);
    JTextField brandField = new JTextField(currentBrand);
    JTextField sizeField = new JTextField(currentSize);
    JTextField typeField = new JTextField(currentType);
    JTextField quantityField = new JTextField(String.valueOf(currentQuantity));
    JTextField priceField = new JTextField(String.valueOf(currentPrice));

    JPanel panel = new JPanel();
    panel.setLayout(new java.awt.GridLayout(6, 2));
    panel.add(new JLabel("Name:"));
    panel.add(nameField);
    panel.add(new JLabel("Brand:"));
    panel.add(brandField);
    panel.add(new JLabel("Size:"));
    panel.add(sizeField);
    panel.add(new JLabel("Type:"));
    panel.add(typeField);
    panel.add(new JLabel("Quantity:"));
    panel.add(quantityField);
    panel.add(new JLabel("Price:"));
    panel.add(priceField);

    int result = JOptionPane.showConfirmDialog(this, panel, "Edit Product Details", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        try {
            String newName = nameField.getText().trim();
            String newBrand = brandField.getText().trim();
            String newSize = sizeField.getText().trim();
            String newType = typeField.getText().trim();
            int newQuantity = Integer.parseInt(quantityField.getText().trim());
            double newPrice = Double.parseDouble(priceField.getText().trim());

            if (newName.equals(currentName) && newBrand.equals(currentBrand) && newSize.equals(currentSize) &&
                newType.equals(currentType) && newQuantity == currentQuantity && newPrice == currentPrice) {
                JOptionPane.showMessageDialog(this, "No changes detected.");
                return;
            }

            conn.setAutoCommit(false); // Start transaction
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE products SET name=?, brand=?, size=?, type=?, quantity=?, price=? WHERE id=?"
            );
            pstmt.setString(1, newName);
            pstmt.setString(2, newBrand);
            pstmt.setString(3, newSize);
            pstmt.setString(4, newType);
            pstmt.setInt(5, newQuantity);
            pstmt.setDouble(6, newPrice);
            pstmt.setInt(7, id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                conn.commit(); // Commit transaction
                loadProducts();
                JOptionPane.showMessageDialog(this, "Product updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Error updating product.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter correct values.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Rollback on error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error updating product.");
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); // Reset auto-commit
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "name", "brand", "size", "type", "quantity", "price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("EDIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("OPEN CART");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addGap(27, 27, 27)
                        .addComponent(jButton4)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       JTextField nameField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField sizeField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(6, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Brand:"));
        panel.add(brandField);
        panel.add(new JLabel("Size:"));
        panel.add(sizeField);
        panel.add(new JLabel("Type:"));
        panel.add(typeField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Enter Product Details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                addProduct(
                    nameField.getText(),
                    brandField.getText(),
                    sizeField.getText(),
                    typeField.getText(),
                    Integer.parseInt(quantityField.getText()),
                    Double.parseDouble(priceField.getText())
                );
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter correct values.");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        deleteProduct();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        editProduct();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                                                
    cart cartFrame = new cart();  // Create an instance of cart.java
    cartFrame.setVisible(true);   // Show the cart JFrame


    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    
   
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(() -> new ProductFrame().setVisible(true));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
