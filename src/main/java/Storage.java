import java.io.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/lubot.txt";

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs(); // create data folder if it doesnt exist
            
            // write
            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(taskToString(task) + "\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private String taskToString(Task task) {
        /*
         * [T][ ] borrow book
         * [D][ ] return book  (by:  Sunday)
         * [E][x] collect angpao  (from: wed to:  thu)
         */
        String s = task.toString();

        // Todo
        if (s.charAt(1) == "T") {
            return "T | " + s.charAt(4)=='X'? 1:0 + " | " + s.substring(7);
        }

        // Deadline
        if (s.charAt(1) == "D") {
            return "D | " + s.charAt(4)=='X'? 1:0 + " | " + s.substring(7);
        }

        // Event
        return "E | " + s.charAt(4)=='X'? 1:0 + " | " + s.substring(7);
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        // check if file exists
        if (!file.exists()) {
            System.out.println("No previous tasks found. Starting fresh!");
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = stringToTask(line);

                if (task != null) {
                    tasks.add(task);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    public Task stringToTask(String s) {
        String[] parts = s.split(" \\| ");

        // check for error
        if (parts.length < 3) {
            return null;
        }

        // Todo
        if (parts[0] == 'T') {
            return new Todo(" ");
        }

        // Deadline
        if (parts[0] == 'D') {
            return new Deadline(" ", " ");
        }

        // Event
        return new Event(" ", " ");
    }

}

