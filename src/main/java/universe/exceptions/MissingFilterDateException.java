package universe.exceptions;

/**
 * MissingFilterDateException class inherits from UniverseException.
 * It passes on the error message that the filter is lacking a date or time.
 * @author yuqing-tham
 */
public class MissingFilterDateException extends UniverseException {
    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingFilterDateException() {
        super("Please provide a date to filter by.");
    }
}
