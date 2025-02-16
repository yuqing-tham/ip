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
     * Asks a UserInputParser instance to help split the response to meaningful parts,
     * then executes the corresponding actions by calling functions in different classes.
     *
     * @throws InvalidResponseException If the user input is not recognised under the list of approved commands.
     */
    public void execute() throws InvalidResponseException {
        UserInputParser userInputParser = new UserInputParser(response);
        String command = userInputParser.getCommand();

        switch (command) {
        case "hi" -> handleHi();
        case "help" -> handleHelp();
        case "list" -> handleList();
        case "check" -> handleCheck(userInputParser);
        case "uncheck" -> handleUncheck(userInputParser);
        case "todo" -> handleToDo(userInputParser);
        case "deadline" -> handleDeadline(userInputParser);
        case "event" -> handleEvent(userInputParser);
        case "remove" -> handleRemove(userInputParser);
        case "filter" -> handleFilter(userInputParser);
        case "find" -> handleFind(userInputParser);
        case "clear" -> handleClear();
        default -> throw new InvalidResponseException();
        }
    }

    public void handleHi() {
        System.out.println("Greetings Universe traveller! Type 'help' if you need the commands list.");
    }

    public void handleHelp() {
        CommandsList commands = new CommandsList();
        commands.printCommands();
    }

    public void handleList() {
        System.out.println("Cosmic Chore Checklist:");
        chores.printChecklist();
    }

    public void handleCheck(UserInputParser userInputParser) {
        int choreNumberCheck = userInputParser.getChoreNumber();
        chores.checkAsDone(choreNumberCheck);
    }

    public void handleUncheck(UserInputParser userInputParser) {
        int choreNumberUncheck = userInputParser.getChoreNumber();
        chores.uncheckAsDone(choreNumberUncheck);
    }

    public void handleToDo(UserInputParser userInputParser) {
        String todoDescription = userInputParser.getDescription();
        ToDo todo = new ToDo(todoDescription);
        chores.addChore(todo);
    }

    public void handleDeadline(UserInputParser userInputParser) {
        String deadlineDescription = userInputParser.getDeadlineDescription();
        LocalDateTime deadlineDateTime = userInputParser.getDeadlineDate();
        Deadline deadline = new Deadline(deadlineDescription, deadlineDateTime);
        chores.addChore(deadline);
    }

    public void handleEvent(UserInputParser userInputParser) {
        String eventDescription = userInputParser.getEventDescription();
        LocalDateTime startDateTime = userInputParser.getStartTime();
        LocalDateTime endDateTime = userInputParser.getEndTime();
        try {
            Event event = new Event(eventDescription, startDateTime, endDateTime);
            chores.addChore(event);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void handleRemove(UserInputParser userInputParser) {
        int choreNumber = userInputParser.getChoreNumber();
        chores.removeChore(choreNumber);
    }

    public void handleFilter(UserInputParser userInputParser) {
        String dateString = userInputParser.getFilterDateString();
        LocalDateTime filterDate = userInputParser.getFilterDate();
        chores.filterByDate(dateString, filterDate);
    }

    public void handleFind(UserInputParser userInputParser) {
        String keyword = userInputParser.getFindKeyword();
        chores.findByKeyword(keyword);
    }

    public void handleClear() {
        chores.clearAllChores();
    }
}
