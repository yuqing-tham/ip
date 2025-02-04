package universe.chores;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    private ToDo todo = new ToDo("buy pens");

    @Test
    public void toStringTest() {
        assertEquals("[T][ ] buy pens", todo.toString());
    }

    @Test
    public void toFileStringTest() {
        assertEquals("T | 0 | buy pens", todo.toFileString());
    }
}
