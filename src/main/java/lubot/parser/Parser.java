package lubot.parser;

import lubot.storage.Storage;
import lubot.tasks.Event;
import lubot.tasks.Deadline;
import lubot.tasks.Task;
import lubot.tasks.TaskList;
import lubot.tasks.Todo;
import lubot.ui.Ui;
import lubot.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static boolean processCommand(String userInput, TaskList taskList, Ui ui, Storage storage) {
        String[] splitInput = userInput.split(" ", 2);
        String command = splitInput[0].toLowerCase();

		if (command.equals("exit")) {
			ui.printExitMessage();
			return false;
		}

		if (command.equals("tasks")) {
			taskList.listTasks();
			return true;
		}

		if (command.equals("help")) {
			ui.printCommands();
			return true;
		}

        // check number of argument
        if (splitInput.length != 2) {
			ui.printErrorMessage("Unknown command.");
			return true;
        }

        switch (command) {
            case "mark":
            case "unmark":
				handleMarkUnmark(command, splitInput, taskList, ui);
				break;

            case "delete":
				handleDelete(splitInput, taskList, ui);
                break;

            case "todo":
				handleTodo(splitInput, taskList, ui);
                break;

            case "deadline":
				handleDeadline(splitInput, taskList, ui);
                break;

            case "event":
				handleEvent(splitInput, taskList, ui);
                break;

			case "find":
				String keyword = splitInput[1];
				taskList.findTasks(keyword);
				break;

            default:
                ui.printErrorMessage("Unknown command: " + command);
        }

        handleSaveTasks(taskList, storage);
		return true;
    }

	private static void handleMarkUnmark(String command, String[] splitInput, TaskList taskList, Ui ui) {
		int index; 

		// check 2nd argument
		try {
			index = Integer.parseInt(splitInput[1]) - 1;
		} catch (NumberFormatException e) {
			ui.printErrorMessage("Invalid format! Use 'mark <task_index>' or 'unmark <task_index>'");
			return;
		}

		// check index out of bound
		if (index < 0 || index > taskList.getSize() - 1) {
			ui.printErrorMessage("invalid task index, pls enter a index from 1 to " + taskList.getSize());
			return;
		}

		// mark
		if (command.equals("mark")) {
			Task updatedTask = taskList.markTask(index);
			ui.printMessage("ive marked the following task!");
			ui.printMessage(String.format("  %d: %s", index+1, updatedTask));
			return;
		}

		// unmark
		Task updatedTask = taskList.unmarkTask(index);
		ui.printMessage("ive unmarked the following task!");
		ui.printMessage(String.format("  %d: %s", index+1, updatedTask));
		return;
	}

	private static void handleDelete(String[] splitInput, TaskList taskList, Ui ui) {
		int index;

		// check 2nd argument
		try {
			index = Integer.parseInt(splitInput[1]) - 1;
		} catch (NumberFormatException e) {
			ui.printErrorMessage("Invalid format: Use 'delete <task_index>'");
			return;
		}

		// check index out of bound
		if (index < 0 || index > taskList.getSize() - 1) {
			ui.printErrorMessage("Index out of bound: pls enter a index from 1 to " + taskList.getSize());
			return;
		}

		Task deletedTask = taskList.deleteTask(index);
        ui.printMessage("ive delete the following task!");
        ui.printMessage(String.format("  %d: %s", index+1, deletedTask));
        return;
	}

	private static void handleTodo(String[] splitInput, TaskList taskList, Ui ui) {
		// check description
        if (splitInput[1].trim().isEmpty()) {
			ui.printErrorMessage("Invalid input: description cannot be empty");
			return;
        }
		
		// update tasks
		Todo newTask = new Todo(splitInput[1]);
		taskList.addTask(newTask);
		ui.printMessage("Added a todo!");
		ui.printMessage(String.format("  %s", newTask));
		return;
	}
	
	private static void handleDeadline(String[] splitInput, TaskList taskList, Ui ui) {
		String[] deadlineParts = splitInput[1].split(" /by ", 2);

		// check number of arguments
		if (deadlineParts.length != 2) {
			ui.printErrorMessage("Invalid input: Use 'deadline <desc> /by <yyyy-MM-dd>'");
			return;
		}

		// check description
        if (deadlineParts[0].trim().isEmpty()) {
			ui.printErrorMessage("Invalid input: Description cannot be empty");
			return;
        }

		// check date format
		LocalDate dueDate = DateUtil.parseDate(deadlineParts[1]);
		if (dueDate == null) {
			return;
		}
		
		// update tasks
		Deadline newTask = new Deadline(deadlineParts[0], dueDate);
		taskList.addTask(newTask);
		ui.printMessage("added a deadline!");
		ui.printMessage(String.format("  %s", newTask));
		return;
	}
	
	private static void handleEvent(String[] splitInput, TaskList taskList, Ui ui) {
		// check positioning of /from and /to
		if (splitInput[1].indexOf(" /from ") > splitInput[1].indexOf(" /to ")) {
			ui.printErrorMessage("Invalid input: '/from' should be in front of '/to'");
			return;
		}
		
		String[] eventParts = splitInput[1].split(" /from | /to ");

		// check number of argument
		if (eventParts.length != 3) {
			ui.printErrorMessage("Invalid input: Use 'event <desc> /from <yyyy-MM-dd> /to <yyyy-MM-dd>'");
			return;
		}

		// check description
        if (eventParts[0].trim().isEmpty()) {
			ui.printErrorMessage("Invalid input: Description cannot be empty");
			return;
        }
		
		// check date format
		LocalDate fromDate = DateUtil.parseDate(eventParts[1]);
		LocalDate toDate = DateUtil.parseDate(eventParts[2]);
		if (fromDate == null || toDate == null) {
			return;
		}

		// update tasks
		Event newTask = new Event(eventParts[0], fromDate, toDate);
		taskList.addTask(newTask);
		ui.printMessage("Added an event!");
		ui.printMessage(String.format("  %s", newTask));
		return;
	}

    public static void handleSaveTasks(TaskList taskList, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        List<String> taskStrings = new ArrayList<>();

        for (Task t : tasks) {
            String taskString = t.toStorageFormat();

            if (t != null) {
                taskStrings.add(taskString);
            }
        }

        storage.saveTasks(taskStrings);
        return;
    }

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

	public static Task rawTaskStringToTask(String taskString) {
        String[] parts = taskString.split(" \\| ");

        // check number of arguments
        if (parts.length < 3) {
            return null;
        }

        boolean isDone = parts[1].equals("1");

        // Todo
        if (parts[0].equals("T")) {
            return isDone ? 
				new Todo(parts[2]).markDone() : 
				new Todo(parts[2]);
        }

        // Deadline
        if (parts[0].equals("D")) {
            LocalDate dueDate = DateUtil.parseDate(parts[3]);

            if (dueDate == null) {
                return null;
            }

            return isDone ? 
                new Deadline(parts[2], dueDate).markDone() : 
                new Deadline(parts[2], dueDate);
        }

        // Event
        LocalDate fromDate = DateUtil.parseDate(parts[3]);
        LocalDate toDate = DateUtil.parseDate(parts[4]);

        if (fromDate == null || toDate == null) {
            return null;
        }

        return isDone ? 
            new Event(parts[2], fromDate, toDate).markDone() :
            new Event(parts[2], fromDate, toDate);
	}
}
