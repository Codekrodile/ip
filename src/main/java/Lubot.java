import java.util.Scanner;

public class Lubot {
	private static Task[] list = new Task[100];
	private static int n = 0;

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
        System.out.println("lubot: greetings master, how can i be of service today?");
        System.out.println("lubot: type 'list' to see list");
        System.out.println("lubot: type 'exit' to exit");
        System.out.println("lubot: type 'mark <int>' to mark a task");
        System.out.println("lubot: type 'todo' to add a todo");
        System.out.println("lubot: type 'deadline /by <date>' to add a deadline");
        System.out.println("lubot: type 'event /from <date> /to <date>' to add an event");
        System.out.println(horizontalBar);

        // main
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // exit
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            // see list
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("lubot:");
                for (int i=0; i<n; i++) {
                    System.out.println(String.format("  %d: %s", i+1, list[i]));
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

			// task
			computeTask(userInput);
			System.out.println(horizontalBar);
        }

        // end
        System.out.println("lubot: pls come again");
        System.out.println(horizontalBar);

    }
	
	private static void markUnmarkTask(String userInput) {
		// format input
		int number;
		String[] userInputs = userInput.split(" ", 2);
		
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
			list[number].markDone();
			System.out.println("lubot: ive marked the following task!");
			System.out.println(String.format("  %d: %s", number+1, list[number]));
			return;
		}

		// unmark task
		if (userInputs[0].equalsIgnoreCase("unmark")) {
			list[number].markUndone();
			System.out.println("lubot: ive unmarked the following task!");
			System.out.println(String.format("  %d: %s", number+1, list[number]));
			return;
		}
	}

	private static void computeTask(String userInput) {
		// format input
		String[] temp = userInput.split(" ", 2);
		
		if (temp.length < 2) {
			System.out.println("lubot: invalid input");
			return;
		}
		
		if (temp[0].equalsIgnoreCase("todo")){
			list[n] = new Todo(temp[1]);
			n++;

			System.out.println("lubot: added a todo!");
			System.out.println(String.format("  %s", list[n-1]));
			return;
		}

		if (temp[0].equalsIgnoreCase("deadline")){
			// format input
			String[] userInputs = temp[1].split("/by", 2);

			if (userInputs.length != 2) {
				System.out.println("lubot: invalid input, deadline <desc> /by <date>");
				return;
			}

			list[n] = new Deadline(userInputs[0], userInputs[1]);
			n++;

			System.out.println("lubot: added a deadline!");
			System.out.println(String.format("  %s", list[n-1]));
			return;
		}

		if (temp[0].equalsIgnoreCase("event")){
			// format input
			String[] userInputs = temp[1].split("/from | /to", 3);

			if (userInputs.length != 3) {
				System.out.println("lubot: invalid input, event <desc> /from <date> /to <date>");
				return;
			}

			list[n] = new Event(userInputs[0], userInputs[1], userInputs[2]);
			n++;

			System.out.println("lubot: added an event!");
			System.out.println(String.format("  %s", list[n-1]));
			return;
		}

		// invalid input
		System.out.println("lubot: invalid input");
	}
}



