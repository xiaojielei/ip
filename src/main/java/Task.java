public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = "[ ] " + description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String toString() {
        String changedIcon = "[" + getStatusIcon() + "]";
        String result;
        if (getStatusIcon().equals("X"))
            result = description.replace("[ ]", changedIcon);
        else
            result = description.replace("[X]", changedIcon);
        return result;
    }

}
