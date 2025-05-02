package plasystem_gui;

import plasystem_functions.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.DecimalFormat;

/**
 * A graphical user interface (GUI) window for displaying the details of a restock operation.
 * This class provides a table showing the items included in a specific restock, with formatted price display.
 */
public class RHDetailsGUI extends JFrame {
    /** The RestockData object containing the restock details to be displayed. */
    private final RestockData restock;

    /**
     * Default constructor that initializes the RHDetailsGUI.
     * Centers the window and sets up the form components.
     */
    public RHDetailsGUI() {
        // Initialize with null restock data
        this.restock = null;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
    }

    /**
     * Constructor that initializes the RHDetailsGUI with restock data.
     * Sets up the form components, centers the window, and populates the table with restock items.
     *
     * @param restock The RestockData object containing the restock items to display
     */
    public RHDetailsGUI(RestockData restock) {
        // Assign the restock data
        this.restock = restock;
        // Initialize the GUI components defined in the form
        initComponents();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Apply price formatting to the table
        new PriceTableRenderer(rhDetailsTbl);
        // Populate the table with restock items
        updateTable();
    }
    
    /**
     * Updates the table with the items from the restock data.
     */
    private void updateTable() {
        // Check if restock data or items are null
        if (restock == null || restock.getRestockItems() == null) {
            // Exit if no valid data is available
            return;
        }

        // Get the table model
        DefaultTableModel rhTblModel = (DefaultTableModel) rhDetailsTbl.getModel();
        // Clear existing rows
        rhTblModel.setRowCount(0);

        // Iterate through the restock items
        for (RestockItemData rhItem : restock.getRestockItems()) {
            // Add each item as a new row in the table
            rhTblModel.addRow(new Object[]{
                rhItem.getRI_productName(),
                rhItem.getRI_productBrand(),
                rhItem.getRI_productSize(),
                rhItem.getRI_productType(),
                rhItem.getRI_productPrice(),
                rhItem.getRI_restockedQuantity()
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
            // Apply formatting and alignment to the price column
            applyPriceFormattingAndAlignment();
        }

        /**
         * Applies decimal formatting and right alignment to the price column.
         */
        private void applyPriceFormattingAndAlignment() {
            // Create a renderer for decimal formatting
            DecimalFormatRenderer renderer = new DecimalFormatRenderer();
            // Set right alignment for the renderer
            renderer.setHorizontalAlignment(SwingConstants.RIGHT);
            // Get the table's column model
            TableColumnModel columnModel = table.getColumnModel();
            // Apply the renderer to the Price column (index 4)
            columnModel.getColumn(4).setCellRenderer(renderer);
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

        rhDetailsScrollPane = new javax.swing.JScrollPane();
        rhDetailsTbl = new javax.swing.JTable();
        design1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        rhDetailsTbl.setAutoCreateRowSorter(true);
        rhDetailsTbl.getTableHeader().setReorderingAllowed(false);
        rhDetailsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Brand", "Size", "Type", "Price", "Restocked Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rhDetailsScrollPane.setViewportView(rhDetailsTbl);

        design1.setBackground(new java.awt.Color(153, 204, 0));
        design1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        design1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/plasystem_resources/rhdetails_title.png"))); // NOI18N
        design1.setToolTipText("");
        design1.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(rhDetailsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(design1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(rhDetailsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(design1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE)
                    .addGap(274, 274, 274)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel design1;
    private javax.swing.JScrollPane rhDetailsScrollPane;
    private javax.swing.JTable rhDetailsTbl;
    // End of variables declaration//GEN-END:variables
}
