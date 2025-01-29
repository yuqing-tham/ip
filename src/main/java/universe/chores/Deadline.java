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
    private LocalDateTime date;

    /**
     * Constructor for Deadline class. Takes in the choreDescription
     * and an additional field date for the deadline date.
     * @param choreDescription String description of the Deadline Chore
     * @param date deadline of the Deadline Chore as an instance of LocalDateTime
     */
    public Deadline(String choreDescription, LocalDateTime date) {
        super(choreDescription);
        this.date = date;
    }

    /**
     * Getter to return the date and time details.
     * Provides a concrete implementation to parent's abstract method.
     * @return date and time details
     */
    @Override
    public LocalDateTime getDateTime() {
        return date;
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
     * @throws DateTimeParseException to catch errors in reformatting
     * @return String of reformatted date to be printed
     */
    public String reformatDate() throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
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
