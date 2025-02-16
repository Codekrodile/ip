import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter USER_INPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDate formatUserInputDate(String dateString) {
        try {
            return LocalDate.parse(dateString, USER_INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Lubot: Invalid date format! Use yyyy-MM-dd");
            return null;
        }
    }
    
    public static LocalDate formatStorageDate(String dateString) {
        try {
            return LocalDate.parse(dateString, STORAGE_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Lubot: Invalid date format! Use yyyy-MM-dd");
            return null;
        }
    }
}
