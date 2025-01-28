package universe.chores;

import java.time.LocalDateTime;

/**
 * Chore class deals with the chore description and marks itself as done or not done.
 * Chore class inspired by the Task class partial solution provided in the instructions.
 */
public abstract class Chore {
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
    }

    // method to mark Chore as not done and prints out status
    public void markAsNotDone() {
        isDone = false;
    }

    // method to return chore description
    public String getChoreDescription() {
        return choreDescription;
    }

    // returns true if the chore is an event or deadline
    public boolean isChoreWithTime() {
        return false;
    }

    public abstract LocalDateTime getDateTime();

    // override toString() method to follow correct format and print chore description correctly
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + choreDescription;
    }

    // prints the details for the chore in the correct format to be put into the file
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + choreDescription;
    }
}
