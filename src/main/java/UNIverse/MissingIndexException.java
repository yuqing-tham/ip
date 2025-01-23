package UNIverse;

/**
 * MissingIndexException class inherits from UNIverseException.
 * It passes on the error message that the check, uncheck and remove commands did not have a chore number.
 */
public class MissingIndexException extends UNIverseException {
    public MissingIndexException() {
        super("Please provide a chore number!");
    }
}
