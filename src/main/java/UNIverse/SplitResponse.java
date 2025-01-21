package UNIverse;

/**
 * SplitResponse class is chiefly in charge of splitting the user's response into useful parts:
 * description of the chore, the deadline, the start and end times of events
 */

public class SplitResponse {
    private String response;

    public SplitResponse(String response) {
        this.response = response;
    }

    // for the "todo" chore
    public String getDescription() {
        String[] parts = response.split(" ", 2);
        return parts[1].trim();
    }

    // for the deadline chore
    public String[] getDeadlineDetails() {
        String temp = this.getDescription();
        String[] parts = temp.split("by", 2);
        return parts;
    }

    // for the event chore
    public String[] getEventDetails() {
        String temp = this.getDescription();
        String[] mid = temp.split("from", 2);
        String[] furtherSplit = mid[1].split("to");
        String[] parts = new String[]{ mid[0], furtherSplit[0], furtherSplit[1] };
        return parts;
    }
}
