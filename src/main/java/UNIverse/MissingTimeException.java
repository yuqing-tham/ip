package UNIverse;

/**
 * MissingTimeException class inherits from UNIverseException.
 * It passes on the error message that a deadline is lacking a date/time.
 */
public class MissingTimeException extends UNIverseException {
    public MissingTimeException() {
        super("A deadline chore must have a date/time!");
    }
}
