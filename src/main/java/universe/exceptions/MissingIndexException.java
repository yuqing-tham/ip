package universe.exceptions;

/**
 * MissingIndexException class inherits from UniverseException.
 * It passes on the error message that the check, uncheck and remove commands did not have a chore number.
 * @author yuqing-tham
 */
public class MissingIndexException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingIndexException() {
        super("Please provide a chore number!");
    }
}
