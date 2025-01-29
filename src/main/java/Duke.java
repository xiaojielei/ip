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
                for (int i = 0; tasks[i] != null; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            } else {
                tasks[taskCount++] = input;
                System.out.println("added: " + input);
            }

        }

    }
}


