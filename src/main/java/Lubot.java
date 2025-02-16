import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Lubot {
	private static ArrayList<Task> tasks = new ArrayList<>();
	private static int n;

    public static void main(String[] args) {
        String logo = ".____         ___.           __   \n"
            + "|    |    __ _\\_ |__   _____/  |_ \n"
            + "|    |   |  |  \\ __ \\ /  _ \\   __\\\n"
            + "|    |___|  |  / \\_\\ (  <_> )  |  \n"
            + "|_______ \\____/|___  /\\____/|__|  \n"
            + "        \\/         \\/             \n";

        String horizontalBar = "____________________________________________\n";

        // start
        System.out.println(logo);
        showCommands();
        System.out.println(horizontalBar);

        // main
        Scanner scanner = new Scanner(System.in);
        Storage.ensureFileExists();
        tasks = Storage.loadTasks();
        n = tasks.size();

        while (true) {
            Storage.saveTasks(tasks);

            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // exit
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            if (userInput.equalsIgnoreCase("help")) {
                showCommands();
				System.out.println(horizontalBar);
                continue;
            }

            // see tasks
            if (userInput.equalsIgnoreCase("tasks")) {
                System.out.println("lubot:");
                for (int i=0; i<n; i++) {
                    System.out.println(String.format("  %d: %s", i+1, tasks.get(i)));
                }
				System.out.println(horizontalBar);
                continue;
            }

			// mark or unmark task
            if (userInput.toLowerCase().startsWith("mark") || userInput.toLowerCase().startsWith("unmark")) {
				markUnmarkTask(userInput);
				System.out.println(horizontalBar);
				continue;
			}

			// delete task
            if (userInput.toLowerCase().startsWith("delete")) {
				deleteTask(userInput);
				System.out.println(horizontalBar);
				continue;
			}

			// task
			computeTask(userInput);
			System.out.println(horizontalBar);
        }

        // end
        System.out.println("lubot: pls come again");
        System.out.println(horizontalBar);
        scanner.close();
    }

    private static void showCommands() {
        System.out.println("lubot: greetings master, these are the commands available");
        System.out.println("lubot: type 'help' to see all commands");
        System.out.println("lubot: type 'tasks' to see tasks");
        System.out.println("lubot: type 'exit' to exit");
        System.out.println("lubot: type 'mark <int>' to mark a task");
        System.out.println("lubot: type 'delete <int>' to delete a task");
        System.out.println("lubot: type 'todo <description>' to add a todo");
        System.out.println("lubot: type 'deadline <description> /by <yyyy-MM-dd>' to add a deadline");
        System.out.println("lubot: type 'event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>' to add an event");
    }
	
	private static void markUnmarkTask(String userInput) {
		// format input
		int number;
		String[] userInputs = userInput.split(" ", 2);
        
        // check number of argument
        if (userInputs.length != 2) {
			System.out.println("lubot: incorrect number of argument, mark <int>");
        }
		
		// check 2nd argument
		try {
			number = Integer.parseInt(userInputs[1]) - 1;
		} catch (NumberFormatException e) {
			System.out.println("lubot: invalid input, pls type an int after mark");
			return;
		}

		if (number < 0 || number > n-1) {
			System.out.println("lubot: invalid task no, pls enter a number from 1 to " + n);
			return;
		}

		// mark task
		if (userInputs[0].equalsIgnoreCase("mark")) {
            tasks.set(number, tasks.get(number).markDone());
			System.out.println("lubot: ive marked the following task!");
			System.out.println(String.format("  %d: %s", number+1, tasks.get(number)));
			return;
		}

		// unmark task
		if (userInputs[0].equalsIgnoreCase("unmark")) {
            tasks.set(number, tasks.get(number).markUndone());
			System.out.println("lubot: ive unmarked the following task!");
			System.out.println(String.format("  %d: %s", number+1, tasks.get(number)));
			return;
		}
	}

	private static void deleteTask(String userInput) {
		// format input
		int number;
		String[] userInputs = userInput.split(" ", 2);
        
        // check number of argument
        if (userInputs.length != 2) {
			System.out.println("lubot: incorrect number of arguments, delete <int>");
        }
		
		// check 2nd argument
		try {
			number = Integer.parseInt(userInputs[1]) - 1;
		} catch (NumberFormatException e) {
			System.out.println("lubot: invalid input, pls type an int after delete");
			return;
		}

		if (number < 0 || number > n-1) {
			System.out.println("lubot: invalid task no, pls enter a number from 1 to " + n);
			return;
		}

		// delete task
        Task deletedTask = tasks.get(number);
        tasks.remove(number);
        n--;
        System.out.println("lubot: ive delete the following task!");
        System.out.println(String.format("  %d: %s", number+1, deletedTask));
        return;
	}

	private static void computeTask(String userInput) {
		// format input
		String[] temp = userInput.split(" ", 2);
		
		if (temp.length < 2) {
			System.out.println("lubot: invalid input");
			return;
		}

        if (temp[1].trim().isEmpty()) {
			System.out.println("lubot: description cannot be empty");
			return;
        }
		
		if (temp[0].equalsIgnoreCase("todo")){
            // update tasks
            tasks.add(new Todo(temp[1]));
			n++;

			System.out.println("lubot: added a todo!");
			System.out.println(String.format("  %s", tasks.get(n-1)));
			return;
		}

		if (temp[0].equalsIgnoreCase("deadline")){
			// format input
			String[] userInputs = temp[1].split(" /by ", 2);

			if (userInputs.length != 2) {
				System.out.println("lubot: invalid input, deadline <desc> /by <yyyy-MM-dd>");
				return;
			}

            LocalDate dueDate = DateUtil.formatUserInputDate(userInputs[1]);
            if (dueDate == null) {
                return;
            }

            // update tasks
            tasks.add(new Deadline(userInputs[0], dueDate));
			n++;

			System.out.println("lubot: added a deadline!");
			System.out.println(String.format("  %s", tasks.get(n-1)));
			return;
		}

		if (temp[0].equalsIgnoreCase("event")){
			if (temp[1].indexOf(" /from ") > temp[1].indexOf(" /to ")) {
				System.out.println("lubot: invalid input, event <desc> /from <date> /to <date>");
				return;
			}

			// format input
			String[] userInputs = temp[1].split(" /from | /to ", 3);

			if (userInputs.length != 3) {
				System.out.println("lubot: invalid input, event <desc> /from <date> /to <date>");
				return;
			}

            LocalDate fromDate = DateUtil.formatUserInputDate(userInputs[1]);
            LocalDate toDate = DateUtil.formatUserInputDate(userInputs[2]);

            if (fromDate == null || toDate == null) {
                return;
            }

            // update tasks
            tasks.add(new Event(userInputs[0], fromDate, toDate));
			n++;

			System.out.println("lubot: added an event!");
			System.out.println(String.format("  %s", tasks.get(n-1)));
			return;
		}

		// invalid input
		System.out.println("lubot: invalid input");
	}
}

