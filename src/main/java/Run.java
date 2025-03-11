import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    private static final ArrayList<Task> tasks = new ArrayList<>(); // The list of tasks managed by Duke.
    private static final Storage storage = new Storage();
    private static final Ui ui = new Ui();

    public Run () {
    }

    public void runDuke() throws IOException {
        storage.loadTasksFromFile(tasks);
        Scanner in = new Scanner(System.in);
        ui.Greet();
        Tasklist tasklist = new Tasklist(tasks);

        while (true) {
            String input = in.nextLine();
            try {
                if (input.equals("bye")) {
                    ui.exit();
                    break;
                } else if (input.equals("list")) tasklist.list();
                else if (input.startsWith("mark")) {
                    tasklist.mark(input);
                } else if (input.startsWith("unmark")) {
                    tasklist.unmark(input);
                } else if (input.startsWith("todo")) {
                    tasklist.todo(input);
                } else if (input.startsWith("deadline")) {
                    tasklist.deadline(input);
                } else if (input.startsWith("event")) {
                    tasklist.event(input);
                } else if (input.startsWith("delete")) {
                    tasklist.deleteTask(input);
                } else if (input.startsWith("find")) {
                    String wordToFind = input.substring(5);
                    tasklist.find(wordToFind);
                } else {
                    throw new DukeException("Sorry, I don't understand that command. Please try again.");
                }
                storage.saveTasksToFile(tasks);
            } catch (DukeException | IOException e) {
                tasklist.printLine();
                System.out.println(e.getMessage());
                tasklist.printLine();
            }
        }

        in.close();
    }

}
