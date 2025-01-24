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
        String[] list = new String[100];
        int n = 0; // list index

        // start
        System.out.println(logo);
        System.out.println("lubot: greetings master, how can i be of service today?");
        System.out.println("lubot: type 'list' to see list");
        System.out.println("lubot: type 'exit' to exit");
        System.out.println(horizontalBar);

        // main
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // check if users wants to exit
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            // check if users wants to see list
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("lubot:");
                for (int i=0; i<n; i++) {
                    System.out.println("  - " + list[i]);
                }
                System.out.println(horizontalBar);
                continue;
            }

            // response
            list[n] = userInput;
            n++;
            System.out.println("lubot: added '" + userInput + "'");
            System.out.println(horizontalBar);
        }

        // end
        System.out.println("lubot: pls come again");
        System.out.println(horizontalBar);

    }
}



