package lubot.parser;

import lubot.storage.Storage;
import lubot.tasks.Event;
import lubot.tasks.Deadline;
import lubot.tasks.Task;
import lubot.tasks.TaskList;
import lubot.tasks.Todo;
import lubot.ui.Ui;
import lubot.util.DateUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage("temp/test.txt");
    }

    @Test
    public void testProcessCommand() {
        String userInput = "todo Read book";
        Parser.processCommand(userInput, taskList, ui, storage);

        assertEquals(taskList.getTasks().get(0).toString(), "[T][ ] Read book");
    }
}
