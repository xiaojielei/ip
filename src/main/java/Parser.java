import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Run the major flow of the whole program
 * and deals with making sense of the user command
 */
public class Parser {
    private static final ArrayList<Task> tasks = new ArrayList<>(); // The list of tasks managed by Duke.
    private static final Storage storage = new Storage();
    private static final Ui ui = new Ui();

    /**
     * Deals with the user's command
     * @throws IOException when If an I/O error occurs in Filewriter()
     */
    public void runDuke() throws IOException {
        storage.loadTasksFromFile(tasks);
        Scanner in = new Scanner(System.in);
        ui.Greet();
        Tasklist tasklist = new Tasklist(tasks);

        while (true) {
            String input = in.nextLine();
            try {
                if (input == null) {
                    throw new DukeException("Input is null.");
                }
                String[] words = input.split(" ", 2);
                String command = words[0];

                switch (command) {
                case "bye":
                    ui.exit();
                    return;
                case "list":
                    tasklist.list();
                    break;
                case "mark":
                    tasklist.mark(input);
                    break;
                case "unmark":
                    tasklist.unmark(input);
                    break;
                case "todo":
                    tasklist.todo(input);
                    break;
                case "deadline":
                    tasklist.deadline(input);
                    break;
                case "event":
                    tasklist.event(input);
                    break;
                case "delete":
                    tasklist.deleteTask(input);
                    break;
                case "find":
                    tasklist.find(input);
                    break;
                default:
                    throw new DukeException("Sorry, I don't understand that command. Please try again.");
                }
                storage.saveTasksToFile(tasks);
            } catch (DukeException | IOException e) {
                tasklist.printLine();
                System.out.println(e.getMessage());
                tasklist.printLine();
            }
        }

    }
}