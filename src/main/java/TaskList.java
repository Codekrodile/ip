import java.util.ArrayList;

public class TaskList {
	private ArrayList<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public Task deleteTask(int index) {
		return tasks.remove(index);
	}

	public Task markTask(int index) {
		tasks.set(index, tasks.get(index).markDone());
		return tasks.get(index);
	}

	public Task unmarkTask(int index) {
		tasks.set(index, tasks.get(index).markUndone());
		return tasks.get(index);
	}

	public void listTasks() {
		for (int i=0; i<tasks.size(); i++) {
			System.out.println(String.format("  %d: %s", i+1, tasks.get(i)));
		}
	}
	
	public int getSize() {
		return tasks.size();
	}

	public ArrayList<Task> getTasks() {
		return this.tasks;
	}
}
