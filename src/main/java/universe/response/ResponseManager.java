package universe.response;

import java.time.LocalDateTime;

import universe.Checklist;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.InvalidResponseException;

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
        String command = r.getCommandFirstWord();
        switch (command) {
        case "list":
            System.out.println("Cosmic Chore Checklist:");
            list.printChecklist();
            break;

        case "check":
            int choreNumberCheck = r.getChoreNumber();
            list.checkAsDone(choreNumberCheck);
            break;

        case "uncheck":
            int choreNumberUncheck = r.getChoreNumber();
            list.uncheckAsDone(choreNumberUncheck);
            break;

        case "todo":
            String todoDescription = r.getDescription();
            ToDo todo = new ToDo(todoDescription);
            list.addChore(todo);
            break;

        case "deadline":
            String deadlineDescription = r.getDeadlineDescription();
            LocalDateTime date = r.getDate();
            Deadline deadline = new Deadline(deadlineDescription, date);
            list.addChore(deadline);
            break;

        case "event":
            String eventDescription = r.getEventDescription();
            LocalDateTime start = r.getStartTime();
            LocalDateTime end = r.getEndTime();
            Event event = new Event(eventDescription, start, end);
            list.addChore(event);
            break;

        case "remove":
            int choreNumber = r.getChoreNumber();
            list.removeChore(choreNumber);
            break;

        case "filter":
            String dateString = r.getFilterDateString();
            LocalDateTime filterDate = r.getFilterDate();
            list.filterByDate(dateString, filterDate);
            break;

        case "find":
            String keyword = r.getKeyword();
            list.findByKeyword(keyword);
            break;

        default:
            throw new InvalidResponseException();
        }
    }
}
