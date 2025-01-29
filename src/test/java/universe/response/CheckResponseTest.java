package universe.response;

import org.junit.jupiter.api.Test;
import universe.Checklist;
import universe.ChecklistTest;
import universe.chores.ToDo;
import universe.exceptions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CheckResponseTest {
    Checklist testList = new Checklist();

    @Test
    public void handleError_emptyResponse_exceptionThrown() {
        String response = "";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (EmptyResponseException e) {
            assertEquals("Still waiting for your response!", e.getMessage());
        } catch (MissingStartAndEndException | MissingTimeException | MissingListItemException
                 | MissingIndexException | MissingDescriptionException | InvalidResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noToDoDescription_exceptionThrown() {
        String response = "todo";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingDescriptionException e) {
            assertEquals("A chore must have a description!", e.getMessage());
        } catch (MissingStartAndEndException | MissingTimeException | MissingListItemException
                 | MissingIndexException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noDeadlineDescription_exceptionThrown() {
        String response = "deadline ";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingDescriptionException e) {
            assertEquals("A chore must have a description!", e.getMessage());
        } catch (MissingStartAndEndException | MissingTimeException | MissingListItemException
                 | MissingIndexException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noEventDescription_exceptionThrown() {
        String response = "event";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingDescriptionException e) {
            assertEquals("A chore must have a description!", e.getMessage());
        } catch (MissingStartAndEndException | MissingTimeException | MissingListItemException
                 | MissingIndexException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noDeadline_exceptionThrown() {
        String response = "deadline weekly quiz 3";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingTimeException e) {
            assertEquals("A deadline chore must have a date/time!", e.getMessage());
        } catch (MissingStartAndEndException | MissingDescriptionException | MissingListItemException
                 | MissingIndexException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noStartEndTime_exceptionThrown() {
        String response = "event midterm quiz from 5 Mar 2025";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingStartAndEndException e) {
            assertEquals("An event must have a start and end time!", e.getMessage());
        } catch (MissingTimeException | MissingDescriptionException | MissingListItemException
                 | MissingIndexException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noCheckNumber_exceptionThrown() {
        String response = "check";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingIndexException e) {
            assertEquals("Please provide a chore number!", e.getMessage());
        } catch (MissingStartAndEndException | MissingDescriptionException | MissingListItemException
                 | MissingTimeException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noUncheckNumber_exceptionThrown() {
        String response = "uncheck ";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingIndexException e) {
            assertEquals("Please provide a chore number!", e.getMessage());
        } catch (MissingStartAndEndException | MissingDescriptionException | MissingListItemException
                 | MissingTimeException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_noRemoveNumber_exceptionThrown() {
        String response = "remove";
        CheckResponse check = new CheckResponse(response, testList);
        try {
            check.handleError();
        } catch (MissingIndexException e) {
            assertEquals("Please provide a chore number!", e.getMessage());
        } catch (MissingStartAndEndException | MissingDescriptionException | MissingListItemException
                 | MissingTimeException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }

    @Test
    public void handleError_listNumberExceeded_exceptionThrown() {
        String response = "check 3";
        ToDo todo = new ToDo("read week 3 readings");
        testList.addChore(todo);
        CheckResponse check = new CheckResponse(response, testList);

        try {
            check.handleError();
        } catch (MissingListItemException e) {
            assertEquals("This chore is not in the list :(", e.getMessage());
        } catch (MissingStartAndEndException | MissingDescriptionException | MissingIndexException
                 | MissingTimeException | InvalidResponseException | EmptyResponseException e) {
            fail();
        }
    }
}
