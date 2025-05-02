package plasystem_functions;

import plasystem_gui.MainProgramGUI;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Utility class for applying role-based access control in the PlaSystem application.
 * Enables or disables buttons and the product table in the MainProgramGUI based on the
 * user's role, using reflection to access GUI components.
 */
public class RoleBasedAccessControl {
    
    /**
     * Applies role-based permissions by enabling or disabling buttons and the product table
     * in the MainProgramGUI based on the user's role. Uses reflection to access GUI components.
     * Disables all components as a fallback if the role is null, unknown, or if reflection fails.
     *
     * @param gui  The MainProgramGUI instance containing the buttons and table. Must not be null.
     * @param role The role of the logged-in user (e.g., "admin", "cashier", "restocker",
     *             "store_manager", "inventory_manager"). May be null.
     * @throws NullPointerException if gui is null.
     * @throws IllegalStateException if reflection fails to access required fields due to
     *                               NoSuchFieldException or IllegalAccessException, with a
     *                               fallback to disable all components.
     */
    public static void applyRolePermissions(MainProgramGUI gui, String role) {
        try {
            // Get references to all buttons and the product table using reflection
            JButton addProductBtn = (JButton) gui.getClass().getDeclaredField("addProductBtn").get(gui);
            JButton editProductBtn = (JButton) gui.getClass().getDeclaredField("editProductBtn").get(gui);
            JButton deleteProductBtn = (JButton) gui.getClass().getDeclaredField("deleteProductBtn").get(gui);
            JButton transactionBtn = (JButton) gui.getClass().getDeclaredField("transactionBtn").get(gui);
            JButton restockProductBtn = (JButton) gui.getClass().getDeclaredField("restockProductBtn").get(gui);
            JButton lowStockBtn = (JButton) gui.getClass().getDeclaredField("lowStockBtn").get(gui);
            JButton userAccountsBtn = (JButton) gui.getClass().getDeclaredField("userAccountsBtn").get(gui);
            JButton exportInventoryBtn = (JButton) gui.getClass().getDeclaredField("exportInventoryBtn").get(gui);
            JButton transactHistoryBtn = (JButton) gui.getClass().getDeclaredField("transactHistoryBtn").get(gui);
            JButton restockHistoryBtn = (JButton) gui.getClass().getDeclaredField("restockHistoryBtn").get(gui);
            JTable productTable = (JTable) gui.getClass().getDeclaredField("productTbl").get(gui);

            // Enable all buttons and the table by default (admin role)
            addProductBtn.setEnabled(true);
            editProductBtn.setEnabled(true);
            deleteProductBtn.setEnabled(true);
            transactionBtn.setEnabled(true);
            restockProductBtn.setEnabled(true);
            lowStockBtn.setEnabled(true);
            userAccountsBtn.setEnabled(true);
            exportInventoryBtn.setEnabled(true);
            transactHistoryBtn.setEnabled(true);
            restockHistoryBtn.setEnabled(true);
            productTable.setEnabled(true);

            // Disable buttons and table based on role
            if (role == null) {
                // Fallback: disable all buttons and the table if role is null
                addProductBtn.setEnabled(false);
                editProductBtn.setEnabled(false);
                deleteProductBtn.setEnabled(false);
                transactionBtn.setEnabled(false);
                restockProductBtn.setEnabled(false);
                lowStockBtn.setEnabled(false);
                userAccountsBtn.setEnabled(false);
                exportInventoryBtn.setEnabled(false);
                transactHistoryBtn.setEnabled(false);
                restockHistoryBtn.setEnabled(false);
                productTable.setEnabled(false);
            } else {
                switch (role) {
                    case "restocker" -> {
                        addProductBtn.setEnabled(false);
                        editProductBtn.setEnabled(false);
                        deleteProductBtn.setEnabled(false);
                        transactionBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        exportInventoryBtn.setEnabled(false);
                        transactHistoryBtn.setEnabled(false);
                    }
                    case "cashier" -> {
                        addProductBtn.setEnabled(false);
                        editProductBtn.setEnabled(false);
                        deleteProductBtn.setEnabled(false);
                        restockProductBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        exportInventoryBtn.setEnabled(false);
                        restockHistoryBtn.setEnabled(false);
                    }
                    case "store_manager" -> userAccountsBtn.setEnabled(false);
                    case "inventory_manager" -> {
                        transactionBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        transactHistoryBtn.setEnabled(false);
                    }
                    case "admin" -> {
                    }
                    default -> {
                        // Unknown role: disable all buttons and the table
                        addProductBtn.setEnabled(false);
                        editProductBtn.setEnabled(false);
                        deleteProductBtn.setEnabled(false);
                        transactionBtn.setEnabled(false);
                        restockProductBtn.setEnabled(false);
                        lowStockBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        exportInventoryBtn.setEnabled(false);
                        transactHistoryBtn.setEnabled(false);
                        restockHistoryBtn.setEnabled(false);
                        productTable.setEnabled(false);
                    }
                }
                // All buttons and table remain enabled
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Log error and disable all buttons and the table as a fallback
            System.err.println("Error accessing components for role-based access control: " + e.getMessage());
            try {
                JButton addBtn = (JButton) gui.getClass().getDeclaredField("addBtn").get(gui);
                JButton editBtn = (JButton) gui.getClass().getDeclaredField("editBtn").get(gui);
                JButton deleteBtn = (JButton) gui.getClass().getDeclaredField("deleteBtn").get(gui);
                JButton transactBtn = (JButton) gui.getClass().getDeclaredField("transactBtn").get(gui);
                JButton restockBtn = (JButton) gui.getClass().getDeclaredField("restockBtn").get(gui);
                JButton lowstockBtn = (JButton) gui.getClass().getDeclaredField("lowstockBtn").get(gui);
                JButton userAccountsBtn = (JButton) gui.getClass().getDeclaredField("userAccountsBtn").get(gui);
                JButton printInventoryBtn = (JButton) gui.getClass().getDeclaredField("printInventoryBtn").get(gui);
                JButton transactHistoryBtn = (JButton) gui.getClass().getDeclaredField("transactHistoryBtn").get(gui);
                JButton restockHistoryBtn = (JButton) gui.getClass().getDeclaredField("restockHistoryBtn").get(gui);
                JTable productTable = (JTable) gui.getClass().getDeclaredField("plasystemTbl").get(gui);

                addBtn.setEnabled(false);
                editBtn.setEnabled(false);
                deleteBtn.setEnabled(false);
                transactBtn.setEnabled(false);
                restockBtn.setEnabled(false);
                lowstockBtn.setEnabled(false);
                userAccountsBtn.setEnabled(false);
                printInventoryBtn.setEnabled(false);
                transactHistoryBtn.setEnabled(false);
                restockHistoryBtn.setEnabled(false);
                productTable.setEnabled(false);
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException ex) {
                System.err.println("Failed to disable components: " + ex.getMessage());
            }
        }
    }
}