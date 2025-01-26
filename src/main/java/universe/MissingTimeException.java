package universe;

/**
 * MissingTimeException class inherits from UniverseException.
 * It passes on the error message that a deadline is lacking a date/time.
 */
public class MissingTimeException extends UniverseException {
    public MissingTimeException() {
        super("A deadline chore must have a date/time!");
    }
}
