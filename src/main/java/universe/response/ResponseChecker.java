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
 * Responsible for checking if a user input contains all the necessary details.
 *
 * @author yuqing-tham
 */
public class ResponseChecker {
    private String response;
    private Checklist chores;

    /**
     * Constructs a ResponseChecker with the specified user response and chores list.
     *
     * @param response A String response by the user.
     * @param chores The Checklist containing all the existing Chores.
     */
    public ResponseChecker(String response, Checklist chores) {
        this.response = response;
        this.chores = chores;
    }

    /**
     * Checks the user input and throws the corresponding exception.
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
            case "hi":
            case "help":
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
