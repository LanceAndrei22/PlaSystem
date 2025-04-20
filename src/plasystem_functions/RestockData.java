package plasystem_functions;

import java.util.List;

/**
 * Represents a single restocking event.
 * Each event can have multiple restocked items.
 */
public class RestockData {
    private int restockId;
    private String restockDate;
    private List<RestockItemData> restockItems;
    
    /**
     * Default constructor.
     */
    public RestockData() {
    
    }

    public RestockData(int restockId, String restockDate, List<RestockItemData> restockItems) {
        this.restockId = restockId;
        this.restockDate = restockDate;
        this.restockItems = restockItems;
    }

    // Getters and setters
    public int getRestockId() {
        return restockId;
    }

    public String getRestockDate() {
        return restockDate;
    }

    public void setRestockDate(String restockDate) {
        this.restockDate = restockDate;
    }

    public List<RestockItemData> getRestockItems() {
        return restockItems;
    }

    public void setRestockItems(List<RestockItemData> restockItems) {
        this.restockItems = restockItems;
    }
}