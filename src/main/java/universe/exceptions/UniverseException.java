package universe.exceptions;

/**
 * UniverseException inherits from Exception class.
 * Parent class for all other Exceptions in the same package.
 * It passes on the error message to the Exception class, so that it can call e.getMessage().
 * @author yuqing-tham
 */
public class UniverseException extends Exception {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public UniverseException(String message) {
        super(message);
    }
}