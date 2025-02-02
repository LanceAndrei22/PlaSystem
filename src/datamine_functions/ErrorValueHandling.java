package datamine_functions;

/**
 * Handles error checking for numeric inputs.
 */
public class ErrorValueHandling {
    
    /**
     * Checks if the input string can be parsed into a double.
     *
     * @param input The string to be checked.
     * @return {@code true} if the input can be parsed into a double, {@code false} otherwise.
     */
    public boolean isDouble(String input) {
        try {
            // Tries to parse the input string into a double
            double value = Double.parseDouble(input);
            return true; // Input is a valid double
        } catch (NumberFormatException e) {
            return false; // Input is not a valid double
        }
    }
    
    /**
     * Checks if the input string can be parsed into an integer.
     *
     * @param input The string to be checked.
     * @return {@code true} if the input can be parsed into an integer, {@code false} otherwise.
     */
    public boolean isInteger(String input) {
        try {
            // Tries to parse the input string into an integer
            int value = Integer.parseInt(input);
            return true; // Input is a valid integer
        } catch (NumberFormatException e) {
            return false; // Input is not a valid integer
        }
    }
}