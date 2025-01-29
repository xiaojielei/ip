import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
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
            } else if (!input.contains("unmark") && input.contains("mark")) {
                String index = input.substring(5);
                String replaced = tasks[Integer.parseInt(index) - 1].replace("[ ]", "[X]");
                tasks[Integer.parseInt(index) - 1] = replaced;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" "  +replaced);
            } else if (input.contains("unmark")) {
                String index = input.substring(7);
                String replaced = tasks[Integer.parseInt(index) - 1].replace("[X]", "[ ]");
                tasks[Integer.parseInt(index) - 1] = replaced;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + replaced);
            } else {
                tasks[taskCount++] = "[ ] " + input;
                System.out.println("added: " + input);
            }
        }

    }
}


