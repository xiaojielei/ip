package constants;

public class Messages {
    public static final String LINE_SEPARATOR = "----------------------------------------------";

    //Constants relate to file loading and saving
    public static final String PATHNAME = "docs/duke.txt";
    public static final String DIRECTORY_PATH = "docs";
    public static final String INVALID_INDEX = "Invalid index.";

    // Error constants.Messages
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
    public static final String INVALID_DEADLINE_FORMAT = "Oops! Invalid deadline format! Use deadline <description> /by <end time>.";
    public static final String INVALID_USER_COMMAND = "Sorry, I don't understand that command. Please try again.";
    public static final String NULL_INPUT = "Input is null.";

    // Success constants.Messages
    public static final String TASK_ADDED = "Got it. I've added this task:";
    public static final String TASK_REMOVED = "Noted. I've removed this task:";
    public static final String TASK_MARKED_DONE = "Nice! I've marked this task as done:";
    public static final String TASK_MARKED_NOT_DONE = "OK, I've marked this task as not done yet.";
    public static final String TASK_LIST_SIZE = "Now you have ";
    public static final String TASKS_IN_LIST = " tasks in the list.";
    public static final String MATCHING_TASKS = "Here are the matching tasks in your list:";
    public static final String TASK_MARKED = "The task has been marked.";
    public static final String MARK_SUCCESSFUL_OUTPUT_MESSAGE = "The task is now unmarked.";

    // Other constants.Messages
    public static final String EMPTY_TASK_LIST = "Your task list is empty.";
    public static final String HERE_ARE_TASKS = "Here are the tasks in your list:";

    //Incorrect Inputs for Mark and unmark
    public static final String MARK_MISSING_INDEX = "mark \n";
    public static final String UNMARK_MISSING_INDEX = "unmark /";

    //Incorrect input and separator of tasks.Deadline
    public static final String DEADLINE_MISSING_DESCRIPTION = "deadline /";
    public static final String DEADLINE_TIME_DESCRIPTION_SEPERATOR = " /by ";

    //Incorrect inputs for event
    public static final String EVENT_INVALID_INPUT = "event /";
    public static final String EVENT_FORM_SEPERATOR = " /from ";
    public static final String EVENT_TO_SEPERATOR = " /to ";

    //Incorrect input for deleteTask
    public static final String DELETE_INVALID_INPUT = "delete /";

    //constants for trimming the user command
    public static final int SPACE_THAT_MARK_TAKES = 5;
    public static final int SPACE_THAT_UNMARK_TAKES = 7;
    public static final int SPACE_THAT_TODO_TAKES = 5;
    public static final int SPACE_THAT_DEADLINE_TAKES = 9;
    public static final int SPACE_THAT_EVENT_TAKES = 6;
    public static final int SPACE_THAT_DELETE_TAKES = 7;
    public static final int SPACE_THAT_FIND_TAKES = 5;
}
