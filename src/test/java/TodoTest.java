import mario.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testGetName() {
        Todo todo1 = new Todo("Task name", false);
        String expected1 = "[T][ ] Task name";
        assertEquals(expected1, todo1.getName());

        Todo todo2 = new Todo("Task name", true);
        String expected2 = "[T][X] Task name";
        assertEquals(expected2, todo2.getName());
    }

    @Test
    public void testToFileFormat() {
        Todo todo1 = new Todo("Task1 name", false);
        String expected1 = "T | 0 | Task1 name";
        assertEquals(expected1, todo1.toFileFormat());

        Todo todo2 = new Todo("Task2 name", true);
        String expected2 = "T | 1 | Task2 name";
        assertEquals(expected2, todo2.toFileFormat());
    }
}
