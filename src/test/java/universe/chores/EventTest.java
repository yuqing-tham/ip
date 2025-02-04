package universe.chores;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {
    private LocalDateTime startTime = LocalDateTime.parse("31 Jan 2025 1600",
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    private LocalDateTime endTime = LocalDateTime.parse("31 Jan 2025 1800",
            DateTimeFormatter.ofPattern("d MMM yyyy HHmm"));
    private Event event = new Event("project meeting", startTime, endTime);

    @Test
    public void toStringTest() {
        assertEquals("[E][ ] project meeting (from: 31-1-2025 16:00 to: 31-1-2025 18:00)",
                event.toString());
    }

    @Test
    public void toFileStringTest() {
        assertEquals("E | 0 | project meeting | 31-1-2025 16:00 to 31-1-2025 18:00",
                event.toFileString());
    }
}
