package lubot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Event task with a due date.
 */
public class Event extends Task{
    private LocalDate fromDate;
    private LocalDate toDate;

    /**
     * Constructs a new Event.
     *
     * @param description The description of the Event.
     */
    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private Event(Task t, LocalDate fromDate, LocalDate toDate) {
        super(t);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Marks the Event as completed.
     *
     * @return A new Event object marked as done.
     */
    public Event markDone() {
        return new Event(super.markDone(), this.fromDate, this.toDate);
    }

    /**
     * Marks the Event as incompleted.
     *
     * @return A new Event object marked as undone.
     */
    public Event markUndone() {
        return new Event(super.markUndone(), this.fromDate, this.toDate);
    }

    /**
     * Converts the Event into a storage format string.
     *
     * @return A formatted string representation for storage.
     */
	public String toStorageFormat() {
		return String.format("E | %s | %s | %s", 
                super.toStorageFormat(), 
                this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

    /**
     * Returns a string representation of the Event.
     *
     * @return The string format of the Event.
     */
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", 
                super.toString(), 
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), 
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))); 
    }
}
