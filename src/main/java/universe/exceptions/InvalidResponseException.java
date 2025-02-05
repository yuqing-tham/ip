package universe.exceptions;

/**
 * InvalidResponseException class inherits from UniverseException.
 * It passes on the error message that the response is invalid.
 * @author yuqing-tham
 */
public class InvalidResponseException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public InvalidResponseException() {
        super("Sorry, this word doesn't exist in the Universe library yet.\n"
                + "Check back again in a million light years or reenter your command.");
    }
}
