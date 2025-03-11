import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final File f = new File("docs/duke.txt");
    private static final String DIRECTORY_PATH = "docs";
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
                fw.write("T | " + (task.isDone ? "1" : "0") + " | " + task.description + System.lineSeparator());
            } else if (task instanceof Deadline) {
                fw.write("D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Deadline) task).by + System.lineSeparator());
            } else if (task instanceof Event) {
                fw.write("E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + ((Event) task).from + " | " + ((Event) task).to + System.lineSeparator());
            }        }
        fw.close();
    }
}
