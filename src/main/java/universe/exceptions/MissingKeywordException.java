package universe.exceptions;

/**
 * MissingKeywordException class inherits from UniverseException.
 * It passes on the error message that the find command is lacking a keyword.
 * @author yuqing-tham
 */
public class MissingKeywordException extends UniverseException {
    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingKeywordException() {
        super("Please provide a keyword.");
    }
}
