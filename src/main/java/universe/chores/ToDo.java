package universe.chores;

import java.time.LocalDateTime;

/**
 * This class inherits from Chore and overrides the toString()
 * and toFileString() methods to print further details.
 * @author yuqing-tham
 */
public class ToDo extends Chore {

    /**
     * Constructor for ToDo class. Takes in the choreDescription.
     * @param choreDescription String description of the chore
     */
    public ToDo(String choreDescription) {
        super(choreDescription);
    }

    /**
     * Getter to return the date and time details.
     * Provides a concrete implementation to parent's abstract method.
     * @return null since ToDo has no date and time details
     */
    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to show ToDo successfully added
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to be written into the Checklist file
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }
}
