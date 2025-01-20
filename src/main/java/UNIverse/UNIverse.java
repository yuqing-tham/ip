package UNIverse;
public class UNIverse {
    private Enter enter;
    private Exit exit;
    public UNIverse() {
        enter = new Enter();
        exit = new Exit();
    }
    public void run() {
        enter.greet();
        System.out.println("\n"); //temporary placeholder to separate greeting and goodbye
        exit.bye();
    }
    public static void main(String[] args) {
        new UNIverse().run();
    }
}
