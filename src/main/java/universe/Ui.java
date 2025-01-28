package universe;

import java.util.Scanner;

/**
 * Ui class deals with greeting the user, receiving user's response and saying goodbye.
 */
public class Ui {
    private static final String INTRO = "Welcome to the Universe! \nWhat cosmic quest can I assist you with today?\n";
    private static final String BYE = "Farewell, Universe traveller. May our paths intersect again soon!";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(INTRO);
    }

    public void bye() {
        System.out.println(BYE);
    }

    // uses scanner's nextLine() method to check for keyboard input by user
    public String getResponse() {
        return scanner.nextLine();
    }
}
