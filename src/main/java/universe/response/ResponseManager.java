package universe.response;

import universe.Checklist;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.InvalidResponseException;

import java.time.LocalDateTime;

/**
 * ResponseManager class calls the corresponding functions based on the user inputs.
 * @author yuqing-tham
 */
public class ResponseManager {
    private Checklist list;
    private String response;

    /**
     * Constructor for the ResponseManager class.
     * @param list Checklist maintained by the Universe bot
     * @param response String response by user
     */
    public ResponseManager(Checklist list, String response) {
        this.list = list;
        this.response = response;
    }

    /**
     * Execute the corresponding action by calling the correct functions in different classes.
     * Asks a Parser instance to help split the response to meaningful parts.
     * @throws InvalidResponseException if user input not recognised under list of keywords
     */
    public void execute() throws InvalidResponseException {
        Parser r = new Parser(response);

        if (response.contains("list")) { // prints checklist to screen if user says "list"
            System.out.println("Cosmic Chore Checklist:");
            list.printChecklist();
        } else if (response.startsWith("check")) { // mark chore as done
            int choreNumber = r.getChoreNumber();
            list.checkAsDone(choreNumber);
        } else if (response.startsWith("uncheck")) { // mark chore as not done
            int choreNumber = r.getChoreNumber();
            list.uncheckAsDone(choreNumber);
        } else if (response.startsWith("todo")) { // deals with a todo chore
            String description = r.getDescription();
            ToDo todo = new ToDo(description);
            list.addChore(todo);
        } else if (response.startsWith("deadline")) { // deals with a deadline chore
            String description = r.getDeadlineDescription();
            LocalDateTime date = r.getDate();
            Deadline deadline = new Deadline(description, date);
            list.addChore(deadline);
        } else if (response.startsWith("event")) { // deals with an event chore
            String description = r.getEventDescription();
            LocalDateTime start = r.getStartTime();
            LocalDateTime end = r.getEndTime();
            Event event = new Event(description, start, end);
            list.addChore(event);
        } else if (response.startsWith("remove")) { // deals with removing
            int choreNumber = r.getChoreNumber();
            list.removeChore(choreNumber);
        } else if (response.startsWith("filter")) { // deals with filtering all the chores on a date
            String dateString = r.getFilterDateString();
            LocalDateTime date = r.getFilterDate();
            list.filter(dateString, date);
        } else if (response.startsWith("find")) { // deals with finding chores with the keyword
            String keyword = r.getKeyword();
            list.find(keyword);
        } else { // command not recognised, so an invalid response exception is thrown
            throw new InvalidResponseException();
        }
    }
}
