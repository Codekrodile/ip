package lubot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as completed.
     *
     * @param index The index of the task to mark.
     * @return The updated task.
     */
    public Task markTask(int index) {
        tasks.set(index, tasks.get(index).markDone());
        return tasks.get(index);
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index The index of the task to unmark.
     * @return The updated task.
     */
    public Task unmarkTask(int index) {
        tasks.set(index, tasks.get(index).markUndone());
        return tasks.get(index);
    }

    /**
     * Prints all tasks in the list.
     */
    public String listTasks() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("  %d: %s", i + 1, tasks.get(i)));
            s += String.format("  %d: %s\n", i + 1, tasks.get(i));
        }

        return s;
    }

    /**
     * Find tasks from a keyword.
     *
     * @param keyword The keyword to search.
     */
    public String findTasks(String keyword) {
        List<String> matchingTasks = new ArrayList<>();

        // check each task description if it contains the keyword
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                matchingTasks.add(String.format("%d: %s", i + 1, tasks.get(i).toString()));
            }
        }

        // print output
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
            return "No matching tasks found.";
        }

        String s = "Here are the matching tasks in your list:\n";
        System.out.println("Here are the matching tasks in your list:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println(matchingTasks.get(i));
            s += matchingTasks.get(i) + "\n";
        }

        return s;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
