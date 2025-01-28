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

                // session terminated once user says "bye"
                if (response.contains("bye")) {
                    isRunComplete = true;
                    ui.bye();
                } else {
                    ResponseManager manager = new ResponseManager(list, response);
                    manager.execute();
                }

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
