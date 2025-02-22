package lubot.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

/**
 * Handles saving and loading task data from a file.
 */
public class Storage {
	private final Path filePath;

	/**
     * Constructs a Storage instance with the specified file path.
     *
     * @param filePath The path where tasks are stored.
     */
	public Storage(String filePath) {
		this.filePath = Paths.get(filePath);
		ensureFileExists();
	}

    /**
     * Ensures that the storage file and its parent directory exist.
     * Creates them if they do not exist.
     */
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

    /**
     * Saves the given task list data to the storage file.
     *
     * @param taskStrings A list of task representations in string format.
     */
    public void saveTasks(List<String> taskStrings) {
		if (taskStrings.isEmpty()) {
			return;
		}

        try {
            Files.write(filePath, taskStrings);
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    /**
     * Loads raw task data from the storage file.
     *
     * @return A list of task strings read from the file, or an empty list if an error occurs.
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
}
