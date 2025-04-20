package plasystem_functions;

import java.util.List;

/**
 * Represents a single restocking event.
 * Each event can have multiple restocked items.
 */
public class RestockData {
    private int restockId;
    private String restockDateYear;
    private String restockDateMonth;
    private String restockDateDay;
    private String restockDateTime;
    private List<RestockItemData> restockItems;
    
    /**
     * Default constructor.
     */
    public RestockData() {
    
    }

    public RestockData(int restockId, String restockDateYear, String restockDateMonth, String restockDateDay, String restockDateTime, List<RestockItemData> restockItems) {
        this.restockId = restockId;
        this.restockDateYear = restockDateYear;
        this.restockDateMonth = restockDateMonth;
        this.restockDateDay = restockDateDay;
        this.restockDateTime = restockDateTime;
        this.restockItems = restockItems;
    }

    // Getters and setters
    public int getRestockId() {
        return restockId;
    }

    public String getRestockDateYear() {
        return restockDateYear;
    }

    public void setRestockDateYear(String restockDate) {
        this.restockDateYear = restockDate;
    }
    
    public String getRestockDateMonth() {
        return restockDateMonth;
    }

    public void setRestockDateMonth(String restockDate) {
        this.restockDateMonth = restockDate;
    }
    
    public String getRestockDateYearDay() {
        return restockDateDay;
    }

    public void setRestockDateDay(String restockDate) {
        this.restockDateDay = restockDate;
    }
    
    public String getRestockDateTime() {
        return restockDateTime;
    }
    
    public void setRestockDateTime(String restockDate) {
        this.restockDateTime = restockDate;
    }
    
    public List<RestockItemData> getRestockItems() {
        return restockItems;
    }

    public void setRestockItems(List<RestockItemData> restockItems) {
        this.restockItems = restockItems;
    }
}