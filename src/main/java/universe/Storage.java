package universe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import universe.chores.Chore;
import universe.chores.Deadline;
import universe.chores.Event;
import universe.chores.ToDo;
import universe.exceptions.IncorrectFormatException;

/**
 * Storage class deals with reading and writing to an existing checklist file.
 * @author yuqing-tham
 */
public class Storage {
    private String filepath;

    /**
     * Constructor for the Storage class.
     * @param filepath String pointing to the file
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the content of the file and create the corresponding Checklist.
     * Catches DateTimeParseException if there are any issues with parsing the date
     * and prompts user to modify format in the file.
     * @throws IOException if there are any issues with reading the file.
     * @throws IncorrectFormatException if there are any issues with reading the file.
     */
    public void readFile() throws IOException, IncorrectFormatException {
        File f = new File(filepath);

        // if the directory does not exist, create the "data" directory
        f.getParentFile().mkdirs();

        // if the file does not exist, create the file
        if (!f.exists()) {
            f.createNewFile();
        }

        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String command = s.nextLine(); // read from the file

            // split the command into relevant parts
            String[] parts = command.split(" \\| ", 4);
            String choreType = parts[0].trim();
            String isDone = parts[1].trim();
            String description = parts[2].trim();

            // create new chores and add to checklist based on chore type
            Chore chore = null;
            switch (choreType) {
            case "T":
                chore = new ToDo(description);
                break;
            case "D":
                String deadlineDate = parts[3].trim();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");
                    LocalDateTime formattedDate = LocalDateTime.parse(deadlineDate, formatter);
                    chore = new Deadline(description, formattedDate);
                } catch (DateTimeParseException e) {
                    System.out.println("Something seems to be wrong with the date/time formats "
                            + "in the provided Checklist.\n");
                }
                break;
            case "E":
                String[] temp = parts[3].split("to");
                String startDateTime = temp[0].trim();
                String endDateTime = temp[1].trim();
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm");
                    LocalDateTime formattedStart = LocalDateTime.parse(startDateTime, formatter);
                    LocalDateTime formattedEnd = LocalDateTime.parse(endDateTime, formatter);
                    chore = new Event(description, formattedStart, formattedEnd);
                } catch (DateTimeParseException e) {
                    System.out.println("Something seems to be wrong with the date/time formats "
                            + "in the provided Checklist.\n");
                }
                break;
            default:
                throw new IncorrectFormatException();
            }

            // mark the chore as done if isDone is true (indicated as 1)
            if (isDone.equals("1")) {
                assert chore != null;
                chore.markAsDone();
            }

            // add the chore to the checklist
            Checklist.getChores().add(chore);
        }
        s.close(); // close the scanner
    }

    /**
     * Saves the modified Checklist to the file.
     * @throws IOException if there are any issues writing to the file.
     */
    public void saveChores() throws IOException {
        FileWriter fw = new FileWriter(filepath);

        // format the checklist
        StringBuilder textToWrite = new StringBuilder();
        ArrayList<Chore> list = Checklist.getChores();
        for (Chore c : list) {
            textToWrite.append(c.toFileString());
            textToWrite.append("\n");
        }

        // write to the file
        fw.write(textToWrite.toString());
        fw.close();
    }
}
