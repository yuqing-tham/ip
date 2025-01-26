package universe;

/**
 * MissingIndexException class inherits from UniverseException.
 * It passes on the error message that the check, uncheck and remove commands did not have a chore number.
 */
public class MissingIndexException extends UniverseException {
    public MissingIndexException() {
        super("Please provide a chore number!\n");
    }
}
