package lubot;

import lubot.tasks.TaskList;
import lubot.storage.Storage;
import lubot.ui.Ui;
import lubot.parser.Parser;

/**
 * Represents the Lubot chatbot application
 * It handles user interactions, task storage, and command parsing.
 */
public class Lubot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

	/**
	 * Constructs a Lubot instance that initializes UI, storage, and task list.
	 *
	 * @param filePath The file path where tasks are stored and loaded from.
	 */
    public Lubot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(Parser.rawTaskDataToTasks(storage.loadRawTaskData()));
    }

	/**
	 * Runs the Lubot chatbot, handling user input and executing commands.
	 */
    public void run() {
        ui.printWelcomeMessage();
        tasks.listTasks();
        ui.printHorizontalBar();

		boolean isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            ui.printHorizontalBar();
            isRunning = Parser.processCommand(userInput, tasks, ui, storage);
            ui.printHorizontalBar();
        }

        ui.close(); // Close Scanner to prevent leaks
    }

	/**
	 * The entry point of Lubot
	 *
	 * @param args Command-line arguments
	 */
    public static void main(String[] args) {
        new Lubot("data/lubot.txt").run();
    }
}

