import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String greeting = "Hello! I'm Shew\n" + "What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String input = in.nextLine();
            if (input.contains("bye")) {
                String exiting = "Bye. Hope to see you again soon!";
                System.out.println(exiting);
                break;
            } else {
                System.out.println("    " + input);
            }
        }

    }
}


