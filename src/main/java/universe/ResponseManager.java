package universe;

import java.time.LocalDateTime;

public class ResponseManager {
    private Checklist list;
    private String response;

    public ResponseManager(Checklist list, String response) {
        this.list = list;
        this.response = response;
    }

    public void execute() throws InvalidResponseException {
        Parser r = new Parser(response);

        if (response.contains("list")) { // prints checklist to screen if user says "list"
            System.out.println("Cosmic Chore Checklist:");
            list.printChecklist();
        } else if (response.startsWith("check")) { // mark chore as done
            list.checkAsDone(response);
        } else if (response.startsWith("uncheck")) { // mark chore as not done
            list.uncheckAsDone(response);
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
            list.removeChore(response);
        } else if (response.startsWith("filter")) {
            list.filter(response);
        } else { // command not recognised, so an invalid response exception is thrown
            throw new InvalidResponseException();
        }
    }
}
