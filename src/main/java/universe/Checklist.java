package universe;

import universe.chores.Chore;
import universe.response.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Checklist class helps keep track of the list of Chores to be completed.
 * @author yuqing-tham
 */
public class Checklist {
    private static ArrayList<Chore> checklist = new ArrayList<>(100);

    /**
     * Getter to return the size of the Checklist.
     * @return the size of the list
     */
    public int getSize() {
        return checklist.size();
    }

    /**
     * Getter to return the checklist itself.
     * @return the checklist
     */
    public static ArrayList<Chore> getChecklist() {
        return checklist;
    }

    /**
     * Adds a chore to the checklist and prints a confirmation message
     * along with the number of chores in the list.
     * @param chore the Chore to be added
     */
    public void addChore(Chore chore) {
        checklist.add(chore);
        System.out.println(chore.toString() + " added to Checklist!");
        this.printCount();
    }

    /**
     * Removes a chore to the checklist and prints a confirmation message
     * along with the number of remaining chores in the list.
     * @param choreNumber the number of the Chore to be removed
     */
    public void removeChore(int choreNumber) {
        int index = choreNumber - 1;
        Chore chore = checklist.get(index);
        checklist.remove(index);
        System.out.println("Got it, this chore is removed: \n" + chore.toString());
        this.printCount();
    }

    /**
     * Prints the number of chores in the checklist.
     */
    public void printCount() {
        int choreCount = checklist.size();
        System.out.println("You now have " + choreCount + " chore(s) in the checklist.\n");
    }

    /**
     * Prints the contents of the checklist when user inputs the "list" command.
     */
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (Chore c : checklist) { // for every element in the checklist
            System.out.printf("%d %s%n", j, c.toString());
            j++;
        }
        this.printCount();
    }

    /**
     * Locates the corresponding chore in the checklist and asks the Chore to mark itself as done.
     * Prints out a confirmation message.
     * @param choreNumber the choreNumber minus 1 is the corresponding index in the ArrayList
     */
    public void checkAsDone(int choreNumber) {
        int index = choreNumber - 1;
        Chore chore = checklist.get(index);
        chore.markAsDone();
        System.out.println("Yay " + chore.getChoreDescription() + " successfully completed!");
        System.out.println(chore.toString() + "\n");
    }

    /**
     * Locates the corresponding chore in the checklist and asks the Chore to mark itself as not done.
     * Prints out a confirmation message.
     * @param choreNumber the choreNumber minus 1 is the corresponding index in the ArrayList
     */
    public void uncheckAsDone(int choreNumber) {
        int index = choreNumber - 1;
        Chore chore = checklist.get(index);
        chore.markAsNotDone();
        System.out.println(chore.getChoreDescription() + " marked as not done yet :(");
        System.out.println(chore.toString() + "\n");
    }

    /**
     * Filters the chores in the checklist with the corresponding date. Only checks deadline and event chores.
     * @param date the date to filter in String form
     * @param formattedDate the date to filter in LocalDateTime form
     */
    public void filter(String date, LocalDateTime formattedDate) {
        System.out.println("Chores on " + date + ": ");
        boolean isFound = false;

        for (Chore c : checklist) {
            if (c.isChoreWithTime() && c.getDateTime().toLocalDate().equals(formattedDate.toLocalDate())) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No deadlines or events on this date\n");
        }
    }

    public void find(String keyword) {
        System.out.println("The matching Chores in the Checklist are:");

        boolean isFound = false;
        for (Chore c : checklist) {
            if (c.getChoreDescription().contains(keyword)) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No Chores matching this description\n");
        }
    }
}
