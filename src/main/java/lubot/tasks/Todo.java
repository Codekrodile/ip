package lubot.tasks;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo.
     *
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    private Todo(Task t) {
        super(t);
    }

    /**
     * Marks the Todo as completed.
     *
     * @return A new Todo object marked as done.
     */
    public Todo markDone() {
        return new Todo(super.markDone());
    }

    /**
     * Marks the Todo as incompleted.
     *
     * @return A new Todo object marked as undone.
     */
    public Todo markUndone() {
        return new Todo(super.markUndone());
    }

    /**
     * Converts the Todo into a storage format string.
     *
     * @return A formatted string representation for storage.
     */
    public String toStorageFormat() {
        return String.format("T | %s", super.toStorageFormat());
    }

    /**
     * Returns a string representation of the Todo.
     *
     * @return The string format of the Todo.
     */
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
