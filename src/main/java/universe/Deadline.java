package universe;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class Deadline extends Chore {
    private LocalDate date;

    public Deadline(String choreDescription, LocalDate date) {
        super(choreDescription);
        this.date = date;
    }

    public String reformatDate() throws DateTimeParseException {
        return date.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
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
