package universe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class deals with reading and writing to an existing checklist file.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    // reads the contents of the file and splits the contents into relevant parts
    // create the corresponding checklist of chores
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
            String choreType = parts[0];
            String isDone = parts[1];
            String description = parts[2];

            // create new chores and add to checklist based on chore type
            Chore chore = null;
            switch (choreType) {
            case "T":
                chore = new ToDo(description);
                break;
            case "D":
                String date = parts[3];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                LocalDate formattedDate = LocalDate.parse(date, formatter);
                chore = new Deadline(description, formattedDate);
                break;
            case "E":
                String[] temp = parts[3].split("-");
                chore = new Event(description, temp[0], temp[1]);
                break;
            default:
                throw new IncorrectFormatException();
            }

            // mark the chore as done if isDone is true (indicated as 1)
            if (isDone.equals("1")) {
                chore.markAsDone();
            }

            // add the chore to the checklist
            Checklist.getChecklist().add(chore);
        }
        s.close(); // close the scanner
    }

    // writes the new checklist to the existing file
    public void saveChores() throws IOException {
        FileWriter fw = new FileWriter(filepath);

        // format the checklist
        StringBuilder textToWrite = new StringBuilder();
        ArrayList<Chore> list = Checklist.getChecklist();
        for (Chore c : list) {
            textToWrite.append(c.toFileString());
            textToWrite.append("\n");
        }

        // write to the file
        fw.write(textToWrite.toString());
        fw.close();
    }
}
