package UNIverse;

/**
 * MissingStartAndEndException class inherits from UNIverseException.
 * It passes on the error message that the event is lacking either a start or end time or both.
 */
public class MissingStartAndEndException extends UNIverseException {
    public MissingStartAndEndException() {
        super("An event must have a start and end time!");
    }
}
