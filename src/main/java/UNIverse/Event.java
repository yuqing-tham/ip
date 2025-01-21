package UNIverse;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class Event extends Chore {
    private String start;
    private String end;

    public Event(String choreDescription, String start, String end) {
        super(choreDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
