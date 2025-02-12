package universe.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class is chiefly in charge of splitting the user's response into useful parts:
 * description of the chore, the deadline, the start and end times of events
 * @author yuqing-tham
 */
public class Parser {
    private String response;

    /**
     * Constructor for the Parser class.
     * @param response String response by user
     */
    public Parser(String response) {
        this.response = response;
    }

    /**
     * Splits the response to obtain the part containing the first word in the user input.
     * @return the first word in input
     */
    public String getCommandFirstWord() {
        String[] parts = response.split(" ", 2);
        return parts[0].trim();
    }

    /**
     * Splits the response to obtain the part containing the Chore descriptions.
     * @return the choreDescription for ToDo class
     */
    public String getDescription() {
        String[] parts = response.split(" ", 2);
        return parts[1].trim();
    }

    /**
     * Splits the response by the word "by".
     * @return a String array containing the chore description and the deadline date
     */
    public String[] getDeadlineDetails() {
        String temp = this.getDescription();
        String[] parts = temp.split("by", 2);
        return parts;
    }

    /**
     * Getter for the deadline description.
     * Calls getDeadlineDetails() and extracts the necessary parts from the array.
     * @return the choreDescription for Deadline class
     */
    public String getDeadlineDescription() {
        String description = this.getDeadlineDetails()[0].trim();
        return description;
    }

    /**
     * Getter for the deadline date.
     * Calls getDeadlineDetails() and extracts the necessary parts from the array.
     * @return the date for Deadline class
     */
    public LocalDateTime getDate() throws DateTimeParseException {
        String date = this.getDeadlineDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(date, formatter);
    }

    /**
     * Splits the response by the word "from" and "to".
     * @return a String array containing the chore description and the deadline date
     */
    public String[] getEventDetails() {
        String temp = this.getDescription();
        String[] mid = temp.split("from", 2);
        String[] furtherSplit = mid[1].split("to");
        String[] parts = new String[]{mid[0], furtherSplit[0], furtherSplit[1]};
        return parts;
    }

    /**
     * Getter for the event description.
     * Calls getEventDetails() and extracts the necessary parts from the array.
     * @return the choreDescription for Event class
     */
    public String getEventDescription() {
        String[] temp = this.getEventDetails();
        return temp[0].trim();
    }

    /**
     * Getter for the event start time.
     * Calls getEventDetails() and extracts the necessary parts from the array.
     * @return the start time for Event class
     */
    public LocalDateTime getStartTime() throws DateTimeParseException {
        String start = this.getEventDetails()[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(start, formatter);
    }

    /**
     * Getter for the event end time.
     * Calls getEventDetails() and extracts the necessary parts from the array.
     * @return the end time for Event class
     */
    public LocalDateTime getEndTime() throws DateTimeParseException {
        String end = this.getEventDetails()[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
        return LocalDateTime.parse(end, formatter);
    }

    /**
     * Splits the response by the space for remove, check and uncheck commands.
     * @return int representing the choreNumber
     */
    public int getChoreNumber() {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        assert choreNumber > 0;
        return choreNumber;
    }

    /**
     * Splits the response by the space for filter commands.
     * @return a String array containing the command and the date
     */
    public String[] splitFilter() {
        String[] parts = response.split(" ", 2);
        return parts;
    }

    /**
     * Getter for the date String by calling splitFilter().
     * @return String date to be printed
     */
    public String getFilterDateString() {
        return this.splitFilter()[1].trim();
    }

    /**
     * Change the String to a LocalDateTime instance.
     * @return LocalDateTime instance to be passed into the filter() method in Checklist class
     */
    public LocalDateTime getFilterDate() {
        String date = this.getFilterDateString();
        LocalDateTime dateToFilter = LocalDateTime.parse(date + " 00:00",
                DateTimeFormatter.ofPattern("d MMM yyy HH:mm"));
        return dateToFilter;
    }

    /**
     * Splits the response by the space for find commands and extract the keyword part.
     * @return a String containing the keyword to be passed on to Checklist
     */
    public String getKeyword() {
        String[] parts = response.split(" ", 2);
        return parts[1];
    }
}
