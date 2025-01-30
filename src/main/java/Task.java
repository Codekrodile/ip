public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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

    public String toString() {
        return String.format("[%c] %s", this.isDone?'x':' ', this.description);
    }
}
