package parser;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import tasks.Task;
import tasks.Tasklist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import storage.Storage;
import ui.Ui;
import constants.Messages;

/**
 * Run the major flow of the whole program
 * and deals with making sense of the user command
 */
public class Parser {
    private static final ArrayList<Task> tasks = new ArrayList<>(); // The list of tasks managed by main.Duke.
    private static final Storage storage = new Storage();

    /**
     * Deals with the user's command
     * @throws IOException when If an I/O error occurs in Filewriter()
     */
    public void runDuke() throws IOException {
        storage.loadTasksFromFile(tasks);
        Scanner in = new Scanner(System.in);
        Ui UI = new Ui(in);
        UI.greet();
        Tasklist tasklist = new Tasklist(tasks);

        while (true) {
            String input = UI.getUserInput();
            try {
                if (input == null) {
                    throw new InvalidCommandException(Messages.NULL_INPUT);
                }
                String[] words = input.split(" ", 2);
                String command = words[0];

                switch (command) {
                case "bye":
                    UI.exit();
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
                    throw new InvalidCommandException(Messages.INVALID_USER_COMMAND);
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