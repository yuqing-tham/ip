package universe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class Deadline extends Chore {
    private LocalDateTime date;

    public Deadline(String choreDescription, LocalDateTime date) {
        super(choreDescription);
        this.date = date;
    }

    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    @Override
    public LocalDateTime getDateTime() {
        return date;
    }

    public String reformatDate() throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.reformatDate() + ")";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + this.reformatDate();
    }
}
