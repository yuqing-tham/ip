package universe;

/**
 * MissingListItemException class inherits from UniverseException.
 * It passes on the error message that the chore does not exist.
 */

public class MissingListItemException extends UniverseException {
    public MissingListItemException() {
        super("This chore is not in the list :(");
    }
}
