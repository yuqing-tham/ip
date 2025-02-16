package universe.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UserInputParserTest {
    private final String response = "todo finish watching lecture 2";
    private UserInputParser userInputParser = new UserInputParser(response);
    private final String response1 = "deadline CA2 by 14 Apr 2025 2359";
    private UserInputParser userInputParser1 = new UserInputParser(response1);
    private final String response2 = "event concert from 5 Mar 2025 1800 to 5 Mar 2025 2000";
    private UserInputParser userInputParser2 = new UserInputParser(response2);

    @Test
    public void getDescriptionTest() {
        assertEquals("finish watching lecture 2", userInputParser.getDescription());
    }

    @Test
    public void getDeadlineDescriptionTest() {
        assertEquals("CA2", userInputParser1.getDeadlineDescription());
    }

    @Test
    public void getDateTest() {
        assertEquals(LocalDateTime.parse("2025-04-14T23:59"), userInputParser1.getDeadlineDate());
    }

    @Test
    public void getEventDescriptionTest() {
        assertEquals("concert", userInputParser2.getEventDescription());
    }

    @Test
    public void getStartTimeTest() {
        assertEquals(LocalDateTime.parse("2025-03-05T18:00"), userInputParser2.getStartTime());
    }

    @Test
    public void getEndTimeTest() {
        assertEquals(LocalDateTime.parse("2025-03-05T20:00"), userInputParser2.getEndTime());
    }

    @Test
    public void getChoreNumberTest() {
        String response = "remove 3";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals(3, userInputParser.getChoreNumber());
    }

    @Test
    public void getFilterDateStringTest() {
        String response = "filter 23 Feb 2025";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals("23 Feb 2025", userInputParser.getFilterDateString());
    }

    @Test
    public void getFilterDateTest() {
        String response = "filter 23 Feb 2025";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals(LocalDateTime.parse("2025-02-23T00:00"), userInputParser.getFilterDate());
    }

    @Test
    public void getKeywordTest() {
        String response = "find book";
        UserInputParser userInputParser = new UserInputParser(response);
        assertEquals("book", userInputParser.getFindKeyword());
    }
}
