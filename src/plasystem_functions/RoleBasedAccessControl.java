package plasystem_functions;

import plasystem_gui.MainProgramGUI;
import javax.swing.JButton;
import javax.swing.JTable;

/**
 * Utility class to apply role-based access control by enabling/disabling buttons and the product table in MainProgramGUI.
 */
public class RoleBasedAccessControl {
    
    /**
     * Applies role-based permissions by enabling/disabling buttons and the product table in MainProgramGUI based on the user's role.
     * @param gui The MainProgramGUI instance containing the buttons and table.
     * @param role The role of the logged-in user.
     */
    public static void applyRolePermissions(MainProgramGUI gui, String role) {
        try {
            // Get references to all buttons and the product table using reflection
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

            // Enable all buttons and the table by default (admin role)
            addBtn.setEnabled(true);
            editBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            transactBtn.setEnabled(true);
            restockBtn.setEnabled(true);
            lowstockBtn.setEnabled(true);
            userAccountsBtn.setEnabled(true);
            printInventoryBtn.setEnabled(true);
            transactHistoryBtn.setEnabled(true);
            restockHistoryBtn.setEnabled(true);
            productTable.setEnabled(true);

            // Disable buttons and table based on role
            if (role == null) {
                // Fallback: disable all buttons and the table if role is null
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
            } else {
                switch (role) {
                    case "restocker":
                        addBtn.setEnabled(false);
                        editBtn.setEnabled(false);
                        deleteBtn.setEnabled(false);
                        transactBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        printInventoryBtn.setEnabled(false);
                        transactHistoryBtn.setEnabled(false);
                        break;
                    case "cashier":
                        addBtn.setEnabled(false);
                        editBtn.setEnabled(false);
                        deleteBtn.setEnabled(false);
                        restockBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        printInventoryBtn.setEnabled(false);
                        restockHistoryBtn.setEnabled(false);
                        break;
                    case "store_manager":
                        userAccountsBtn.setEnabled(false);
                        break;
                    case "inventory_manager":
                        transactBtn.setEnabled(false);
                        userAccountsBtn.setEnabled(false);
                        transactHistoryBtn.setEnabled(false);
                        break;
                    case "admin":
                        // All buttons and table remain enabled
                        break;
                    default:
                        // Unknown role: disable all buttons and the table
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
                        break;
                }
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
            } catch (Exception ex) {
                System.err.println("Failed to disable components: " + ex.getMessage());
            }
        }
    }
}