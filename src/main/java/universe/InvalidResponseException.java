package universe;

/**
 * InvalidResponseException class inherits from UniverseException.
 * It passes on the error message that the response is invalid.
 */
public class InvalidResponseException extends UniverseException {
    public InvalidResponseException() {
        super("Sorry, this word doesn't exist in the Universe library yet.\n"
                + "Check back again in a million light years or reenter your command.");
    }
}
