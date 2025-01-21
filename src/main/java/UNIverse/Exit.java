package UNIverse;

/**
 * The Exit class deals with the goodbye message sent by UNIverse once "bye" is detected.
 */
public class Exit {
    // the goodbye message
    private static final String BYE = "Farewell, UNIverse traveller. May our paths intersect again soon!";
    public void bye() {
        System.out.println(BYE);
    }
}
