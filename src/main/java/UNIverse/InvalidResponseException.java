package UNIverse;

/**
 * InvalidResponseException class inherits from UNIverseException.
 * It passes on the error message that the response is invalid.
 */
public class InvalidResponseException extends UNIverseException {
    public InvalidResponseException() {
        super("Sorry, this word doesn't exist in the UNIverse library yet.\n" +
                "Check back again in a million light years or reenter your command.");
    }
}
