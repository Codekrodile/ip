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

        // start
        System.out.println(logo);
        System.out.println("lubot: greetings master, how can i be of service today?");
        System.out.println("lubot: type 'exit' to exit");
        System.out.println(horizontalBar);


        // echo
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();

            // check if users wants to exit
            if (userInput.equalsIgnoreCase("exit")) {
                break;
            }

            // response
            System.out.println("lubot: " + userInput);
            System.out.println(horizontalBar);
        }


        // end
        System.out.println("lubot: pls come again");
        System.out.println(horizontalBar);

    }
}



