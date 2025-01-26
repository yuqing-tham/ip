package universe;

/**
 * MissingStartAndEndException class inherits from UniverseException.
 * It passes on the error message that the event is lacking either a start or end time or both.
 */
public class MissingStartAndEndException extends UniverseException {
    public MissingStartAndEndException() {
        super("An event must have a start and end time!\n");
    }
}
