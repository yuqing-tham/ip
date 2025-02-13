package universe.chores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from Chore and overrides the toString()
 * and toFileString() methods to print further details.
 * @author yuqing-tham
 */
public class Deadline extends Chore {
    private LocalDateTime deadlineDateTime;

    /**
     * Constructor for Deadline class. Takes in the choreDescription
     * and an additional field deadlineDateTime for the deadline deadlineDateTime.
     * @param choreDescription String description of the Deadline Chore
     * @param deadlineDateTime deadline of the Deadline Chore as an instance of LocalDateTime
     */
    public Deadline(String choreDescription, LocalDateTime deadlineDateTime) {
        super(choreDescription);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Getter to return the date and time details.
     * Provides a concrete implementation to parent's abstract method.
     * @return date and time details
     */
    @Override
    public LocalDateTime getDateTime() {
        return deadlineDateTime;
    }

    /**
     * A deadline has a date and time. Overrides the method in parent class to show true.
     * @return <code>true</code> because it is a Chore with time details.
     */
    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    /**
     * Reformat the LocalDateTime to a String using DateTimeFormatter.
     * @return String of reformatted date to be printed
     * @throws DateTimeParseException to catch errors in reformatting
     */
    public String reformatDate() throws DateTimeParseException {
        return deadlineDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to show Deadline successfully added
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.reformatDate() + ")";
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to be written into the Checklist file
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.reformatDate();
    }
}
