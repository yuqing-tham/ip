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
    private Checklist chores;
    private String response;

    /**
     * Constructor for the ResponseManager class.
     * @param chores Checklist maintained by the Universe bot
     * @param response String response by user
     */
    public ResponseManager(Checklist chores, String response) {
        this.chores = chores;
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
            chores.printChecklist();
            break;

        case "check":
            int choreNumberCheck = r.getChoreNumber();
            chores.checkAsDone(choreNumberCheck);
            break;

        case "uncheck":
            int choreNumberUncheck = r.getChoreNumber();
            chores.uncheckAsDone(choreNumberUncheck);
            break;

        case "todo":
            String todoDescription = r.getDescription();
            ToDo todo = new ToDo(todoDescription);
            chores.addChore(todo);
            break;

        case "deadline":
            String deadlineDescription = r.getDeadlineDescription();
            LocalDateTime deadlineDateTime = r.getDate();
            Deadline deadline = new Deadline(deadlineDescription, deadlineDateTime);
            chores.addChore(deadline);
            break;

        case "event":
            String eventDescription = r.getEventDescription();
            LocalDateTime startDateTime = r.getStartTime();
            LocalDateTime endDateTime = r.getEndTime();
            Event event = new Event(eventDescription, startDateTime, endDateTime);
            chores.addChore(event);
            break;

        case "remove":
            int choreNumber = r.getChoreNumber();
            chores.removeChore(choreNumber);
            break;

        case "filter":
            String dateString = r.getFilterDateString();
            LocalDateTime filterDate = r.getFilterDate();
            chores.filterByDate(dateString, filterDate);
            break;

        case "find":
            String keyword = r.getKeyword();
            chores.findByKeyword(keyword);
            break;

        case "clear":
            chores.clearAllChores();
            break;

        default:
            throw new InvalidResponseException();
        }
    }
}
