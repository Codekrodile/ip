public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(Task t) {
        this.description = t.description;
        this.isDone = t.isDone;
    }

    /*
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
    */

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%c] %s", this.isDone?'x':' ', this.description);
    }
}
