package universe;

/**
 * EmptyResponseException class inherits from UniverseException.
 * It passes on the error message that the Universe is still waiting for the user's response.
 */
public class EmptyResponseException extends UniverseException {
    public EmptyResponseException() {
        super("Still waiting for your response!\n");
    }
}
