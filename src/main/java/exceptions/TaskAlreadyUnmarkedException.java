package exceptions;

public class TaskAlreadyUnmarkedException extends DukeException {
    public TaskAlreadyUnmarkedException(String message) {
        super(message);
    }
}
