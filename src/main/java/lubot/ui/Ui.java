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
    public String printWelcomeMessage() {
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
        return logo
            + "lubot: greetings master, how can i be of service today?\n"
            + printCommands()
            + printHorizontalBar();
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
    public String printHorizontalBar() {
        String horizontalBar = "____________________________________________";

        System.out.println(horizontalBar);
        return horizontalBar + "\n";
    }

    /**
     * Prints a message.
     *
     * @param message The message to be printed.
     */
    public String printMessage(String message) {
        System.out.println("lubot: " + message);
        return "lubot: " + message + "\n";
    }

    /**
     * Prints an error message.
     *
     * @param message The error message to be printed.
     */
    public String printErrorMessage(String message) {
        System.out.println("lubot: error! " + message);
        return "lubot: error! " + message + "\n";
    }

    /**
     * Prints the list of available commands.
     */
    public String printCommands() {
        String commands = "lubot: these are the commands available:\n"
            + "\thelp - to see all commands\n"
            + "\ttasks - to see all tasks\n"
            + "\texit - to exit\n"
            + "\tmark <int> - to mark a task\n"
            + "\tunmark <int> - to unmark a task\n"
            + "\tdelete <int> - to delete a task\n"
            + "\ttodo <description> - to add a todo\n"
            + "\tdeadline <description> /by <yyyy-MM-dd> - to add a deadline\n"
            + "\tevent <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd> - to add an event\n";

        System.out.println(commands);
        return commands;
    }

    /**
     * Prints the exit message.
     */
    public String printExitMessage() {
        System.out.println("lubot: pls come again!");
        return "lubot: pls come again!\n";
    }

    /**
     * Closes the Scanner.
     */
    public void close() {
        scanner.close();
    }
}
