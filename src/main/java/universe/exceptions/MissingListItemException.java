package universe.exceptions;

/**
 * MissingListItemException class inherits from UniverseException.
 * It passes on the error message that the chore does not exist.
 * @author yuqing-tham
 */
public class MissingListItemException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingListItemException() {
        super("This chore is not in the list :(");
    }
}
