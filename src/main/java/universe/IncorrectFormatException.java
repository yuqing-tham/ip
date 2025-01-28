package universe;

/**
 * IncorrectFormatException class inherits from UniverseException.
 * It passes on the error message that there is an error reading from the checklist file.
 */
public class IncorrectFormatException extends UniverseException {
    public IncorrectFormatException() {
        super("Error in reading chore from the provided checklist.\n");
    }
}
