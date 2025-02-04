public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    private Todo(Task t) {
        super(t);
    }

    public Todo markDone() {
        return new Todo(super.markDone());
    }

    public Todo markUndone() {
        return new Todo(super.markUndone());
    }

    public String toFileFormat() {
        return String.format("T | %s", super.toFileFormat());
    }

    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
