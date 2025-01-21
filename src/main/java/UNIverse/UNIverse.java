package UNIverse;

/**
 * UNIverse is the main class where the chatbot will run, performing actions such as
 * greeting the user, interacting with the user by responding appropriately, and
 * ending the session when the user says "bye".
 */
public class UNIverse {
    // fields to store the actions
    private Enter enter;
    private WaitForResponse wait;
    private Exit exit;
    private Checklist list;

    // constructor for UNIverse class
    // every new "session" with the chatbot will create new instances of the action classes
    public UNIverse() {
        this.enter = new Enter();
        this.wait = new WaitForResponse();
        this.exit = new Exit();
        this.list = new Checklist();
    }

    // main method to run the chatbot
    // includes UNIverse's responses to different scenarios
    public void run() {
        boolean isRunComplete = false; // a "toggle switch" to keep track of whether the session has ended
        enter.greet(); // UNIverse will greet the user for every new session

        // while user has yet to input "bye", UNIverse will wait for their response
        while (!isRunComplete) {
            String response = wait.getResponse();

            if (response.contains("bye")) { // session terminated once user says "bye"
                exit.bye();
                isRunComplete = true;
            } else if (response.contains("list")) { // prints checklist to screen if user says "list"
                System.out.println("Cosmic Chore Checklist:");
                list.printChecklist();
            } else if (response.startsWith("check")) { // mark chore as done
                list.checkAsDone(response);
            } else if (response.startsWith("uncheck")) { // mark chore as not done
                list.uncheckAsDone(response);
            } else { // adds the chore to the checklist
                Chore chore = new Chore(response);
                list.addChore(chore);
            }
        }
    }
    public static void main(String[] args) {
        new UNIverse().run();
    }
}
