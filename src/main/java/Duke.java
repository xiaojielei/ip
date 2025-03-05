import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

/**
 * Duke is a chatbox project that allows users to add, list, mark, unmark, delete, and find tasks.
 * It also save tasks to a file and loads them on startup.
 */
public class Duke {
    /** The list of tasks managed by Duke. */
    private static final ArrayList<Task> tasks = new ArrayList<>();
    /** The file used to persist tasks. */
    private static final File f = new File("docs/duke.txt");
    /** The directory path for the tasks file. */
    private static final String DIRECTORY_PATH = "docs";
    /** The directory object for the tasks file. */
    private static final File DIRECTORY = new File(DIRECTORY_PATH);

    /**
     * Prints a horizontal line to separate output.
     */
    private static void printLine() {
        System.out.println("----------------------------------------------");
    }

    /**
     * Prints an exit message and terminates the application.
     */
    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Lists all tasks in the task list.
     */
    public static void list() {
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
    public static void mark(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("mark")) {
            throw new DukeException("Oops! The description of a mark cannot be empty.");
        }
        int index = Integer.parseInt(input.substring(5).trim()) - 1;
        tasks.get(index).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
        printLine();
        saveTasksToFile();
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The user input containing the unmark command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public static void unmark(String input) throws DukeException, IOException {
        printLine();
        if (input.trim().equals("unmark")) {
            throw new DukeException("Oops! The description of an unmark cannot be empty.");
        }
        int index = Integer.parseInt(input.substring(7).trim()) - 1;
        tasks.get(index).markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
        printLine();
        saveTasksToFile();
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param input The user input containing the todo command and task description.
     * @throws DukeException If the task description is empty.
     * @throws IOException If an I/O error occurs.
     */
    public static void todo(String input) throws DukeException, IOException {
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
        saveTasksToFile();
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input containing the deadline command, task description, and deadline.
     * @throws DukeException If the task description or deadline is empty.
     * @throws IOException If an I/O error occurs.
     */
    public static void deadline(String input) throws DukeException, IOException {
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
        saveTasksToFile();
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input containing the event command, task description, start time, and end time.
     * @throws DukeException If the task description, start time, or end time is empty or the event format is invalid.
     * @throws IOException If an I/O error occurs.
     */
    public static void event(String input) throws DukeException, IOException {
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
        saveTasksToFile();
    }

    /**
     * Deletes a task from the task list based on the input index.
     *
     * @param input The user input containing the delete command and task index.
     * @throws DukeException If the input is invalid or the task index is out of bounds.
     * @throws IOException If an I/O error occurs.
     */
    public static void deleteTask(String input) throws DukeException, IOException {
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
        saveTasksToFile();
    }

    /**
     * Saves the current task list to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void saveTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                fw.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + System.lineSeparator());
            } else if (task instanceof Deadline) {
                fw.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
            } else if (task instanceof Event) {
                fw.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to + System.lineSeparator());
            }        }
        fw.close();
    }

    /**
     * Loads the current task list to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private static void loadTasksFromFile() throws IOException {
        if (!DIRECTORY.exists()) DIRECTORY.mkdir();
        if (!f.exists()) f.createNewFile();
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" \\| ");
            Task task;

            switch (parts[0]) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                continue;
            }
            if (parts[1].equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        scanner.close();
    }

    /**
     * Finds and lists tasks that contain the specified keyword in their description.
     *
     * @param wordToFind The keyword to search for in task descriptions.
     * @throws DukeException If no matching tasks are found.
     */
    public static void find(String wordToFind) throws DukeException {
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

    /**
     * The main method that starts the Duke application.
     * It loads tasks from a file, processes user commands, and saves tasks to a file on exit.
     *
     * @param args The command line arguments (not used).
     * @throws IOException If an I/O error occurs during file operations.
     */
    public static void main(String[] args) throws IOException {
        loadTasksFromFile();

        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm Jerry\n" + "What can I do for you?");

        while (true) {
            String input = in.nextLine();

            try {
                if (input.equals("bye")) {
                    exit();
                    saveTasksToFile();
                    break;
                } else if (input.equals("list")) {
                    list();
                } else if (input.startsWith("mark")) {
                    mark(input);
                    saveTasksToFile();
                } else if (input.startsWith("unmark")) {
                    unmark(input);
                    saveTasksToFile();
                } else if (input.startsWith("todo")) {
                    todo(input);
                    saveTasksToFile();
                } else if (input.startsWith("deadline")) {
                    deadline(input);
                    saveTasksToFile();
                } else if (input.startsWith("event")) {
                    event(input);
                    saveTasksToFile();
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                    saveTasksToFile();
                } else if (input.startsWith("find")) {
                    String wordToFind = input.substring(5);
                    find(wordToFind);
                } else {
                    throw new DukeException("Sorry, I don't understand that command. Please try again.");
                }
            } catch (DukeException | IOException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        in.close();
    }
}
