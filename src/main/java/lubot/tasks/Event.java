package lubot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDate fromDate;
    private LocalDate toDate;

    public Event(String description, LocalDate fromDate, LocalDate toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private Event(Task t, LocalDate fromDate, LocalDate toDate) {
        super(t);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event markDone() {
        return new Event(super.markDone(), this.fromDate, this.toDate);
    }

    public Event markUndone() {
        return new Event(super.markUndone(), this.fromDate, this.toDate);
    }

	public String toStorageFormat() {
		return String.format("E | %s | %s | %s", 
                super.toStorageFormat(), 
                this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
                this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", 
                super.toString(), 
                this.fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), 
                this.toDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))); 
    }
}
