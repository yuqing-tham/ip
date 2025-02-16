package universe.response;

import java.time.LocalDateTime;

import universe.Checklist;
import universe.CommandsList;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.InvalidResponseException;

/**
 * Responsible for calling the corresponding functions based on user input.
 *
 * @author yuqing-tham
 */
public class ResponseManager {
    private Checklist chores;
    private String response;

    /**
     * Constructs a new ResponseManager to execute commands based on user input.
     *
     * @param chores A Checklist maintained by the Universe bot.
     * @param response A String representing the response by the user.
     */
    public ResponseManager(Checklist chores, String response) {
        this.chores = chores;
        this.response = response;
    }

    /**
     * Asks a Parser instance to help split the response to meaningful parts,
     * then executes the corresponding actions by calling functions in different classes.
     *
     * @throws InvalidResponseException If the user input is not recognised under the list of approved commands.
     */
    public void execute() throws InvalidResponseException {
        Parser r = new Parser(response);
        String command = r.getCommandFirstWord();
        switch (command) {
        case "hi":
            System.out.println("Greetings Universe traveller! Type 'help' if you need the commands list.");
            break;

        case "help":
            CommandsList commands = new CommandsList();
            commands.printCommands();
            break;

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
            try {
                Event event = new Event(eventDescription, startDateTime, endDateTime);
                chores.addChore(event);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
            }
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
