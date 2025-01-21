package UNIverse;

import java.util.ArrayList;

/**
 * Checklist class helps keep track of the list of Chores to be completed.
 */

public class Checklist {
    private static ArrayList<Chore> checklist;

    public Checklist() {
        checklist = new ArrayList<>(100);
    }

    // method to add a chore to the Checklist
    public void addChore(Chore chore) {
        checklist.add(chore);
        System.out.println(chore.getChoreDescription() + " added to Checklist!");
    }

    // method to print checklist when requested by user
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (Chore c : checklist) { // for every element in the checklist
            System.out.printf("%d %s%n", j, c.toString());
            j++;
        }
    }

    // method to isolate the integer from the response and pass on to Chore class
    // passes arguments to the markAsDone method in Chore
    public void checkAsDone(String response) {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        Chore chore = checklist.get(choreNumber - 1);
        chore.markAsDone();
    }

    // method to isolate the integer from the response and pass on to Chore class
    // passes arguments to the markAsNotDone method in Chore
    public void uncheckAsDone(String response) {
        String[] parts = response.split(" ");
        int choreNumber = Integer.parseInt(parts[1]);
        Chore chore = checklist.get(choreNumber - 1);
        chore.markAsNotDone();
    }
}
