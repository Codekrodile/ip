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
                writer.write(task.toFileFormat() + "\n");
                ;
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
