package universe;

/**
 * CheckResponse class checks if a response by the user contains all the necessary details and handles errors accordingly.
 */
public class CheckResponse {
    private String response;
    private Checklist list;

    public CheckResponse(String response, Checklist list) {
        this.response = response;
        this.list = list;
    }

    // method to handle all the errors in the responses
    public void handleError() throws EmptyResponseException, MissingDescriptionException, MissingTimeException,
            MissingStartAndEndException, MissingIndexException, InvalidResponseException, MissingListItemException {
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
                    int choreIndex = Integer.parseInt(r[1]); // only applicable to commands check, uncheck and remove
                    if (choreIndex > list.getSize()) {
                        throw new MissingListItemException();
                    }
                }
                break;
            }
        }
    }
}
