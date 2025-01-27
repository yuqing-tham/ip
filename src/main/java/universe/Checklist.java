package universe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Checklist class helps keep track of the list of Chores to be completed.
 */

public class Checklist {
    private static ArrayList<Chore> checklist = new ArrayList<>(100);;

    public int getSize() {
        return checklist.size();
    }

    public static ArrayList<Chore> getChecklist() {
        return checklist;
    }

    // method to add a chore to the Checklist
    public void addChore(Chore chore) {
        checklist.add(chore);
        System.out.println(chore.toString() + " added to Checklist!");
        this.printCount();
    }

    // method to remove a chore from the Checklist
    public void removeChore(String response) {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        int index = choreNumber - 1;
        Chore chore = checklist.get(index);
        checklist.remove(index);
        System.out.println("Got it, this chore is removed: \n" + chore.toString());
        this.printCount();
    }

    // method to print choreCount
    public void printCount() {
        // new message to reflect number of chores in the checklist
        int choreCount = checklist.size();
        System.out.println("You now have " + choreCount + " chore(s) in the checklist.\n");
    }

    // method to print checklist when requested by user
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (Chore c : checklist) { // for every element in the checklist
            System.out.printf("%d %s%n", j, c.toString());
            j++;
        }
        this.printCount();
    }

    // method to isolate the integer from the response and pass on to Chore class
    // passes arguments to the markAsDone method in Chore
    public void checkAsDone(String response) {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        Chore chore = checklist.get(choreNumber - 1);
        chore.markAsDone();
        System.out.println("Yay " + chore.getChoreDescription() + " successfully completed!");
        System.out.println(chore.toString() + "\n");
    }

    // method to isolate the integer from the response and pass on to Chore class
    // passes arguments to the markAsNotDone method in Chore
    public void uncheckAsDone(String response) {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        Chore chore = checklist.get(choreNumber - 1);
        chore.markAsNotDone();
        System.out.println(chore.getChoreDescription() + " marked as not done yet :(");
        System.out.println(chore.toString() + "\n");
    }

    public void filter(String response) {
        String[] parts = response.split(" ", 2);
        String date = parts[1].trim();
        LocalDateTime dateToFilter = LocalDateTime.parse(date + " 00:00"
                , DateTimeFormatter.ofPattern("d MMM yyy HH:mm"));

        System.out.println("Chores on " + date + ": ");
        boolean isFound = false;
        for (Chore c : checklist) {
            if (c.isChoreWithTime() && c.getDateTime().toLocalDate().equals(dateToFilter.toLocalDate())) {
                isFound = true;
                System.out.println(c.toString());
            }
        }

        if (!isFound) {
            System.out.println("No deadlines or events on this date\n");
        }
    }
}
