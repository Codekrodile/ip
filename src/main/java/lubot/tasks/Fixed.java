package lubot.tasks;

/**
 * Represents a Fixed task with a duration.
 */
public class Fixed extends Task {
    private int duration;

    /**
     * Constructs a new Fixed.
     *
     * @param description The description of the Fixed.
     * @param duration The duration of the Fixed.
     */
    public Fixed(String description, int duration) {
        super(description);
        this.duration = duration;
    }

    private Fixed(Task t, int duration) {
        super(t);
        this.duration = duration;
    }

    /**
     * Marks the Fixed as completed.
     *
     * @return A new Fixed object marked as done.
     */
    public Fixed markDone() {
        return new Fixed(super.markDone(), this.duration);
    }

    /**
     * Marks the Fixed as incompleted.
     *
     * @return A new Fixed object marked as undone.
     */
    public Fixed markUndone() {
        return new Fixed(super.markUndone(), this.duration);
    }

    /**
     * Converts the Fixed into a storage format string.
     *
     * @return A formatted string representation for storage.
     */
    public String toStorageFormat() {
        return String.format("F | %s | %d",
                super.toStorageFormat(),
                this.duration);
    }

    /**
     * Returns a string representation of the Fixed.
     *
     * @return The string format of the Fixed.
     */
    public String toString() {
        return String.format("[F]%s (duration: %d hour(s))",
                super.toString(),
                this.duration);
    }
}

