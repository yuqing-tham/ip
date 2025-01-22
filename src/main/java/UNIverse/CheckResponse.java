package UNIverse;

/**
 * CheckResponse class checks if a response by the user contains all the necessary details and handles errors accordingly.
 */
public class CheckResponse {
    private String response;

    public CheckResponse(String response) {
        this.response = response;
    }

    public void handleError() throws MissingDescriptionException, MissingTimeException, MissingStartAndEndException {
        if (response.equals("todo") | response.equals("todo ")) {
            throw new MissingDescriptionException();
        } else if (response.equals("deadline") | response.equals("deadline ")) {
            throw new MissingDescriptionException();
        } else if (response.equals("event") | response.equals("event ")) {
            throw new MissingDescriptionException();
        } else if (response.contains("deadline") && !(response.contains("by"))) {
            throw new MissingTimeException();
        } else if (response.contains("event")) {
            if (!(response.contains("from")) | !(response.contains("to"))) {
                throw new MissingStartAndEndException();
            }
        }
    }
}
