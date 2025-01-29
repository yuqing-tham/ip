package universe;

import java.util.Scanner;

/**
 * Ui class deals with greeting the user, receiving user's response and saying goodbye.
 * @author yuqing-tham
 */
public class Ui {
    private static final String INTRO = "Welcome to the Universe! \nWhat cosmic quest can I assist you with today?\n";
    private static final String BYE = "Farewell, Universe traveller. May our paths intersect again soon!";
    private final Scanner scanner;

    /**
     * Constructor for the Ui class. Takes in input from user keyboard.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints out the greeting message.
     */
    public void greet() {
        System.out.println(INTRO);
    }

    /**
     * Prints out the goodbye message.
     */
    public void bye() {
        System.out.println(BYE);
    }

    /**
     * Uses scanner's nextLine() method to check for keyboard input by user.
     * @return String containing the user's response
     */
    public String getResponse() {
        return scanner.nextLine();
    }
}
