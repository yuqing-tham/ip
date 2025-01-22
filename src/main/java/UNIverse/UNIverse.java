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
            try {
                String response = wait.getResponse();

                // checks if response is a valid response with the correct details required
                CheckResponse c = new CheckResponse(response);
                c.handleError();

                // if valid response, pass on the response to be split
                SplitResponse r = new SplitResponse(response);

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
                } else if (response.startsWith("todo")) { // deals with a todo chore
                    String description = r.getDescription();
                    ToDo todo = new ToDo(description);
                    list.addChore(todo);
                } else if (response.startsWith("deadline")) { // deals with a deadline chore
                    String[] temp = r.getDeadlineDetails();
                    Deadline deadline = new Deadline(temp[0].trim(), temp[1].trim());
                    list.addChore(deadline);
                } else if (response.startsWith("event")) { // deals with an event chore
                    String[] temp = r.getEventDetails();
                    Event event = new Event(temp[0].trim(), temp[1].trim(), temp[2].trim());
                    list.addChore(event);
                } else { // command not recognised, so an invalid response exception is thrown
                    throw new InvalidResponseException();
                }
            } catch (UNIverseException e) { // catches all the exceptions defined in the exception classes
                System.out.println(e.getMessage() + "\n"); // prints out error message
            }
        }
    }
    public static void main(String[] args) {
        new UNIverse().run();
    }
}
