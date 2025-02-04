public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    private Deadline(Task t, String by) {
        super(t);
        this.by = by;
    }

    public Deadline markDone() {
        return new Deadline(super.markDone(), this.by);
    }

    public Deadline markUndone() {
        return new Deadline(super.markUndone(), this.by);
    }

    public String toFileFormat() {
        return String.format("D | %s | %s", super.toFileFormat(), this.by);
    }

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
