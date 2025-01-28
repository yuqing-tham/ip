package universe.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class is chiefly in charge of splitting the user's response into useful parts:
 * description of the chore, the deadline, the start and end times of events
 */

public class Parser {
    private String response;

    public Parser(String response) {
        this.response = response;
    }

    // for the "todo" chore
    public String getDescription() {
        String[] parts = response.split(" ", 2);
        return parts[1].trim();
    }

    // for the deadline chore
    public String[] getDeadlineDetails() {
        String temp = this.getDescription();
        String[] parts = temp.split("by", 2);
        return parts;
    }

    public String getDeadlineDescription() {
        String description = this.getDeadlineDetails()[0].trim();
        return description;
    }

    public LocalDateTime getDate() throws DateTimeParseException {
        String date = this.getDeadlineDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(date, formatter);
    }

    // for the event chore
    public String[] getEventDetails() {
        String temp = this.getDescription();
        String[] mid = temp.split("from", 2);
        String[] furtherSplit = mid[1].split("to");
        String[] parts = new String[]{ mid[0], furtherSplit[0], furtherSplit[1] };
        return parts;
    }

    public String getEventDescription() {
        String[] temp = this.getEventDetails();
        return temp[0].trim();
    }

    public LocalDateTime getStartTime() {
        String start = this.getEventDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(start, formatter);
    }

    public LocalDateTime getEndTime() {
        String end = this.getEventDetails()[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(end, formatter);
    }
}
