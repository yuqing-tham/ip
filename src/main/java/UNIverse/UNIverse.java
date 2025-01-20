package UNIverse;
public class UNIverse {
    private Enter enter;
    private WaitForResponse wait;
    private Exit exit;

    public UNIverse() {
        this.enter = new Enter();
        this.wait = new WaitForResponse();
        this.exit = new Exit();
    }
    public void run() {
        boolean isRunComplete = false; // a "toggle switch" to keep track of whether the session has ended

        enter.greet();

        while (!isRunComplete) {
            String response = wait.getResponse();
            if (response.equals("bye")) {
                exit.bye();
                isRunComplete = true;
            } else if (response.equals("list")) {
                System.out.println("Cosmic Chore Checklist: "); //temp placeholder for task list logic
            } else {
                System.out.println("Sorry, this word doesn't exist in the UNIverse library yet. " +
                        "Check back again in a million light years :)");
            }
        }

    }
    public static void main(String[] args) {
        new UNIverse().run();
    }
}
