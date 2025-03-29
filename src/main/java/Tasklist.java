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
        System.out.println(Messages.LINE_SEPARATOR);
    }

    /**
     * Lists all tasks in the task list.
     */
    public void list() {
        try {
            printLine();
            if (tasks.isEmpty()) {
                System.out.println(Messages.EMPTY_TASK_LIST);
            } else {
                System.out.println(Messages.HERE_ARE_TASKS);
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
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }
            printLine();

            if (input.contains("mark /")) {
                throw new DukeException(Messages.MARK_DESCRIPTION_EMPTY);
            }
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 1 || index >= tasks.size()) {
                throw new DukeException(Messages.INVALID_INDEX);
            }
            tasks.get(index).markAsDone();
            System.out.println(Messages.TASK_MARKED_DONE);
            System.out.println(tasks.get(index));
            printLine();
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The user input containing the unmark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     */
    public void unmark(String input) throws DukeException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }

            printLine();
            if (input.contains("unmark /")) {
                throw new DukeException(Messages.UNMARK_DESCRIPTION_EMPTY);
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 1 || index >= tasks.size()) {
                throw new DukeException(Messages.INVALID_INDEX);
            }
            tasks.get(index).markAsNotDone();
            System.out.println(Messages.TASK_MARKED_NOT_DONE);
            System.out.println(tasks.get(index));
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
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
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }
            printLine();
            Task newTask = new Todo(input.substring(5).trim());
            if (newTask.description.equals("")) {
                throw new DukeException(Messages.TODO_DESCRIPTION_EMPTY);
            }
            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
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
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }

            printLine();
            if (input.contains("deadline /")) {
                throw new DukeException(Messages.DEADLINE_DESCRIPTION_EMPTY);
            }
            if (!input.contains(" /by ")) {
                throw new DukeException(Messages.INVALID_EVENT_FORMAT);
            }
            String[] parts = input.substring(9).trim().split(" /by ");
            Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
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
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }

            printLine();
            if (input.contains("event /")) {
                throw new DukeException(Messages.EVENT_DESCRIPTION_EMPTY);
            }
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new DukeException(Messages.INVALID_EVENT_FORMAT);
            }
            String[] parts = input.substring(6).trim().split(" /from ");
            String[] timeParts = parts[1].split(" /to ");
            Task newTask = new Event(parts[0].trim(), timeParts[0].trim(), timeParts[1].trim());
            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);
            printLine();
        } catch (RuntimeException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Deletes a task from the task list based on the input index.
     *
     * @param input The user input containing the delete command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     */
    public void deleteTask(String input) throws DukeException {
        try {
            if (input == null || tasks == null) {
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }

            printLine();
            if (input.contains("delete /")) {
                throw new DukeException(Messages.DELETE_DESCRIPTION_EMPTY);
            }
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new DukeException(Messages.INVALID_TASK_NUMBER);
            }
            Task removedTask = tasks.remove(index);
            System.out.println(Messages.TASK_REMOVED);
            System.out.println("  " + removedTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);
            printLine();
        } catch (NumberFormatException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Messages.INVALID_TASK_NUMBER);
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
                throw new DukeException(Messages.NULL_VALUE_ERROR);
            }

            String wordToFind = input.substring(5);
            if (input.trim().equals("find")) {
                throw new DukeException(Messages.MISSING_WORD_TO_FIND);
            }
            if (wordToFind.isEmpty()) {
                throw new DukeException(Messages.MISSING_WORD_TO_FIND);
            }
            printLine();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.description.contains(wordToFind)) {
                    matchingTasks.add(task);
                }
            }
            if (matchingTasks.isEmpty()) {
                throw new DukeException(Messages.NO_MATCHING_TASKS);
            } else {
                System.out.println(Messages.MATCHING_TASKS);
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println((i + 1) + "." + matchingTasks.get(i));
                }
            }
            printLine();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(Messages.MISSING_WORD_TO_FIND);
        } catch (RuntimeException e) {
            throw new DukeException(Messages.INVALID_INPUT_ERROR);
        } catch (DukeException e) {
            throw e;
        }
    }
}
