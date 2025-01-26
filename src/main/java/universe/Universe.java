package universe;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Universe is the main class where the chatbot will run, performing actions such as
 * greeting the user, interacting with the user by responding appropriately, and
 * ending the session when the user says "bye".
 */
public class Universe {
    // fields to store the actions
    private Enter enter;
    private WaitForResponse wait;
    private Exit exit;
    private Checklist list;

    // constructor for Universe class
    // every new "session" with the chatbot will create new instances of the action classes
    public Universe() {
        this.enter = new Enter();
        this.wait = new WaitForResponse();
        this.exit = new Exit();
        this.list = new Checklist();
    }

    // main method to run the chatbot
    // includes Universe's responses to different scenarios
    public void run() {
        boolean isRunComplete = false; // a "toggle switch" to keep track of whether the session has ended
        enter.greet(); // Universe will greet the user for every new session

        // create a new Storage instance with the default filepath
        Storage storage = new Storage("data/chores.txt");

        // attempt to read the file and check if the file exists
        try {
            storage.readFile();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, your Checklist file cannot be found!\n");
        } catch (IncorrectFormatException | IOException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // bot then waits for users' inputs
        // while user has yet to input "bye", Universe will wait for their response
        while (!isRunComplete) {
            try {
                String response = wait.getResponse();

                // checks if response is a valid response with the correct details required
                CheckResponse c = new CheckResponse(response, list);
                c.handleError();

                // if valid response, pass on the response to be split
                SplitResponse r = new SplitResponse(response);

                if (response.contains("bye")) { // session terminated once user says "bye"
                    exit.bye();
                    isRunComplete = true;
                } else if (response.contains("list")) { // prints checklist to screen if user says "list"
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
                    String[] temp = r.getDeadlineDetails();
                    Deadline deadline = new Deadline(temp[0].trim(), temp[1].trim());
                    list.addChore(deadline);
                } else if (response.startsWith("event")) { // deals with an event chore
                    String[] temp = r.getEventDetails();
                    Event event = new Event(temp[0].trim(), temp[1].trim(), temp[2].trim());
                    list.addChore(event);
                } else if (response.startsWith("remove")) { // deals with removing
                    list.removeChore(response);
                } else { // command not recognised, so an invalid response exception is thrown
                    throw new InvalidResponseException();
                }

                // save the chores to the corresponding file after each command
                storage.saveChores();

            } catch (UniverseException e) { // catches all the exceptions defined in the exception classes
                System.out.println(e.getMessage() + "\n"); // prints out error message
            } catch (IOException e) {
                System.out.println("Sorry, unable to save chores. Please try again.\n");
            }
        }
    }
    public static void main(String[] args) {
        new Universe().run();
    }
}
