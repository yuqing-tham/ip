package universe.response;

import universe.Checklist;
import universe.exceptions.EmptyResponseException;
import universe.exceptions.InvalidResponseException;
import universe.exceptions.MissingDescriptionException;
import universe.exceptions.MissingFilterDateException;
import universe.exceptions.MissingIndexException;
import universe.exceptions.MissingKeywordException;
import universe.exceptions.MissingListItemException;
import universe.exceptions.MissingStartAndEndException;
import universe.exceptions.MissingTimeException;

/**
 * CheckResponse class checks if a response by the user contains
 * all the necessary details and handles errors accordingly.
 * @author yuqing-tham
 */
public class ResponseChecker {
    private String response;
    private Checklist chores;

    /**
     * Constructor for the CheckResponse class.
     * @param response String response by the user
     * @param chores the Checklist maintained by the Universe bot containing all the existing Chores
     */
    public ResponseChecker(String response, Checklist chores) {
        this.response = response;
        this.chores = chores;
    }

    /**
     * Checks the response passed into the CheckResponse instance and throw the corresponding exception.
     * @throws EmptyResponseException if user hits enter without inputting any text
     * @throws MissingDescriptionException if user inputs todo, deadline, event without any description
     * @throws MissingTimeException if user inputs deadline chores without their deadline
     * @throws MissingStartAndEndException if user inputs event chores without their start and/or end times
     * @throws MissingIndexException if user inputs check, uncheck and remove without a chore number
     * @throws InvalidResponseException if user inputs a unrecognisable command
     * @throws MissingListItemException if user inputs a command with a list number not in Checklist
     */
    public void handleError() throws EmptyResponseException, MissingDescriptionException, MissingTimeException,
            MissingStartAndEndException, MissingIndexException, InvalidResponseException, MissingListItemException,
            MissingFilterDateException, MissingKeywordException {
        if (response.isEmpty()) {
            throw new EmptyResponseException();
        } else {
            String[] r = response.split("\\s+"); // split response by spaces
            String command = r[0].trim(); // take first word

            switch (command) {
            case "todo":
            case "deadline":
            case "event":
                if (r.length < 2) {
                    throw new MissingDescriptionException();
                } else if (command.equals("deadline") && !(response.contains("by"))) {
                    throw new MissingTimeException();
                } else if (command.equals("event")) {
                    if (!(response.contains("from")) || !(response.contains("to"))) {
                        throw new MissingStartAndEndException();
                    }
                }
                break;

            case "check":
            case "uncheck":
            case "remove":
                if (r.length < 2) {
                    throw new MissingIndexException();
                } else {
                    int choreIndex = Integer.parseInt(r[1]);
                    if (choreIndex > chores.getSize()) {
                        throw new MissingListItemException();
                    }
                }
                break;

            case "list":
            case "clear":
                break;

            case "filter":
                if (r.length < 2) {
                    throw new MissingFilterDateException();
                }
                break;

            case "find":
                if (r.length < 2) {
                    throw new MissingKeywordException();
                }
                break;

            default:
                throw new InvalidResponseException();
            }
        }
    }
}
