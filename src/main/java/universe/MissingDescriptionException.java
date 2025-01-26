package universe;

/**
 * MissingDescriptionException class inherits from UniverseException.
 * It passes on the error message that the chore is lacking a description.
 */
public class MissingDescriptionException extends UniverseException {
    public MissingDescriptionException() {
        super("A chore must have a description!");
    }
}
