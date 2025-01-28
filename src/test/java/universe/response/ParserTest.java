package universe.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private final String response = "todo finish watching lecture 2";
    private Parser parser = new Parser(response);
    private final String response1 = "deadline CA2 by 14 Apr 2025 2359";
    private Parser parser1 = new Parser(response1);
    private final String response2 = "event concert from 5 Mar 2025 1800 to 5 Mar 2025 2000";
    private Parser parser2 = new Parser(response2);

    @Test
    public void getDescriptionTest(){
        assertEquals("finish watching lecture 2", parser.getDescription());
    }

    @Test
    public void getDeadlineDescriptionTest(){
        assertEquals("CA2", parser1.getDeadlineDescription());
    }

    @Test
    public void getDateTest(){
        assertEquals(LocalDateTime.parse("2025-04-14T23:59"), parser1.getDate());
    }

    @Test
    public void getEventDescriptionTest(){
        assertEquals("concert", parser2.getEventDescription());
    }

    @Test
    public void getStartTimeTest(){
        assertEquals(LocalDateTime.parse("2025-03-05T18:00"), parser2.getStartTime());
    }

    @Test
    public void getEndTimeTest(){
        assertEquals(LocalDateTime.parse("2025-03-05T20:00"), parser2.getEndTime());
    }
}
