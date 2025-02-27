package lubot.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lubot.storage.Storage;
import lubot.tasks.Deadline;
import lubot.tasks.Event;
import lubot.tasks.Task;
import lubot.tasks.TaskList;
import lubot.tasks.Todo;
import lubot.ui.Ui;
import lubot.util.DateUtil;

/**
 * The Parser class handles user input commands.
 */
public class Parser {
    /**
     * Processes the user input.
     *
     * @param userInput The user's input.
     * @param taskList  The task list to store tasks.
     * @param ui        The UI handler to display messages.
     * @param storage   The storage handler to save tasks.
     * @return false if the "exit" commad is given, otherwise true.
     */
    public static String processCommand(String userInput, TaskList taskList, Ui ui, Storage storage) {
        assert userInput != null : "Input should not be null";
        assert taskList != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0].toLowerCase();

        if (command.equals("exit")) {
            return ui.printExitMessage();
        }

        if (command.equals("tasks")) {
            return taskList.listTasks();
        }

        if (command.equals("help")) {
            return ui.printCommands();
        }

        // check number of argument
        if (splitInput.length != 2) {
            return ui.printErrorMessage("Unknown command.");
        }

        String response;
        switch (command) {
        case "mark":
        case "unmark":
            response = handleMarkUnmark(command, splitInput, taskList, ui);
            break;

        case "delete":
            response = handleDelete(splitInput, taskList, ui);
            break;

        case "todo":
            response = handleTodo(splitInput, taskList, ui);
            break;

        case "deadline":
            response = handleDeadline(splitInput, taskList, ui);
            break;

        case "event":
            response = handleEvent(splitInput, taskList, ui);
            break;

        case "find":
            String keyword = splitInput[1];
            response = taskList.findTasks(keyword);
            break;

        default:
            response = ui.printErrorMessage("Unknown command: " + command);
            break;
        }

