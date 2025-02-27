package lubot;

import lubot.parser.Parser;
import lubot.storage.Storage;
import lubot.tasks.TaskList;
import lubot.ui.Ui;

/**
 * Represents the Lubot chatbot application
 * It handles user interactions, task storage, and command parsing.
 */
public class Lubot {
    private static final String FILEPATH = "data/lubot.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Lubot instance that initializes UI, storage, and task list.
     */
    public Lubot() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        // this.storage = new Storage(filePath);
        this.tasks = new TaskList(Parser.rawTaskDataToTasks(storage.loadRawTaskData()));
    }

    /**
     * Processes user input and returns a response.
     *
     * @param input The user's input command.
     * @return The chatbot's response.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be empty";
        return Parser.processCommand(input, this.tasks, this.ui, this.storage);
    }

    /**
     * Prints welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.printWelcomeMessage();
    }

    /**
     * The entry point of Lubot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        // new Lubot("data/lubot.txt").run();
        new Lubot();
    }

    // /**
    //  * Runs the Lubot chatbot, handling user input and executing commands.
    //  */
    // public void run() {
    //     ui.printWelcomeMessage();
    //     tasks.listTasks();
    //     ui.printHorizontalBar();

    //     boolean isRunning = true;

    //     while (isRunning) {
    //         String userInput = ui.readCommand();
    //         ui.printHorizontalBar();
    //         isRunning = Parser.processCommand(userInput, tasks, ui, storage);
    //         ui.printHorizontalBar();
    //     }

    //     ui.close(); // Close Scanner to prevent leaks
    // }
}

