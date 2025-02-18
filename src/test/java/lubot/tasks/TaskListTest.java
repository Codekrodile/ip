package lubot.tasks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testAddTask() {
        Task newTask = new Task("");
        taskList.addTask(newTask);

        assertEquals(taskList.getSize(), 1);
        assertEquals(taskList.getTasks().get(0), newTask);
    }

    @Test
    public void testDeleteTask() {
        Task newTask = new Task("");
        taskList.addTask(newTask);

        Task deletedTask = taskList.deleteTask(0);
        assertEquals(taskList.getSize(), 0);
        assertEquals(deletedTask, newTask);
    }

    @Test
    public void testMarkTask() {
        Task newTask = new Task("Read book");
        taskList.addTask(newTask);

        taskList.markTask(0);
        assertEquals(taskList.getTasks().get(0).toString(), "[x] Read book");
    }

    @Test
    public void testUnmarkTask() {
        Task newTask = new Task("Read book");
        taskList.addTask(newTask);

        taskList.unmarkTask(0);
        assertEquals(taskList.getTasks().get(0).toString(), "[ ] Read book");
    }
}
