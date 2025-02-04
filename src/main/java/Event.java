public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private Event(Task t, String from, String to) {
        super(t);
        this.from = from;
        this.to = to;
    }

    public Event markDone() {
        return new Event(super.markDone(), this.from, this.to);
    }

    public Event markUndone() {
        return new Event(super.markUndone(), this.from, this.to);
    }

    public String toFileFormat() {
        return String.format("E | %s | %s | %s", super.toFileFormat(), this.from, this.to);
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
