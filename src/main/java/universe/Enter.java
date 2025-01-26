package universe;

/**
 * The Enter class deals with the greeting sent by Universe once a new session starts.
 */
public class Enter {
    // the "Hi" message
    private static final String INTRO = "Welcome to the Universe! \nWhat cosmic quest can I assist you with today?\n";
    public void greet() {
        System.out.println(INTRO);
    }
}
