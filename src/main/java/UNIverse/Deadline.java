package UNIverse;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class Deadline extends Chore {
    private String date;

    public Deadline(String choreDescription, String date) {
        super(choreDescription);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
