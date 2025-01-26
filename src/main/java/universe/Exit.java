package universe;

/**
 * The Exit class deals with the goodbye message sent by Universe once "bye" is detected.
 */
public class Exit {
    // the goodbye message
    private static final String BYE = "Farewell, Universe traveller. May our paths intersect again soon!";
    public void bye() {
        System.out.println(BYE);
    }
}
