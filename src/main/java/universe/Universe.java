package universe;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.time.format.DateTimeParseException;

import universe.exceptions.IncorrectFormatException;
import universe.exceptions.UniverseException;
import universe.response.ResponseChecker;
import universe.response.ResponseManager;

/**
 * Responsible for running the Universe bot.
 *
 * @author yuqing-tham
 */
public class Universe {
    private final Ui ui;
    private Checklist chores;
    private final Storage storage;

    /**
     * Constructs a new Universe with a new Ui, Checklist and Storage instance.
     * Attempts to read the existing checklist file.
     *
     * @param filepath Filepath to the existing checklist file.
     */
    public Universe(String filepath) {
        this.ui = new Ui();
        this.chores = new Checklist();
        this.storage = new Storage(filepath);
        try {
            storage.readFile();
        } catch (IncorrectFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Sorry, unable to read provided checklist file.");
        }
    }

    /**
     * Greets the user, and while user has yet to input "bye", waits for user's response.
     */
    public void run() {
        ui.greet();

        boolean isRunComplete = false; // a "toggle switch" to keep track of whether the session has ended
        while (!isRunComplete) {
            try {
                // checks if response is a valid response with the correct details required
                String response = ui.getResponse();
                ResponseChecker c = new ResponseChecker(response, chores);
                c.handleError();

                if (response.contains("bye")) {
                    isRunComplete = true;
                    ui.bye();
                } else {
                    ResponseManager manager = new ResponseManager(chores, response);
                    manager.execute();
                }

                // save the chores to the file after each command
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
     * Generates a response for the user input in the GUI.
     *
     * @return A String response to be shown in the GUI.
     */
    public String getResponse(String input) {
        // save original system out
        PrintStream originalOut = System.out;

        // capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);
        System.setOut(newOut);

        try {
            ResponseChecker c = new ResponseChecker(input, chores);
            c.handleError();

            ResponseManager manager = new ResponseManager(chores, input);
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
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            // restore the original system out
            System.setOut(originalOut);
        }
        return outputStream.toString();
    }

    /**
     * Creates a new Universe.
     */
    public static void main(String[] args) {
        new Universe("data/chores.txt").run();
    }
}
