package plasystem_functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Utility class for converting month names to their two-digit numeric equivalents in the PlaSystem application.
 * Supports case-insensitive month name inputs and returns the original input for invalid month names.
 */
public class MonthConverter {

    /**
     * Converts a month name to its two-digit numeric equivalent (e.g., "April" or "april" returns "04").
     * If the input is not a valid month name (e.g., "Invalid"), null, or empty, the original input is returned unchanged.
     * The conversion is case-insensitive and uses English locale for month name validation.
     *
     * @param monthName The month name to convert (case-insensitive). May be null or empty.
     * @return A two-digit numeric string (e.g., "04" for April) if the input is a valid month name,
     *         or the original input if invalid, null, or empty.
     */
    public static String monthNameToNumeric(String monthName) {
        if (monthName == null || monthName.trim().isEmpty()) {
            return monthName;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            sdf.setLenient(false);
            sdf.parse(monthName.trim());
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(monthName.trim()));
            return String.format("%02d", cal.get(Calendar.MONTH) + 1);
        } catch (ParseException e) {
            return monthName;
        }
    }
}