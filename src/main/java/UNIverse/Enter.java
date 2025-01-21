package UNIverse;

/**
 * The Enter class deals with the greeting sent by UNIverse once a new session starts.
 */
public class Enter {
    // the "Hi" message
    private static final String INTRO = "Welcome to the UNIverse! \nWhat cosmic quest can I assist you with today?";
    public void greet() {
        System.out.println(INTRO);
    }
}
