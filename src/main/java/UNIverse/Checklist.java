package UNIverse;

import java.util.ArrayList;

/**
 * Checklist class helps keep track of the list of chores to be completed.
 */

public class Checklist {
    private static ArrayList<String> checklist;

    public Checklist() {
        checklist = new ArrayList<>(100);
    }

    // method to add a chore to the Checklist
    public void addChore(String chore) {
        checklist.add(chore);
        System.out.println(chore + " added to Checklist!");
    }

    // method to print checklist when requested by user
    public void printChecklist() {
        int j = 1; // a counter to serve as index for the list
        for (String s : checklist) { // for every element in the checklist
            System.out.printf("%d %s%n", j, s);
            j++;
        }
    }
}
