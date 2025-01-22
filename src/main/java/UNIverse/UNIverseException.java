package UNIverse;

/**
 * UNIverseException inherits from Exception class.
 * It passes on the error message to the Exception class, so that it can call e.getMessage().
 */
public class UNIverseException extends Exception {
    public UNIverseException(String message) {
        super(message);
    }
}