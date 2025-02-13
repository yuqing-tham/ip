package universe.chores;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class inherits from Chore and overrides the toString()
 * and toFileString() methods to print further details.
 * @author yuqing-tham
 */
public class Event extends Chore {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    /**
     * Constructor for Event class. Takes in the choreDescription
     * and additional fields startDateTime and endDateTime for starting and ending times of the event.
     * @param choreDescription String description of the Event Chore
     * @param startDateTime startDateTime time of the Event as an instance of LocalDateTime
     * @param endDateTime endDateTime time of the Event as an instance of LocalDateTime
     */
    public Event(String choreDescription, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(choreDescription);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Getter to return the date and time details.
     * Provides a concrete implementation to parent's abstract method.
     * @return start time details
     */
    @Override
    public LocalDateTime getDateTime() {
        return startDateTime;
    }

    /**
     * An event has a date and time. Overrides the method in parent class to show true.
     * @return <code>true</code> because it is a Chore with time details.
     */
    @Override
    public boolean isChoreWithTime() {
        return true;
    }

    /**
     * Reformat the start LocalDateTime to a String using DateTimeFormatter.
     * @return String of reformatted date to be printed
     * @throws DateTimeParseException to catch errors in reformatting
     */
    public String reformatStartDateTime() throws DateTimeParseException {
        return startDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Reformat the end LocalDateTime to a String using DateTimeFormatter.
     * @return String of reformatted date to be printed
     * @throws DateTimeParseException to catch errors in reformatting
     */
    public String reformatEndDateTime() throws DateTimeParseException {
        return endDateTime.format(DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"));
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to show Event successfully added
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.reformatStartDateTime()
                + " to: " + this.reformatEndDateTime() + ")";
    }

    /**
     * Override the method in parent class Chore to provide further details.
     * @return a formatted String intended to be written into the Checklist file
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + this.reformatStartDateTime()
                + " to " + this.reformatEndDateTime();
    }
}
