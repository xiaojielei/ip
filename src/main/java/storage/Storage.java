package storage;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import constants.*;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private static final File f = new File(Messages.PATHNAME);
    private static final String DIRECTORY_PATH = Messages.DIRECTORY_PATH;
    private static final File DIRECTORY = new File(DIRECTORY_PATH);

    public Storage() {
    }

    /**
     * Loads the current task list to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void loadTasksFromFile(ArrayList<Task> tasks) throws IOException {
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
     * Saves the current task list to the file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                fw.write("T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + System.lineSeparator());
            } else if (task instanceof Deadline) {
                fw.write("D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Deadline) task).getBy() + System.lineSeparator());
            } else if (task instanceof Event) {
                fw.write("E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + System.lineSeparator());
            }        }
        fw.close();
    }
}
