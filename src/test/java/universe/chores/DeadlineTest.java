package universe.chores;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    LocalDateTime dateTime = LocalDateTime.parse("31 Jan 2025 1600",
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    Deadline deadline = new Deadline("ip week 3", dateTime);

    @Test
    public void toStringTest(){
        assertEquals("[D][ ] ip week 3 (by: 31-1-2025 16:00)",
                deadline.toString());
    }

    @Test
    public void toFileStringTest(){
        assertEquals("D | 0 | ip week 3 | 31-1-2025 16:00",
                deadline.toFileString());
    }
}