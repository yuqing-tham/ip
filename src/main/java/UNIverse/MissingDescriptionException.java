package UNIverse;

/**
 * MissingDescriptionException class inherits from UNIverseException.
 * It passes on the error message that the chore is lacking a description.
 */
public class MissingDescriptionException extends UNIverseException {
    public MissingDescriptionException() {
        super("A chore must have a description!");
    }
}
