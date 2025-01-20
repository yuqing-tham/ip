package UNIverse;

import java.util.Scanner;

public class WaitForResponse {
    private final Scanner scanner;

    public WaitForResponse() {
        this.scanner = new Scanner(System.in);
    }

    public String getResponse() {
        return scanner.nextLine();
    }
}
