import java.io.IOException;
import java.util.ArrayList;

/// The class dealing with adding/deleting/finding a task
public class Tasklist {
    private static ArrayList<Task> tasks;

    public Tasklist(ArrayList<Task> tasks) {
        Tasklist.tasks = tasks;
    }

    public void printLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Lists all tasks in the task list.
     */
    public void list() {
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        printLine();
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The user input containing the mark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public void mark(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("mark")) {
            throw new DukeException("Oops! The description of a mark cannot be empty.");
        }
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
        printLine();
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The user input containing the unmark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public void unmark(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("unmark")) {
            throw new DukeException("Oops! The description of an unmark cannot be empty.");
        }
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        tasks.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
        printLine();
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param input The user input containing the todo command and task description.
     * @throws DukeException If the task description is empty.
     * @throws IOException If an I/O error occurs.
     */
    public void todo(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("todo")) {
            throw new DukeException("Oops! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(input.substring(5).trim());
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input containing the deadline command, task description, and deadline.
     * @throws DukeException If the task description or deadline is empty.
     * @throws IOException If an I/O error occurs.
     */
    public void deadline(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("deadline")) {
            throw new DukeException("Oops! The description of a deadline cannot be empty.");
        }
        String[] parts = input.substring(9).trim().split(" /by ");
        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input containing the event command, task description, start time, and end time.
     * @throws DukeException If the task description, start time, or end time is empty or the event format is invalid.
     * @throws IOException If an I/O error occurs.
     */
    public void event(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("event")) {
            throw new DukeException("Oops! The description of an event cannot be empty.");
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new DukeException("Invalid event format! Use: event <description> /from <start time> /to <end time>");
        }
        String[] parts = input.substring(6).trim().split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        Task newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Deletes a task from the task list based on the input index.
     *
     * @param input The user input containing the delete command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public void deleteTask(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("delete")) {
            throw new DukeException("Oops! The description of a delete cannot be empty.");
        }
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Oops! The task number is invalid.");
        }
        Task removedTask = tasks.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * Finds and lists tasks that contain the specified keyword in their description.
     *
     * @param wordToFind The keyword to search for in task descriptions.
     * @throws DukeException If no matching tasks are found.
     */
    public void find(String wordToFind) throws DukeException {
        printLine();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(wordToFind)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new DukeException("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        printLine();
    }
}
