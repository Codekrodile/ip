package lubot.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility class for handling date parsing and formatting.
 */
public class DateUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Parses a date string into a LocalDate object.
     *
     * @param dateString The date string in "yyyy-MM-dd" format.
     * @return The parsed LocalDate object, or null if parsing fails.
     */
    public static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Lubot: Invalid date format! Use 'yyyy-MM-dd'");
            return null;
        }
    }
}
