package universe;

/**
 * UniverseException inherits from Exception class.
 * It passes on the error message to the Exception class, so that it can call e.getMessage().
 */
public class UniverseException extends Exception {
    public UniverseException(String message) {
        super(message);
    }
}