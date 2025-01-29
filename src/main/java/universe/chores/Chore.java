package universe.chores;

import java.time.LocalDateTime;

/**
 * Chore is the abstract base class for all types of Chores: ToDo, Deadline and Event,
 * which deals with the chore description, whether the Chore is a chore with time details,
 * prints formatted information about the Chore in two different formats using
 * methods toString() and toFileString(), and marks itself as done or not done.
 * The implementation of this class is inspired by the Task class partial solution provided.
 * @author yuqing-tham
 */
public abstract class Chore {
    private String choreDescription;
    private boolean isDone;

    /**
     * Constructor for Chore class. Takes in the choreDescription
     * and sets the state isDone to the default false.
     * @param choreDescription String description of the chore
     */
    public Chore(String choreDescription) {
        this.choreDescription = choreDescription;
        this.isDone = false; // completion status is not done by default
    }

    /**
     * Getter to return the private field choreDescription.
     * @return choreDescription
     */
    public String getChoreDescription() {
        return choreDescription;
    }

    /**
     * Getter to return the date and time details for subclasses Deadline and Event.
     * Subclasses ToDo, Deadline and Event override this method.
     * @return date and time details
     */
    public abstract LocalDateTime getDateTime();

    /**
     * A chore and a ToDo is not a Chore with time.
     * @return <code>true</code> if it is a chore with date and time details
     */
    public boolean isChoreWithTime() {
        return false;
    }

    /**
     * Mark the Chore as Done by changing its isDone state to true from the default false.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the Chore as Not Done by changing its isDone state to false.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Override the Object.toString() method to print the choreDescription.
     * @return a formatted String intended to show Chore successfully added
     */
    @Override
    public String toString() {
        String status = isDone ? "X" : " ";
        return "[" + status + "] " + choreDescription;
    }

    /**
     * Format a String to print choreDescription in an alternative way.
     * @return a formatted String intended to be written into the Checklist file
     */
    // prints the details for the chore in the correct format to be put into the file
    public String toFileString() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + choreDescription;
    }
}
