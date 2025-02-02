import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String greeting = "Hello! I'm Shew\n" + "What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String input = in.nextLine();
            if (input.contains("bye")) {
                String exiting = "Bye. Hope to see you again soon!";
                System.out.println(exiting);
                break;
            } else if (input.contains("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; tasks[i] != null; i++) {
                    System.out.println(i+1 + "." + tasks[i]);
                }
            } else if (!input.contains("un") && input.contains("mark")) {
                String tmp = input.substring(5);
                int index = Integer.parseInt(tmp) - 1;
                tasks[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index]);
            } else if (input.contains("unmark")) {
                String tmp = input.substring(7);
                int index = Integer.parseInt(tmp) - 1;
                tasks[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[index]);
            } else {
                tasks[taskCount++] = new Task(input);
                System.out.println("added: " + input);
            }
        }

    }
}


