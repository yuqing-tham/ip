package universe.exceptions;

/**
 * EmptyResponseException class inherits from UniverseException.
 * It passes on the error message that the Universe is still waiting for the user's response.
 * @author yuqing-tham
 */
public class EmptyResponseException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public EmptyResponseException() {
        super("Still waiting for your response!");
    }
}
