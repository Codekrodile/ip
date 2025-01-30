public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /*
    private Deadline(Task t, String by) {
        super(t);
        this.by = by;
    }
    */

    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
