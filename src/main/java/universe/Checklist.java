package universe;

import java.time.LocalDateTime;
import java.util.ArrayList;

import universe.chores.Chore;

/**
 * Checklist class helps keep track of the list of Chores to be completed.
 * @author yuqing-tham
 */
public class Checklist {
    private static ArrayList<Chore> chores = new ArrayList<>(100);

    /**
     * Getter to return the size of the Checklist.
     * @return the size of the list
     */
    public int getSize() {
        return chores.size();
    }

    /**
     * Getter to return the checklist itself.
     * @return the checklist
     */
    public static ArrayList<Chore> getChores() {
        return chores;
    }

    /**
     * Adds a chore to the checklist and prints a confirmation message
     * along with the number of chores in the list.
     * @param chore the Chore to be added
     */
    public void addChore(Chore chore) {
        chores.add(chore);
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
        assert index >= 0;
        Chore chore = chores.get(index);
        chores.remove(index);
        System.out.println("Got it, this chore is removed: \n" + chore.toString());
        this.printCount();
    }

    /**
     * Prints the number of chores in the checklist.
     */
    public void printCount() {
        int choreCount = chores.size();
        System.out.println("You now have " + choreCount + " chore(s) in the checklist.\n");
    }

    /**
     * Prints the contents of the checklist when user inputs the "list" command.
     */
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (Chore c : chores) { // for every element in the checklist
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
        assert index >= 0;
        Chore chore = chores.get(index);
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
        assert index >= 0;
        Chore chore = chores.get(index);
        chore.markAsNotDone();
        System.out.println(chore.getChoreDescription() + " marked as not done yet :(");
        System.out.println(chore.toString() + "\n");
    }

    /**
     * Filters the chores in the checklist with the corresponding date. Only checks deadline and event chores.
     * @param date the date to filter in String form
     * @param formattedDate the date to filter in LocalDateTime form
     */
    public void filterByDate(String date, LocalDateTime formattedDate) {
        System.out.println("Chores on " + date + ": ");
        boolean isFound = false;

        for (Chore c : chores) {
            if (c.isChoreWithTime() && c.getDateTime().toLocalDate().equals(formattedDate.toLocalDate())) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No deadlines or events on this date\n");
        }
    }

    /**
     * Finds the chores in the checklist that contains the keyword and prints them
     * @param keyword to be checked against the chore descriptions
     */
    public void findByKeyword(String keyword) {
        System.out.println("The matching Chores in the Checklist are:");

        boolean isFound = false;
        for (Chore c : chores) {
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
