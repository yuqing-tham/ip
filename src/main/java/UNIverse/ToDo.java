package UNIverse;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class ToDo extends Chore {

    public ToDo(String choreDescription) {
        super(choreDescription);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
