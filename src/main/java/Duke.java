import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>()

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public static void exit() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

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

    public static void mark(String input) throws DukeException {
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

    public static void unmark(String input) throws DukeException {
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

    public static void todo(String input) throws DukeException {
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

    public static void deadline(String input) throws DukeException {
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

    public static void event(String input) throws DukeException {
        printLine();
        if (input.trim().equals("event")) {
            throw new DukeException("Oops! The description of an event cannot be empty.");
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

    public static void deleteTask(String input) throws DukeException {
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

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Hello! I'm XIAO\n" + "What can I do for you?");

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
