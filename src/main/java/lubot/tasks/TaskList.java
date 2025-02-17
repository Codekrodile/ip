package lubot.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
	private List<Task> tasks;

	public TaskList() {
		this.tasks = new ArrayList<>();
	}

	public TaskList(List<Task> tasks) {
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

	public List<Task> getTasks() {
		return this.tasks;
	}
}
