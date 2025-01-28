package universe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Universe is the main class where the chatbot will run, performing actions such as
 * greeting the user, interacting with the user by responding appropriately, and
 * ending the session when the user says "bye".
 */
public class Universe {
    private final Ui ui;
    private Checklist list;
    private final Storage storage;

    // constructor for Universe class
    // every new "session" with the chatbot will create new instances of the action classes
    public Universe(String filepath) {
        this.ui = new Ui();
        this.list = new Checklist();
        this.storage = new Storage(filepath);
    }

    // main method to run the chatbot
    // includes Universe's responses to different scenarios
    public void run() {
        ui.greet(); // Universe will greet the user for every new session

        // attempt to read existing checklist file
        try {
            storage.readFile();
        } catch (IncorrectFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Sorry, unable to read provided checklist file.");
        }

        boolean isRunComplete = false; // a "toggle switch" to keep track of whether the session has ended

        // while user has yet to input "bye", Universe will wait for their response
        while (!isRunComplete) {
            try {
                // checks if response is a valid response with the correct details required
                String response = ui.getResponse();
                CheckResponse c = new CheckResponse(response, list);
                c.handleError();

                // if valid response, pass on the response to be split
                // Parser r = new Parser(response);

                // session terminated once user says "bye"
                if (response.contains("bye")) {
                    isRunComplete = true;
                    ui.bye();
                } else {
                    ResponseManager manager = new ResponseManager(ui, list, response);
                    manager.execute();
                }

                /*
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

                 */

                // save the chores to the corresponding file after each command
                storage.saveChores();

            } catch (FileNotFoundException e) {
                System.out.println("Sorry, your Checklist file cannot be found!\n");
            } catch (UniverseException e) {
                System.out.println(e.getMessage() + "\n");
            } catch (IOException e) {
                System.out.println("Sorry, unable to save chores. Please try again.\n");
            } catch (DateTimeParseException e) {
                System.out.println("Sorry, something wrong with the date format. " +
                        "Please key following 'D-MMM-YYYY' format.\n");
            }
        }
    }

    public static void main(String[] args) {
        new Universe("data/chores.txt").run();
    }
}
