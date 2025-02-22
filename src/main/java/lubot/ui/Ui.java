package lubot.ui;

import java.util.Scanner;

/**
 * Handles user interaction, including input and output messages.
 */
public class Ui {
	private Scanner scanner;

    /**
     * Constructs a new Ui instance with a Scanner for user input.
     */
	public Ui() {
		this.scanner = new Scanner(System.in);
	}

    /**
     * Prints the welcome message.
     */
	public void printWelcomeMessage() {
        String logo = ".____         ___.           __   \n"
            + "|    |    __ _\\_ |__   _____/  |_ \n"
            + "|    |   |  |  \\ __ \\ /  _ \\   __\\\n"
            + "|    |___|  |  / \\_\\ (  <_> )  |  \n"
            + "|_______ \\____/|___  /\\____/|__|  \n"
            + "        \\/         \\/             \n";
		System.out.println(logo);
		System.out.println("lubot: greetings master, how can i be of service today?");
		printCommands();
		printHorizontalBar();
	}

    /**
     * Reads a command from the user.
     *
     * @return The user input string.
     */
	public String readCommand() {
		System.out.print("You: ");
		return scanner.nextLine();
	}

    /**
     * Prints a horizontal separator bar.
     */
	public void printHorizontalBar() {
        System.out.println("____________________________________________");
	}

    /**
     * Prints a message.
     *
     * @param message The message to be printed.
     */
	public void printMessage(String message) {
		System.out.println("lubot: " + message);
	}

    /**
     * Prints an error message.
     *
     * @param message The error message to be printed.
     */
	public void printErrorMessage(String message) {
		System.out.println("lubot: error! " + message);
	}

    /**
     * Prints the list of available commands.
     */
	public void printCommands() {
        System.out.println("lubot: these are the commands available:");
        System.out.println("    help - to see all commands");
        System.out.println("    tasks - to see all tasks");
        System.out.println("    exit - to exit");
        System.out.println("    mark <int> - to mark a task");
        System.out.println("    unmark <int> - to unmark a task");
        System.out.println("    delete <int> - to delete a task");
        System.out.println("    todo <description> - to add a todo");
        System.out.println("    deadline <description> /by <yyyy-MM-dd> - to add a deadline");
        System.out.println("    event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd> - to add an event");
	}

    /**
     * Prints the exit message.
     */
	public void printExitMessage() {
        System.out.println("lubot: pls come again!");
	}

    /**
     * Closes the Scanner.
     */
	public void close() {
		scanner.close();
	}
}
