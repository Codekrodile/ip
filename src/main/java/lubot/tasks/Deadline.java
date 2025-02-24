package lubot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {
    private LocalDate dueDate;

    /**
     * Constructs a new Deadline.
     *
     * @param description The description of the Deadline.
     * @param dueDate The due date of the Deadline.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    private Deadline(Task t, LocalDate dueDate) {
        super(t);
        this.dueDate = dueDate;
    }

    /**
     * Marks the Deadline as completed.
     *
     * @return A new Deadline object marked as done.
     */
    public Deadline markDone() {
        return new Deadline(super.markDone(), this.dueDate);
    }

    /**
     * Marks the Deadline as incompleted.
     *
     * @return A new Deadline object marked as undone.
     */
    public Deadline markUndone() {
        return new Deadline(super.markUndone(), this.dueDate);
    }

    /**
     * Converts the Deadline into a storage format string.
     *
     * @return A formatted string representation for storage.
     */
    public String toStorageFormat() {
        return String.format("D | %s | %s",
                super.toStorageFormat(),
                this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    /**
     * Returns a string representation of the Deadline.
     *
     * @return The string format of the Deadline.
     */
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
