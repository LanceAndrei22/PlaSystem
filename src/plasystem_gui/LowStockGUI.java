package plasystem_gui;

import plasystem_functions.DataHandling;
import plasystem_functions.ProductData;
import javax.swing.table.*;
import java.util.LinkedList;

public class LowStockGUI extends javax.swing.JFrame{
    
    private DataHandling dataHandler;
    private LinkedList<ProductData> productList;
    private DataHandling dataHandling;
    private String filePath;
    
    public LowStockGUI(){
        initComponents();
        setLocationRelativeTo(null);
    }

    public LowStockGUI(LinkedList<ProductData> productList, String path){
        this.filePath = path;
        this.dataHandler = new DataHandling(filePath);
        this.productList = dataHandler.getList(); // Load full product list;

        initComponents(); // always initialize GUI components
        setLocationRelativeTo(null); // center the window

        DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
        model.setRowCount(0); // clear table just in case

        refreshLowStockTable();
    }
    
    private void refreshLowStockTable() {
    DefaultTableModel model = (DefaultTableModel) lowstockTable.getModel();
    model.setRowCount(0); // Clear table
    for (ProductData product : this.productList) {
        if (product.getProductQuantity() < product.getProductRestockValue()) {
            model.addRow(new Object[]{
                    product.getProductID(),
                    product.getProductName(),
                    product.getProductQuantity(),
                    product.getProductRestockValue(),
            });
        }
    }
}

            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lowstockScrollPane = new javax.swing.JScrollPane();
        lowstockTable = new javax.swing.JTable();
        Design = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lowstockTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Product ID", "Name", "Quantity", "Restock Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        lowstockScrollPane.setViewportView(lowstockTable);

        Design.setBackground(new java.awt.Color(51, 51, 51));
        Design.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Design.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/lowstocktitle.png"))); // NOI18N
        Design.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lowstockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Design, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(lowstockScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(Design, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 278, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Design;
    private javax.swing.JScrollPane lowstockScrollPane;
    private javax.swing.JTable lowstockTable;
    // End of variables declaration//GEN-END:variables
}
