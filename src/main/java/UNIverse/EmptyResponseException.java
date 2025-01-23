package UNIverse;

/**
 * EmptyResponseException class inherits from UNIverseException.
 * It passes on the error message that the UNIverse is still waiting for the user's response.
 */
public class EmptyResponseException extends UNIverseException {
    public EmptyResponseException() {
        super("Still waiting for your response!");
    }
}
