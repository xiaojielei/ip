import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
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
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The user input containing the mark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     */
    public void mark(String input) throws DukeException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }
            printLine();

            if (input.contains("mark /")) {
                throw new DukeException("Oops! The description of a mark cannot be empty.");
            }
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 1 || index >= tasks.size()) {
                throw new DukeException("Invalid index.");
            }
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));
            printLine();
        } catch (NumberFormatException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. Please try again by referring to the user guide.");
        }
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The user input containing the unmark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     */
    public void unmark(String input) throws DukeException{
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }

            printLine();
            if (input.contains("unmark /")) {
                throw new DukeException("Oops! The description of an unmark cannot be empty.");
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 1 || index >= tasks.size()) {
                throw new DukeException("Invalid index.");
            }
            tasks.get(index).markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(index));
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        }
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param input The user input containing the todo command and task description.
     * @throws DukeException If the task description is empty.
     * @throws IOException If an I/O error occurs.
     */
    public void todo(String input) throws DukeException, IOException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }
            printLine();
            Task newTask = new Todo(input.substring(5).trim());
            if (newTask.description.equals("")) {
                throw new DukeException("Oops! The description of a todo cannot be empty.");
            }
            tasks.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        }
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input containing the deadline command, task description, and deadline.
     * @throws DukeException If the task description or deadline is empty.
     * @throws IOException If an I/O error occurs.
     */
    public void deadline(String input) throws DukeException, IOException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }

            printLine();
            if (input.contains("deadline /")) {
                throw new DukeException("Oops! The description of a deadline cannot be empty.");
            }
            if (!input.contains(" /by ")) {
                throw new DukeException("Invalid event format! Use: deadline /by <end time>");
            }
            String[] parts = input.substring(9).trim().split(" /by ");
            Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input containing the event command, task description, start time, and end time.
     * @throws DukeException If the task description, start time, or end time is empty or the event format is invalid.
     * @throws IOException If an I/O error occurs.
     */
    public void event(String input) throws DukeException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }

            printLine();
            if (input.contains("event /")) {
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
        } catch (RuntimeException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        }
    }

    /**
     * Deletes a task from the task list based on the input index.
     *
     * @param input The user input containing the delete command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     */
    public void deleteTask(String input) throws DukeException{
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }

            printLine();
            if (input.contains("delete /")) {
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
        } catch (NumberFormatException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Oops! Invalid task number, please check the task index.");
        }
    }

    /**
     * Finds and lists tasks that contain the specified keyword in their description.
     *
     * @param input The user input containing the find command and task description.
     * @throws DukeException If no matching tasks are found.
     */
    public void find(String input) throws DukeException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException("Unexpected error: Null value encountered.");
            }

            String wordToFind = input.substring(5);
            if (input.trim().equals("find")) {
                throw new DukeException("Oops! You omitted the word to find.");
            }
            if (wordToFind.isEmpty()) {
                throw new DukeException("Oops! You omitted the word to find.");
            }
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
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Oops! You omitted the word to find.");
        } catch (RuntimeException e) {
            throw new DukeException("Oops! You may have typed in incorrectly. PLease try again by referring to user guide.");
        } catch (DukeException e) {
            throw e;
        }
    }
}
