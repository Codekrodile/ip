package lubot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lubot.storage.Storage;
import lubot.tasks.TaskList;
import lubot.ui.Ui;

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
