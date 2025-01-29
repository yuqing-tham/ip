package universe.exceptions;

/**
 * MissingStartAndEndException class inherits from UniverseException.
 * It passes on the error message that the event is lacking either a start or end time or both.
 * @author yuqing-tham
 */
public class MissingStartAndEndException extends UniverseException {

    /**
     * Constructor for the Exception class. Passes on the corresponding error message.
     */
    public MissingStartAndEndException() {
        super("An event must have a start and end time!");
    }
}
