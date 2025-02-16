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
        Parser parser = new Parser(response);
        String command = parser.getCommandFirstWord();
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
            int choreNumberCheck = parser.getChoreNumber();
            chores.checkAsDone(choreNumberCheck);
            break;

        case "uncheck":
            int choreNumberUncheck = parser.getChoreNumber();
            chores.uncheckAsDone(choreNumberUncheck);
            break;

        case "todo":
            String todoDescription = parser.getDescription();
            ToDo todo = new ToDo(todoDescription);
            chores.addChore(todo);
            break;

        case "deadline":
            String deadlineDescription = parser.getDeadlineDescription();
            LocalDateTime deadlineDateTime = parser.getDate();
            Deadline deadline = new Deadline(deadlineDescription, deadlineDateTime);
            chores.addChore(deadline);
            break;

        case "event":
            String eventDescription = parser.getEventDescription();
            LocalDateTime startDateTime = parser.getStartTime();
            LocalDateTime endDateTime = parser.getEndTime();
            try {
                Event event = new Event(eventDescription, startDateTime, endDateTime);
                chores.addChore(event);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + "\n");
            }
            break;

        case "remove":
            int choreNumber = parser.getChoreNumber();
            chores.removeChore(choreNumber);
            break;

        case "filter":
            String dateString = parser.getFilterDateString();
            LocalDateTime filterDate = parser.getFilterDate();
            chores.filterByDate(dateString, filterDate);
            break;

        case "find":
            String keyword = parser.getKeyword();
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
