package universe.exceptions;

/**
 * MissingTimeException class inherits from UniverseException.
 * It passes on the error message that a deadline is lacking a date/time.
 * @author yuqing-tham
 */
public class MissingTimeException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingTimeException() {
        super("A deadline chore must have a date/time!");
    }
}
