// package lubot;

// import lubot.tasks.TaskList;
// import lubot.storage.Storage;
// import lubot.ui.Ui;
// import lubot.parser.Parser;

public class Lubot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Lubot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    public void run() {
        ui.printWelcomeMessage();
		boolean isRunning = true;

        while (isRunning) {
            String userInput = ui.readCommand();
            ui.printHorizontalBar();
            isRunning = Parser.processCommand(userInput, tasks, ui, storage);
            ui.printHorizontalBar();
        }

        ui.close(); // Close Scanner to prevent leaks
    }

    public static void main(String[] args) {
        new Lubot("data/lubot.txt").run();
    }
}

