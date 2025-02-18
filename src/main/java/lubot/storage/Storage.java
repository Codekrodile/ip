package lubot.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class Storage {
	private final Path filePath;

	public Storage(String filePath) {
		this.filePath = Paths.get(filePath);
		ensureFileExists();
	}

    public void ensureFileExists() {
        try {
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void saveTasks(List<String> taskStrings) {
        try {
            Files.write(filePath, taskStrings);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    /*
    public void saveTasks(ArrayList<Task> tasks) {
		List<String> taskStrings = new ArrayList<>();

		for (Task t : tasks) {
			taskStrings.add(t.toStorageFormat());
		}

        try {
            Files.write(filePath, taskStrings);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    */

    public List<String> loadRawTaskData() {
        ensureFileExists();

        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return new ArrayList<>();
    }
    /*
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        ensureFileExists();

        try {
            List<String> taskStrings = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        for (String s : taskStrings) {
            Task t = Parser.storageStringToTask(s);

            if (t != null) {
                tasks.add(t);
            }
        }

        return tasks;
    }
    */
}

