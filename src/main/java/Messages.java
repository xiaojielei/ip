public class Messages {
    public static final String LINE_SEPARATOR = "----------------------------------------------";

    public static final String PATHNAME = "docs/duke.txt";
    public static final String DIRECTORY_PATH = "docs";
    public static final String INVALID_INDEX = "Invalid index.";

    // Error Messages
    public static final String NULL_VALUE_ERROR = "Unexpected error: Null value encountered.";
    public static final String MARK_DESCRIPTION_EMPTY = "Oops! You need to provide a description when marking a task.";
    public static final String UNMARK_DESCRIPTION_EMPTY = "Oops! You need to provide a description when unmarking a task.";
    public static final String TODO_DESCRIPTION_EMPTY = "Oops! The description of a todo cannot be empty.";
    public static final String DEADLINE_DESCRIPTION_EMPTY = "Oops! The description of a deadline cannot be empty.";
    public static final String EVENT_DESCRIPTION_EMPTY = "Oops! The description of an event cannot be empty.";
    public static final String INVALID_INPUT_ERROR = "Oops! The input is invalid, please check your command format.";
    public static final String INVALID_TASK_NUMBER = "Oops! The task number is invalid.";
    public static final String DELETE_DESCRIPTION_EMPTY = "Oops! You need to specify which task to delete.";
    public static final String MISSING_WORD_TO_FIND = "Oops! You omitted the word to find.";
    public static final String NO_MATCHING_TASKS = "No matching tasks found.";
    public static final String INVALID_EVENT_FORMAT = "Invalid event format! Use: event <description> /from <start time> /to <end time>";

    // Success Messages
    public static final String TASK_ADDED = "Got it. I've added this task:";
    public static final String TASK_REMOVED = "Noted. I've removed this task:";
    public static final String TASK_MARKED_DONE = "Nice! I've marked this task as done:";
    public static final String TASK_MARKED_NOT_DONE = "OK, I've marked this task as not done yet.";
    public static final String TASK_LIST_SIZE = "Now you have ";
    public static final String TASKS_IN_LIST = " tasks in the list.";
    public static final String MATCHING_TASKS = "Here are the matching tasks in your list:";

    // Other Messages
    public static final String EMPTY_TASK_LIST = "Your task list is empty.";
    public static final String HERE_ARE_TASKS = "Here are the tasks in your list:";
}
