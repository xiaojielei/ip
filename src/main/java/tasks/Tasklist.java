package tasks;

import exceptions.*;
import constants.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class Tasklist {
    private static ArrayList<Task> tasks;

    public Tasklist(ArrayList<Task> tasks) {
        Tasklist.tasks = tasks;
    }

    public void printLine() {
        System.out.println(Messages.LINE_SEPARATOR);
    }

    /**
     * Lists all tasks in the task list.
     */
    public void list() {
        try {
            printLine();
            if (tasks.isEmpty()) {
                System.out.println(Messages.EMPTY_TASK_LIST);
            } else {
                System.out.println(Messages.HERE_ARE_TASKS);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
            }
            printLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Marks a task as done based on the input index.
     *
     * @param input The user input containing the mark command and task index.
     * @throws InvalidTaskIndexException If the task index is out of bounds.
     * @throws InvalidCommandException If the input format is invalid.
     * @throws InputIsNullException If the input or task list is null.
     * @throws InvalidFormatException If the mark command is missing an index.
     * @throws TaskAlreadyMarkedException If the task is already marked as done.
     */
    public void mark(String input) throws InvalidTaskIndexException, InvalidCommandException, InputIsNullException, InvalidFormatException, TaskAlreadyMarkedException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            if (input.contains(Messages.MARK_MISSING_INDEX)) {
                throw new InvalidFormatException(Messages.MARK_DESCRIPTION_EMPTY);
            }

            int index = Integer.parseInt(input.substring(Messages.SPACE_THAT_MARK_TAKES).trim()) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskIndexException(Messages.INVALID_INDEX);
            }

            if (tasks.get(index).isDone) {
                throw new TaskAlreadyMarkedException(Messages.TASK_MARKED);
            }

            tasks.get(index).markAsDone();
            System.out.println(Messages.TASK_MARKED_DONE);
            System.out.println(tasks.get(index));

            printLine();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Marks a task as not done based on the input index.
     *
     * @param input The user input containing the unmark command and task index.
     * @throws InvalidTaskIndexException If the task index is out of bounds.
     * @throws InvalidCommandException If the input format is invalid.
     * @throws InvalidFormatException If the unmark command is missing an index.
     * @throws TaskAlreadyUnmarkedException If the task is already unmarked.
     */
    public void unmark(String input) throws InvalidTaskIndexException, InvalidCommandException, InvalidFormatException, TaskAlreadyUnmarkedException {
        try {
            if (input == null || tasks == null) {
                throw new InvalidTaskIndexException(Messages.NULL_VALUE_ERROR);
            }

            if (input.contains(Messages.UNMARK_MISSING_INDEX)) {
                throw new InvalidFormatException(Messages.UNMARK_DESCRIPTION_EMPTY);
            }

            int index = Integer.parseInt(input.substring(Messages.SPACE_THAT_UNMARK_TAKES).trim()) - 1;//-1 to convert to zero based indexing

            if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskIndexException(Messages.INVALID_INDEX);
            }

            if (!tasks.get(index).isDone) {
                throw new TaskAlreadyUnmarkedException(Messages.MARK_SUCCESSFUL_OUTPUT_MESSAGE);
            }

            tasks.get(index).markAsNotDone();
            System.out.println(Messages.TASK_MARKED_NOT_DONE);
            System.out.println(tasks.get(index));
            printLine();
        } catch (RuntimeException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Adds a new tasks.Todo task to the task list.
     *
     * @param input The user input containing the todo command and task description.
     * @throws InputIsNullException If the input or task list is null.
     * @throws InvalidCommandException If the input format is invalid.
     * @throws EmptyTaskListException If the todo description is empty.
     * @throws IOException If an I/O error occurs.
     */
    public void todo(String input) throws InputIsNullException, InvalidCommandException, EmptyTaskListException, IOException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            Task newTask = new Todo(input.substring(Messages.SPACE_THAT_TODO_TAKES).trim());

            if (newTask.description.isEmpty()) {
                throw new EmptyTaskListException(Messages.TODO_DESCRIPTION_EMPTY);
            }

            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);

            printLine();
        } catch (RuntimeException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Adds a new tasks.Deadline task to the task list.
     *
     * @param input The user input containing the deadline command, task description, and deadline.
     * @throws InputIsNullException If the input or task list is null.
     * @throws InvalidFormatException If the deadline command format is invalid.
     * @throws InvalidCommandException If the input format is invalid.
     * @throws IOException If an I/O error occurs.
     */
    public void deadline(String input) throws InputIsNullException, InvalidCommandException, InvalidFormatException, IOException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            if (input.contains(Messages.DEADLINE_MISSING_DESCRIPTION)) {
                throw new InvalidFormatException(Messages.DEADLINE_DESCRIPTION_EMPTY);
            }

            if (!input.contains(Messages.DEADLINE_TIME_DESCRIPTION_SEPERATOR)) {
                throw new InvalidFormatException(Messages.INVALID_DEADLINE_FORMAT);
            }

            String[] parts = input.substring(Messages.SPACE_THAT_DEADLINE_TAKES).trim().split(Messages.DEADLINE_TIME_DESCRIPTION_SEPERATOR);

            String deadlineDescription = parts[0];
            String deadlineEndTime = parts[1];

            Task newTask = new Deadline(deadlineDescription.trim(), deadlineEndTime.trim());
            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);

            printLine();
        } catch (RuntimeException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Adds a new tasks.Event task to the task list.
     *
     * @param input The user input containing the event command, task description, start time, and end time.
     * @throws InputIsNullException If the input or task list is null.
     * @throws InvalidFormatException If the event command format is invalid.
     * @throws InvalidCommandException If the input format is invalid.
     */
    public void event(String input) throws InputIsNullException, InvalidFormatException, InvalidCommandException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            if (input.contains(Messages.EVENT_INVALID_INPUT)) {
                throw new InvalidFormatException(Messages.EVENT_DESCRIPTION_EMPTY);
            }

            if (!input.contains(Messages.EVENT_FORM_SEPERATOR) || !input.contains(Messages.EVENT_TO_SEPERATOR)) {
                throw new InvalidFormatException(Messages.INVALID_EVENT_FORMAT);
            }

            String[] parts = input.substring(Messages.SPACE_THAT_EVENT_TAKES).trim().split(Messages.EVENT_FORM_SEPERATOR);
            String[] timeParts = parts[1].split(Messages.EVENT_TO_SEPERATOR);

            String eventDescription = parts[0];
            String startTime = timeParts[0];
            String endTime = timeParts[1];

            Task newTask = new Event(eventDescription.trim(), startTime.trim(), endTime.trim());
            tasks.add(newTask);
            System.out.println(Messages.TASK_ADDED);
            System.out.println(newTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);

            printLine();
        } catch (RuntimeException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        }
    }

    /**
     * Deletes a task from the task list based on the input index.
     *
     * @param input The user input containing the delete command and task index.
     * @throws InputIsNullException If the input or task list is null.
     * @throws InvalidFormatException If the delete command format is invalid.
     * @throws InvalidCommandException If the input format is invalid.
     * @throws InvalidTaskIndexException If the task index is out of bounds.
     */
    public void deleteTask(String input) throws  InputIsNullException, InvalidFormatException, InvalidCommandException, InvalidTaskIndexException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            if (input.contains(Messages.DELETE_INVALID_INPUT)) {
                throw new InvalidCommandException(Messages.DELETE_DESCRIPTION_EMPTY);
            }

            int index = Integer.parseInt(input.substring(Messages.SPACE_THAT_DELETE_TAKES).trim()) - 1;//-1 to convert to zero based index

            if (index < 0 || index >= tasks.size()) {
                throw new InvalidTaskIndexException(Messages.INVALID_TASK_NUMBER);
            }

            Task removedTask = tasks.remove(index);
            System.out.println(Messages.TASK_REMOVED);
            System.out.println("  " + removedTask);
            System.out.println(Messages.TASK_LIST_SIZE + tasks.size() + Messages.TASKS_IN_LIST);

            printLine();
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(Messages.INVALID_TASK_NUMBER);
        }
    }

    /**
     * Finds and lists tasks that contain the specified keyword in their description.
     *
     * @param input The user input containing the find command and keyword.
     * @throws EmptyTaskListException If the task list is empty.
     * @throws InputIsNullException If the input or task list is null.
     * @throws NoMatchingTasksException If no matching tasks are found.
     * @throws InvalidFormatException If the find command is missing a keyword.
     * @throws InvalidTaskIndexException If the keyword extraction fails due to an incorrect format.
     * @throws InvalidCommandException If the input format is invalid.
     */
    public void find(String input) throws EmptyTaskListException, InputIsNullException, NoMatchingTasksException, InvalidFormatException, InvalidTaskIndexException, InvalidCommandException {
        try {
            if (input == null || tasks == null) {
                throw new InputIsNullException(Messages.NULL_VALUE_ERROR);
            }

            String wordToFind = input.substring(Messages.SPACE_THAT_FIND_TAKES);

            if (input.trim().equals("find")) {
                throw new InvalidFormatException(Messages.MISSING_WORD_TO_FIND);
            }

            if (wordToFind.isEmpty()) {
                throw new EmptyTaskListException(Messages.MISSING_WORD_TO_FIND);
            }

            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (Task task : tasks) {
                if (task.description.contains(wordToFind)) {
                    matchingTasks.add(task);
                }
            }
            if (matchingTasks.isEmpty()) {
                throw new NoMatchingTasksException(Messages.NO_MATCHING_TASKS);
            } else {
                System.out.println(Messages.MATCHING_TASKS);
                for (int i = 0; i < matchingTasks.size(); i++) {
                    System.out.println((i + 1) + "." + matchingTasks.get(i));
                }
            }

            printLine();
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException(Messages.MISSING_WORD_TO_FIND);
        } catch (RuntimeException e) {
            throw new InvalidCommandException(Messages.INVALID_INPUT_ERROR);
        }
    }
}