        saveTasks(taskList, storage);
        return response;
    }

    /**
     * Handles marking and unmarking tasks.
     */
    private static String handleMarkUnmark(String command, String[] splitInput, TaskList taskList, Ui ui) {
        // check validitiy of index
        int index = parseTaskIndex(splitInput[1], taskList, ui);
        if (index == -1) {
            return ui.printErrorMessage("Invalid format! Use 'mark <task_index>' or 'unmark <task_index>'");
        }
        if (index == -2) {
            return ui.printErrorMessage("Invalid task index, pls enter a index from 1 to " + taskList.getSize());
        }

        /*
        // check 2nd argument
        try {
            index = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.printErrorMessage("Invalid format! Use 'mark <task_index>' or 'unmark <task_index>'");
        }

        // check index out of bound
        if (index < 0 || index > taskList.getSize() - 1) {
            return ui.printErrorMessage("invalid task index, pls enter a index from 1 to " + taskList.getSize());
        }
        */

        // mark
        if (command.equals("mark")) {
            Task updatedTask = taskList.markTask(index);
            return ui.printMessage(String.format("ive marked the follow task!\n  %d: %s", index + 1, updatedTask));
        }

        // unmark
        Task updatedTask = taskList.unmarkTask(index);
        return ui.printMessage(String.format("ive unmarked the following task!\n  %d: %s", index + 1, updatedTask));
    }

    /**
     * Handles deleting tasks.
     */
    private static String handleDelete(String[] splitInput, TaskList taskList, Ui ui) {
        // check validitiy of index
        int index = parseTaskIndex(splitInput[1], taskList, ui);
        if (index == -1) {
            return ui.printErrorMessage("Invalid format! Use 'delete <task_index>'");
        }
        if (index == -2) {
            return ui.printErrorMessage("Invalid task index, pls enter a index from 1 to " + taskList.getSize());
        }

        /*
        // check 2nd argument
        try {
            index = Integer.parseInt(splitInput[1]) - 1;
        } catch (NumberFormatException e) {
            return ui.printErrorMessage("Invalid format: Use 'delete <task_index>'");
        }

        // check index out of bound
        if (index < 0 || index > taskList.getSize() - 1) {
            return ui.printErrorMessage("Index out of bound: pls enter a index from 1 to " + taskList.getSize());
        }
        */

        // delete
        Task deletedTask = taskList.deleteTask(index);
        return ui.printMessage(String.format("ive deleted the following task!\n  %d: %s", index + 1, deletedTask));
    }

    /**
     * Handles adding a Todo task.
     */
    private static String handleTodo(String[] splitInput, TaskList taskList, Ui ui) {
        // check description
        if (splitInput[1].trim().isEmpty()) {
            return ui.printErrorMessage("Invalid input: description cannot be empty");
        }

        // update tasks
        Todo newTask = new Todo(splitInput[1]);
        taskList.addTask(newTask);
        return ui.printMessage(String.format("Added a todo!\n  %s", newTask));
    }

    /**
     * Handles adding a Deadline task.
     */
    private static String handleDeadline(String[] splitInput, TaskList taskList, Ui ui) {
        String[] deadlineParts = splitInput[1].split(" /by ", 2);

        // check number of arguments
        if (deadlineParts.length != 2) {
            return ui.printErrorMessage("Invalid input: Use 'deadline <desc> /by <yyyy-MM-dd>'");
        }

        // check description
        if (deadlineParts[0].trim().isEmpty()) {
            return ui.printErrorMessage("Invalid input: Description cannot be empty");
        }

        // check date format
        LocalDate dueDate = DateUtil.parseDate(deadlineParts[1]);
        if (dueDate == null) {
            return ui.printErrorMessage("Invalid input: Use yyyy-MM-dd");
        }

        // update tasks
        Deadline newTask = new Deadline(deadlineParts[0], dueDate);
        taskList.addTask(newTask);
        return ui.printMessage(String.format("Added a deadline\n  %s", newTask));
    }

    /**
     * Handles adding an Event task.
     */
    private static String handleEvent(String[] splitInput, TaskList taskList, Ui ui) {
        // check positioning of /from and /to
        if (splitInput[1].indexOf(" /from ") > splitInput[1].indexOf(" /to ")) {
            return ui.printErrorMessage("Invalid input: '/from' should be in front of '/to'");
        }

        String[] eventParts = splitInput[1].split(" /from | /to ");

        // check number of argument
        if (eventParts.length != 3) {
            return ui.printErrorMessage("Invalid input: Use 'event <desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>'");
        }

        // check description
        if (eventParts[0].trim().isEmpty()) {
            return ui.printErrorMessage("Invalid input: Description cannot be empty");
        }

        // check date format
        LocalDate fromDate = DateUtil.parseDate(eventParts[1]);
        LocalDate toDate = DateUtil.parseDate(eventParts[2]);
        if (fromDate == null || toDate == null) {
            return ui.printErrorMessage("Invalid input: Use yyyy-MM-dd");
        }

        // update tasks
        Event newTask = new Event(eventParts[0], fromDate, toDate);
        taskList.addTask(newTask);
        return ui.printMessage(String.format("Added an event!\n  %s", newTask));
    }

    /**
     * Saves tasks to storage.
     */
    public static void saveTasks(TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<String> taskStrings = new ArrayList<>();

        for (Task t : tasks) {
            String taskString = t.toStorageFormat();

            if (t != null) {
                taskStrings.add(taskString);
            }
        }

        storage.saveTasks(taskStrings);
    }

    /**
     * Converts tasks from storage format to Task class.
     */
    public static List<Task> rawTaskDataToTasks(List<String> rawTaskData) {
        List<Task> tasks = new ArrayList<>();

        for (String rawTaskString : rawTaskData) {
            Task t = Parser.rawTaskStringToTask(rawTaskString);

            if (t != null) {
                tasks.add(t);
            }
        }

        return tasks;
    }

    /**
     * Converts task string from storage format to Task class.
     */
    public static Task rawTaskStringToTask(String taskString) {
        String[] parts = taskString.split(" \\| ");

        // check number of arguments
        if (parts.length < 3) {
            return null;
        }

        boolean isDone = parts[1].equals("1");

        // Todo
        if (parts[0].equals("T")) {
            return isDone
                ? new Todo(parts[2]).markDone()
                : new Todo(parts[2]);
        }

        // Deadline
        if (parts[0].equals("D")) {
            LocalDate dueDate = DateUtil.parseDate(parts[3]);

            if (dueDate == null) {
                return null;
            }

            return isDone
                ? new Deadline(parts[2], dueDate).markDone()
                : new Deadline(parts[2], dueDate);
        }

        // Event
        LocalDate fromDate = DateUtil.parseDate(parts[3]);
        LocalDate toDate = DateUtil.parseDate(parts[4]);

        if (fromDate == null || toDate == null) {
            return null;
        }

        return isDone
            ? new Event(parts[2], fromDate, toDate).markDone()
            : new Event(parts[2], fromDate, toDate);
    }

    /**
     * Helper function to check for index out of bound error
     */
    private static int parseTaskIndex(String input, TaskList taskList, Ui ui) {
        try {
            int index = Integer.parseInt(input) - 1;

            // if index out of bound
            if (index < 0 || index >= taskList.getSize()) {
                return -1;
            }

            return index;
        } catch (NumberFormatException e) {
            // index not integer
            return -2;
        }
    }

}

