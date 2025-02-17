package lubot.tasks;

public class Task {
    private String description;
    private boolean isDone;

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

	public String toStorageFormat() {
		return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
	}

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "x" : " ", this.description);
    }
}
