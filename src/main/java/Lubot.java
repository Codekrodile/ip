import java.util.Scanner;

public class Lubot {
    public static void main(String[] args) {
        String logo = ".____         ___.           __   \n"
        + "|    |    __ _\\_ |__   _____/  |_ \n"
        + "|    |   |  |  \\ __ \\ /  _ \\   __\\\n"
        + "|    |___|  |  / \\_\\ (  <_> )  |  \n"
        + "|_______ \\____/|___  /\\____/|__|  \n"
        + "        \\/         \\/             \n";

        String horizontalBar = "____________________________________________\n";
        Task[] list = new Task[100];
        int n = 0; // task list index

        // start
        System.out.println(logo);
        System.out.println("lubot: greetings master, how can i be of service today?");
        System.out.println("lubot: type 'list' to see list");
        System.out.println("lubot: type 'exit' to exit");
        System.out.println("lubot: type 'mark <int>' to mark a task");
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

            // mark task
            if (userInput.toLowerCase().startsWith("mark") && !userInput.equalsIgnoreCase("mark")) {
                // check if second argument is int
                int number = Integer.parseInt(userInput.split(" ")[1]) - 1;

                // check number
                if (number < 0 || number > n-1) {
                    System.out.println("lubot: invalid task no, pls enter a number from 1 to " + n);
                } else {
                    list[number] = list[number].markDone();
                    System.out.println("lubot: ive marked the following task a done!");
                    System.out.println(String.format("  %d: %s", number+1, list[number]));
                }
                System.out.println(horizontalBar);
                continue;
            }

            // unmark task
            if (userInput.toLowerCase().startsWith("unmark") && !userInput.equalsIgnoreCase("unmark")) {
                // check if second argument is int
                int number = Integer.parseInt(userInput.split(" ")[1]) - 1;

                // check number
                if (number < 0 || number > n-1) {
                    System.out.println("lubot: invalid task no, pls enter a number from 1 to " + n);
                } else {
                    list[number] = list[number].markUndone();
                    System.out.println("lubot: ive marked the following task as undone!");
                    System.out.println(String.format("  %d: %s", number+1, list[number]));
                }
                System.out.println(horizontalBar);
                continue;
            }

            // response
            list[n] = new Task(userInput);
            n++;
            System.out.println("lubot: added '" + list[n-1] + "'");
            System.out.println(horizontalBar);
        }

        // end
        System.out.println("lubot: pls come again");
        System.out.println(horizontalBar);

    }
}



