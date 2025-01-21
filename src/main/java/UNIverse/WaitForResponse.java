package UNIverse;

import java.util.Scanner;

/**
 * The WaitForResponse class deals with detecting the user's response.
 */
public class WaitForResponse {
    private final Scanner scanner;

    public WaitForResponse() {
        this.scanner = new Scanner(System.in);
    }

    // uses scanner's nextLine() method to check for keyboard input by user
    public String getResponse() {
        return scanner.nextLine();
    }
}
