package plasystem_functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Utility class for converting month names to numeric format.
 */
public class MonthConverter {

    /**
     * Converts a month name to its two-digit numeric equivalent.
     * For example, "April" or "april" returns "04". If the input is not a valid month name,
     * the original input is returned unchanged.
     *
     * @param monthName The month name to convert (case-insensitive).
     * @return A two-digit numeric string (e.g., "04") or the original input if invalid.
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