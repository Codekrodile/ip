package lubot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate dueDate;

    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    private Deadline(Task t, LocalDate dueDate) {
        super(t);
        this.dueDate = dueDate;
    }

    public Deadline markDone() {
        return new Deadline(super.markDone(), this.dueDate);
    }

    public Deadline markUndone() {
        return new Deadline(super.markUndone(), this.dueDate);
    }

	public String toStorageFormat() {
		return String.format("D | %s | %s", 
                super.toStorageFormat(), 
                this.dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
	}

    public String toString() {
        return String.format("[D]%s (by: %s)", 
                super.toString(), 
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
