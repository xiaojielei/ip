import java.util.Scanner;

public class Duke {
    private static int taskCount = 0;

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public static void exit() {
        printLine();
        String exiting = "Bye. Hope to see you again soon!";
        System.out.println(exiting);
        printLine();
    }

    public static void list(Task[] tasks) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; tasks[i] != null; i++) {
            System.out.println(i + 1 + "." + tasks[i]);
        }
        printLine();
    }

    public static void mark(String input, Task[] tasks) throws DukeException {
        printLine();
        if (input.trim().equals("mark")) {
            throw new DukeException("Oops! The description of a mark cannot be empty.");
        }
        String tmp = input.substring(5);
        int index = Integer.parseInt(tmp) - 1;
        tasks[index].markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index]);
        printLine();
    }

    public static void unmark(String input, Task[] tasks) throws DukeException {
        printLine();
        if (input.trim().equals("unmark")) {
            throw new DukeException("Oops! The description of a unmark cannot be empty.");
        }
        String tmp = input.substring(7);
        int index = Integer.parseInt(tmp) - 1;
        tasks[index].markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index]);
        printLine();
    }

    public static void todo(String input, Task[] tasks) throws DukeException {
        printLine();
        if (input.trim().equals("todo")) {
            throw new DukeException("Oops! The description of a todo cannot be empty.");
        }
        System.out.println("Got it. I've added this task:");
        tasks[taskCount++] = new Todo(input.substring(5));
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public static void deadline(String input, Task[] tasks) throws DukeException {
        printLine();
        if (input.trim().equals("deadline")) {
            throw new DukeException("Oops! The description of a deadline cannot be empty.");
        }
        System.out.println("Got it. I've added this task:");
        String[] parts = input.substring(9).split(" /by ");
        tasks[taskCount++] = new Deadline(parts[0], parts[1]);
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    public static void event(String input, Task[] tasks) throws DukeException {
        printLine();
        if (input.trim().equals("event")) {
            throw new DukeException("Oops! The description of a event cannot be empty.");
        }
        String[] parts = input.substring(6).trim().split(" /from ");
        String[] timeParts = parts[1].split(" /to ");
        tasks[taskCount++] = new Event(parts[0], timeParts[0], timeParts[1]);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

//    public static void addTask(String input, Task[] tasks) {
//        printLine();
//        tasks[taskCount++] = new Task(input);
//        System.out.println("added: " + input);
//        printLine();
//    }

    public static void main(String[] args) throws DukeException {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm XIA\n" + "What can I do for you?");

        while (true) {
            String input = in.nextLine();

            try {
                if (input.contains("bye")) {
                    exit();
                    break;
                } else if (input.contains("list")) {
                    list(tasks);
                } else if (!input.contains("un") && input.contains("mark")) {
                    mark(input, tasks);
                } else if (input.contains("unmark")) {
                   unmark(input, tasks);
                } else if (input.startsWith("todo")) {
                    todo(input, tasks);
                } else if (input.contains("deadline")) {
                   deadline(input, tasks);
                } else if (input.contains("event")) {
                    event(input, tasks);
                } else throw new DukeException("Sorry, I don't understand that command. Please try again.");
            } catch (DukeException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        in.close();
    }
}



