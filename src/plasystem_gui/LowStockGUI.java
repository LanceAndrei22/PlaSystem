package plasystem_gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import plasystem_functions.ProductData;
import plasystem_functions.DataHandling;
import java.util.LinkedList;

public class LowStockGUI extends javax.swing.JFrame {
    
    private LinkedList<ProductData> productList;
    private JTable lowStockTable;
    private JScrollPane scrollPane;
    private DataHandling dataHandling;
    
    public LowStockGUI(LinkedList<ProductData> productList, String path) {
        initComponents(productList, path);
    }
    
    private void initComponents(LinkedList<ProductData> productList, String path) {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Low Stock Items");
        
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, new String[]{
                "Product ID", "Quantity", "Restock Value", "Name"
        });
        
        this.productList = productList;
        lowStockTable = new JTable(model);
        scrollPane = new JScrollPane(lowStockTable);
        this.dataHandling = new DataHandling(path); // Instantiate DataHandling with the file path
        add(scrollPane);
        
        // Populate table with low stock items
        productList = dataHandling.getList();
        for (ProductData product : productList) {
            if (product.getProductQuantity() < product.getProductRestockValue()) {
                model.addRow(new Object[]{
                        product.getProductID(),
                        product.getProductQuantity(),
                        product.getProductRestockValue(),
                        product.getProductName()
                });
            }
        }
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            // Assuming MainProgramGUI is running and has productList
            MainProgramGUI mainGUI = new MainProgramGUI();
            
        });
    }
}
