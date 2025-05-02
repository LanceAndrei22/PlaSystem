package plasystem_functions;

/**
 * Utility class for validating numeric input strings in the PlaSystem application.
 * Provides methods to check if a string represents a valid non-negative integer or
 * a non-negative double with at most two decimal places.
 */
public class ErrorValueHandling {

    /**
     * Checks if the input string represents a valid non-negative number, either an integer
     * (e.g., "0", "123") or a double with at most two decimal places (e.g., "12.34", "12.3", "0.00", "0.0").
     * Invalid inputs, such as numbers with three or more decimal places (e.g., "12.345"), non-numeric strings
     * (e.g., "letters"), malformed numbers (e.g., "12.34.56"), or negative numbers (e.g., "-12.34"), are rejected.
     *
     * @param input The string to be validated.
     * @return {@code true} if the input is a valid non-negative number with at most two decimal places,
     *         {@code false} if the input is null, empty, or invalid.
     */
    public boolean isDouble(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        // Regex for non-negative numbers: integer or decimal with 0-2 digits after decimal
        String doubleRegex = "^\\d+(\\.\\d{0,2})?$";
        
        // Check if input matches the regex
        if (!input.matches(doubleRegex)) {
            return false;
        }
        
        try {
            // Parse to ensure it's a valid double and non-negative
            double value = Double.parseDouble(input);
            return value >= 0; // Enforce PROD_PRICE >= 0
        } catch (NumberFormatException e) {
            return false; // Shouldn't occur due to regex, but included for robustness
        }
    }

    /**
     * Checks if the input string represents a valid non-negative integer (e.g., "0", "123").
     * Invalid inputs, such as decimal numbers (e.g., "12.34"), non-numeric strings (e.g., "letters"),
     * mixed strings (e.g., "12a"), or negative numbers (e.g., "-123"), are rejected.
     *
     * @param input The string to be validated.
     * @return {@code true} if the input is a valid non-negative integer,
     *         {@code false} if the input is null, empty, or invalid.
     */
    public boolean isInteger(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }

        // Regex for non-negative integers: one or more digits, no decimal point
        String integerRegex = "^\\d+$";

        // Check if input matches the regex
        if (!input.matches(integerRegex)) {
            return false;
        }

        try {
            // Parse to ensure it's a valid integer and non-negative
            int value = Integer.parseInt(input);
            return value >= 0; // Enforce non-negative
        } catch (NumberFormatException e) {
            return false; // Shouldn't occur due to regex, but included for robustness
        }
    }
}