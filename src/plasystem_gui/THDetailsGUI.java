package plasystem_gui;

import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;

/**
 * A graphical user interface (GUI) window for displaying the details of a transaction.
 * This class provides a table showing the items included in a specific transaction, with formatted price display.
 */
public class THDetailsGUI extends JFrame {
    /** The TransactionData object containing the transaction details to be displayed. */
    private TransactionData transaction;
    
    /**
     * Default constructor that initializes the THDetailsGUI.
     * Centers the window and sets up the form components.
     */
    public THDetailsGUI() {
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Constructor that initializes the THDetailsGUI with transaction data.
     * Sets up the form components, centers the window, and populates the table with transaction items.
     *
     * @param transaction The TransactionData object containing the transaction items to display
     */
    public THDetailsGUI(TransactionData transaction) {
        // Assign the transaction data
        this.transaction = transaction;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Apply price formatting to the table
        new PriceTableRenderer(thDetailstbl);
        // Populate the table with transaction items
        updateTable();
    }
    
    /**
     * Updates the table with the items from the transaction data.
     */
    private void updateTable() {
        // Get the table model
        DefaultTableModel thTblModel = (DefaultTableModel) thDetailstbl.getModel();
        // Clear existing rows
        thTblModel.setRowCount(0);

        // Iterate through the transaction items
        for (TransactionItemData thItem : transaction.getTransactionItems()) {
            // Add each item as a new row in the table
            thTblModel.addRow(new Object[]{
                thItem.getTI_productName(),
                thItem.getTI_productBrand(),
                thItem.getTI_productSize(),
                thItem.getTI_productType(),
                thItem.getTI_buyQuantity(),
                thItem.getTI_unitPrice(),
                thItem.getTI_totalPrice()
            });
        }
    }
    
    /**
     * Custom renderer to format and align price columns in the table.
     */
    private static class PriceTableRenderer {
        /** The JTable to which the renderer is applied. */
        private final JTable table;

        /**
         * Constructor that initializes the renderer for a specific table.
         *
         * @param table The JTable to apply price formatting to
         */
        public PriceTableRenderer(JTable table) {
            // Assign the table
            this.table = table;
            // Apply formatting and alignment to price columns
            applyPriceFormattingAndAlignment();
        }

        /**
         * Applies decimal formatting and right alignment to the Unit Price and Total Price columns.
         */
        private void applyPriceFormattingAndAlignment() {
            // Create a renderer for decimal formatting
            DecimalFormatRenderer renderer = new DecimalFormatRenderer();
            // Set right alignment for the renderer
            renderer.setHorizontalAlignment(SwingConstants.RIGHT);
            // Get the table's column model
            TableColumnModel columnModel = table.getColumnModel();
            // Apply the renderer to Unit Price (index 5)
            columnModel.getColumn(5).setCellRenderer(renderer);
            // Apply the renderer to Total Price (index 6)
            columnModel.getColumn(6).setCellRenderer(renderer);
        }
    }

    /**
     * Inner renderer to format double values to two decimal places.
     */
    private static class DecimalFormatRenderer extends DefaultTableCellRenderer {
        /** The DecimalFormat instance for formatting numbers to two decimal places. */
        private final DecimalFormat formatter = new DecimalFormat("#0.00");

        /**
         * Formats the cell value, applying two-decimal-place formatting for numeric values.
         *
         * @param table The JTable being rendered
         * @param value The value to render
         * @param isSelected Whether the cell is selected
         * @param hasFocus Whether the cell has focus
         * @param row The row index of the cell
         * @param column The column index of the cell
         * @return The rendered component
         */
        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                               boolean isSelected, boolean hasFocus,
                                                               int row, int column) {
            // Check if the value is a number
            if (value instanceof Number number) {
                // Format the number to two decimal places
                value = formatter.format(number.doubleValue());
            }
            // Call the superclass method to render the cell
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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

        thDetailsScrollPane = new javax.swing.JScrollPane();
        thDetailstbl = new javax.swing.JTable();
        design1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        thDetailstbl.setAutoCreateRowSorter(true);
        thDetailstbl.getTableHeader().setReorderingAllowed(false);
        thDetailstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Brand", "Size", "Type", "Quantity", "Unit Price", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        thDetailsScrollPane.setViewportView(thDetailstbl);

        design1.setBackground(new java.awt.Color(255, 102, 102));
        design1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        design1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/thdetails_title.png"))); // NOI18N
        design1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(thDetailsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(design1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(thDetailsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(design1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, Short.MAX_VALUE)
                    .addGap(252, 252, 252)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel design1;
    private javax.swing.JScrollPane thDetailsScrollPane;
    private javax.swing.JTable thDetailstbl;
    // End of variables declaration//GEN-END:variables
}
