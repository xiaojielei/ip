package exceptions;

public class NoMatchingTasksException extends DukeException {
    public NoMatchingTasksException(String message) {
        super(message);
    }
}
