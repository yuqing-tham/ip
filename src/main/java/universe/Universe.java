package universe;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;

import universe.exceptions.IncorrectFormatException;
import universe.exceptions.UniverseException;
import universe.response.CheckResponse;
import universe.response.ResponseManager;

/**
 * Universe is the main class where the chatbot will run, performing actions such as
 * greeting the user, interacting with the user by responding appropriately, and
 * ending the session when the user says "bye".
 * @author yuqing-tham
 */
public class Universe {
    private final Ui ui;
    private Checklist list;
    private final Storage storage;

    /**
     * Constructor for the Universe class.
     * Creates a new instance of Ui to greet, wait for response and say goodbye.
     * Creates a new instance of Checklist to store the checklist.
     * Creates a new Storage instance to read and write to the file.
     * @param filepath pointing to where the file containing the existing Checklist is
     */
    public Universe(String filepath) {
        this.ui = new Ui();
        this.list = new Checklist();
        this.storage = new Storage(filepath);
    }

    /**
     * The main method to run the Universe chatbot.
     * Universe greets the user, read the file, and while user has yet to input "bye", waits for user's response,
     * finally it saves the new checklist to the file.
     * It catches Exceptions thrown by the different function calls and prints out the corresponding error messages.
     */
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
                System.out.println("Sorry, something wrong with the date format. "
                        + "Please key following 'D-MMM-YYYY' format.\n");
            }
        }
    }

    /**
     * Generates a response for the user's chat message in the GUI.
     * @return String response to be shown on the GUI.
     */
    public String getResponse(String input) {
        // save original System out
        PrintStream originalOut = System.out;

        // to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);
        System.setOut(newOut);

        try {
            CheckResponse c = new CheckResponse(input, list);
            c.handleError();

            ResponseManager manager = new ResponseManager(list, input);
            manager.execute();

            // save the chores to the corresponding file after each command
            storage.saveChores();
        } catch (FileNotFoundException e) {
            return "Sorry, your Checklist file cannot be found!\n";
        } catch (UniverseException e) {
            return e.getMessage() + "\n";
        } catch (IOException e) {
            return "Sorry, unable to save chores. Please try again.\n";
        } catch (DateTimeParseException e) {
            return "Sorry, something wrong with the date format. "
                    + "Please key following 'D-MMM-YYYY-HHmm' format.\n";
        } finally {
            // restore the original System out
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }

    /**
     * The main method where a new instance of the Universe chatbot is created.
     */
    public static void main(String[] args) {
        new Universe("data/chores.txt").run();
    }
}
