package lubot.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Copy constructor to create a new Task from an existing one.
     *
     * @param t The existing Task to copy.
     */
    public Task(Task t) {
        this.description = t.description;
        this.isDone = t.isDone;
    }

    private Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Marks the task as completed.
     *
     * @return A new Task object marked as done.
     */
    public Task markDone() {
        return new Task(this.description, true);
    }

    /**
     * Marks the task as incompleted.
     *
     * @return A new Task object marked as undone.
     */
    public Task markUndone() {
        return new Task(this.description, false);
    }

    /**
     * Converts the task to a storage format string.
     *
     * @return A formatted string representation for storage.
     */
    public String toStorageFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return The string format of the task.
     */
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "x" : " ", this.description);
    }
}
