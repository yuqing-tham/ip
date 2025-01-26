package universe;

/**
 * Chore class deals with the chore description and marks itself as done or not done.
 * Chore class inspired by the Task class partial solution provided in the instructions.
 */
public class Chore {
    // field to store the description and completion status
    private String choreDescription;
    private boolean isDone;

    public Chore(String choreDescription) {
        this.choreDescription = choreDescription;
        this.isDone = false; // completion status is not done by default
    }

    // method to mark Chore as done and prints out status
    public void markAsDone() {
        isDone = true;
        System.out.println("Yay " + choreDescription + " successfully completed!");
        System.out.println(this.toString() + "\n");
    }

    // method to mark Chore as not done and prints out status
    public void markAsNotDone() {
        isDone = false;
        System.out.println(choreDescription + " marked as not done yet :(");
        System.out.println(this.toString() + "\n");
    }

    // method to return chore description
    public String getChoreDescription() {
        return choreDescription;
    }

    // override toString() method to follow correct format and print chore description correctly
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + choreDescription;
    }
}
