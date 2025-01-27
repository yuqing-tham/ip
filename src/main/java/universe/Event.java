package universe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from Chore and overrides the toString() method to print further details.
 */
public class Event extends Chore {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String choreDescription, LocalDateTime start, LocalDateTime end) {
        super(choreDescription);
        this.start = start;
        this.end = end;
    }

    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    @Override
    public LocalDateTime getDateTime() {
        return start;
    }

    public String reformatStart() throws DateTimeParseException {
        return start.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    public String reformatEnd() throws DateTimeParseException {
        return end.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.reformatStart() + " to: " + this.reformatEnd() + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.reformatStart() + " to " + this.reformatEnd();
    }
}
