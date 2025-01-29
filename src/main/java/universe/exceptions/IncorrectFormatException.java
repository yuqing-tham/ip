package universe.exceptions;

/**
 * IncorrectFormatException class inherits from UniverseException.
 * It passes on the error message that there is an error reading from the checklist file.
 * @author yuqing-tham
 */
public class IncorrectFormatException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public IncorrectFormatException() {
        super("Error in reading chore from the provided checklist.\n");
    }
}
