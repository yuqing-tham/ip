package universe.exceptions;

/**
 * MissingDescriptionException class inherits from UniverseException.
 * It passes on the error message that the chore is lacking a description.
 * @author yuqing-tham
 */
public class MissingDescriptionException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingDescriptionException() {
        super("A chore must have a description!");
    }
}
