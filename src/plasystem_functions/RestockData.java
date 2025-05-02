package plasystem_functions;

import java.util.List;

/**
 * Represents a single restocking event in the PlaSystem database, mapping to the Restock table.
 * Each event includes a unique ID, date and time details, and a list of associated restock items.
 * Provides getters and setters for accessing and modifying the event's attributes.
 */
public class RestockData {
    /** The unique ID of the restocking event (RESTOCK_ID, INTEGER PRIMARY KEY AUTOINCREMENT). */
    private int restockId;
    
    /** The year of the restocking event (RESTOCK_DATE_YEAR, TEXT NOT NULL). */
    private String restockDateYear;
    
    /** The month of the restocking event (RESTOCK_DATE_MONTH, TEXT NOT NULL). */
    private String restockDateMonth;
    
    /** The day of the restocking event (RESTOCK_DATE_DAY, TEXT NOT NULL). */
    private String restockDateDay;
    
    /** The time of the restocking event (RESTOCK_DATE_TIME, TEXT NOT NULL). */
    private String restockDateTime;
    
    /** The list of items restocked in this event (linked to RestockItems table). */
    private List<RestockItemData> restockItems;

    /**
     * Default constructor. Initializes a RestockData object with default values
     * (0 for restockId, null for String fields, and null for restockItems).
     */
    public RestockData() {
    }

    /**
     * Constructs a RestockData object with the specified values, initializing all attributes.
     *
     * @param restockId        The unique ID of the restocking event (auto-incremented by the database).
     * @param restockDateYear  The year of the restocking event. Should not be null to match database constraints.
     * @param restockDateMonth The month of the restocking event. Should not be null to match database constraints.
     * @param restockDateDay   The day of the restocking event. Should not be null to match database constraints.
     * @param restockDateTime  The time of the restocking event. Should not be null to match database constraints.
     * @param restockItems     The list of restock items associated with this event. May be null or empty.
     */
    public RestockData(int restockId, String restockDateYear, String restockDateMonth, String restockDateDay, String restockDateTime, List<RestockItemData> restockItems) {
        this.restockId = restockId;
        this.restockDateYear = restockDateYear;
        this.restockDateMonth = restockDateMonth;
        this.restockDateDay = restockDateDay;
        this.restockDateTime = restockDateTime;
        this.restockItems = restockItems;
    }

    /**
     * Gets the unique ID of the restocking event.
     *
     * @return The restock ID (RESTOCK_ID).
     */
    public int getRestockId() {
        return restockId;
    }

    /**
     * Gets the year of the restocking event.
     *
     * @return The restock date year (RESTOCK_DATE_YEAR).
     */
    public String getRestockDateYear() {
        return restockDateYear;
    }

    /**
     * Sets the year of the restocking event.
     *
     * @param restockDateYear The new restock date year. Should not be null to match database constraints.
     */
    public void setRestockDateYear(String restockDateYear) {
        this.restockDateYear = restockDateYear;
    }
    
    /**
     * Gets the month of the restocking event.
     *
     * @return The restock date month (RESTOCK_DATE_MONTH).
     */
    public String getRestockDateMonth() {
        return restockDateMonth;
    }

    /**
     * Sets the month of the restocking event.
     *
     * @param restockDateMonth The new restock date month. Should not be null to match database constraints.
     */
    public void setRestockDateMonth(String restockDateMonth) {
        this.restockDateMonth = restockDateMonth;
    }
    
    /**
     * Gets the day of the restocking event.
     *
     * @return The restock date day (RESTOCK_DATE_DAY).
     */
    public String getRestockDateDay() {
        return restockDateDay;
    }

    /**
     * Sets the day of the restocking event.
     *
     * @param restockDateDay The new restock date day. Should not be null to match database constraints.
     */
    public void setRestockDateDay(String restockDateDay) {
        this.restockDateDay = restockDateDay;
    }
    
    /**
     * Gets the time of the restocking event.
     *
     * @return The restock date time (RESTOCK_DATE_TIME).
     */
    public String getRestockDateTime() {
        return restockDateTime;
    }
    
    /**
     * Sets the time of the restocking event.
     *
     * @param restockDateTime The new restock date time. Should not be null to match database constraints.
     */
    public void setRestockDateTime(String restockDateTime) {
        this.restockDateTime = restockDateTime;
    }
    
    /**
     * Gets the list of restock items associated with this restocking event.
     *
     * @return The list of RestockItemData objects. May be null or empty.
     */
    public List<RestockItemData> getRestockItems() {
        return restockItems;
    }

    /**
     * Sets the list of restock items associated with this restocking event.
     *
     * @param restockItems The new list of RestockItemData objects. May be null or empty.
     */
    public void setRestockItems(List<RestockItemData> restockItems) {
        this.restockItems = restockItems;
    }
}