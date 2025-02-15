import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private static final Path FILE_PATH = Paths.get("./data/lubot.txt");

    public static void ensureFileExists() {
        try {
            if (Files.notExists(FILE_PATH.getParent())) {
                Files.createDirectories(FILE_PATH.getParent());
            }
            if (Files.notExists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            List<String> taskStrings = new ArrayList<>();

            for (Task t : tasks) {
                taskStrings.add(taskToString(t));
            }

            Files.write(FILE_PATH, taskStrings);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    private static String taskToString(Task task) {
        /*
         * [T][ ] borrow book
         * [D][ ] return book  (by:  Sunday)
         * [E][x] collect angpao  (from: wed to:  thu)
         */
        String taskString = task.toString();
        boolean isDone = taskString.charAt(4) == 'x';

        // Todo
        if (task instanceof Todo) {
            return String.format("T | %s | %s", 
                    isDone? "1":"0", 
                    taskString.substring(7));
        }

        // Deadline
        if (task instanceof Deadline) {
            int byIndex = taskString.indexOf(" (by: ");

            return String.format("D | %s | %s | %s", 
                    isDone? "1":"0",
                    taskString.substring(7, byIndex),
                    taskString.substring(byIndex + 6, taskString.length() - 1));
        }

        // Event
        int fromIndex = taskString.indexOf(" (from: ");
        int toIndex = taskString.indexOf(" to: ");

        return String.format("E | %s | %s | %s | %s",
                isDone? "1":"0",
                taskString.substring(7, fromIndex),
                taskString.substring(fromIndex + 8, toIndex),
                taskString.substring(toIndex + 5, taskString.length() - 1));
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        ensureFileExists();

        try {
            List<String> taskStrings = Files.readAllLines(FILE_PATH);

            for (String s : taskStrings) {
                Task t = stringToTask(s);

                if (t != null) {
                    tasks.add(t);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }

    public static Task stringToTask(String s) {
        String[] parts = s.split(" \\| ");

        // check for error
        if (parts.length < 3) {
            return null;
        }

        boolean isDone = parts[1].equals("1");

        // Todo
        if (parts[0].equals("T")) {
            if (isDone) {
                return new Todo(parts[2]).markDone();
            }
            return new Todo(parts[2]);
        }

        // Deadline
        if (parts[0].equals("D")) {
            if (isDone) {
                return new Deadline(parts[2], parts[3]).markDone();
            }
            return new Deadline(parts[2], parts[3]);
        }

        // Event
        if (isDone) {
            return new Event(parts[2], parts[3], parts[4]).markDone();
        }
        return new Event(parts[2], parts[3], parts[4]);
    }
}

