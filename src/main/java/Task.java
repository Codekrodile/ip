public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(Task t) {
        this.description = t.description;
        this.isDone = t.isDone;
    }

    private Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public Task markDone() {
        return new Task(this.description, true);
    }

    public Task markUndone() {
        return new Task(this.description, false);
    }

    public String toFileFormat() {
        return String.format("%s | %s", this.description, this.isDone);
    }

    public String toString() {
        return String.format("[%c] %s", this.isDone?'x':' ', this.description);
    }
}
