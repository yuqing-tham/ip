package UNIverse;

/**
 * MissingListItemException class inherits from UNIverseException.
 * It passes on the error message that the chore does not exist.
 */

public class MissingListItemException extends UNIverseException {
    public MissingListItemException() {
        super("This chore is not in the list :(");
    }
}
