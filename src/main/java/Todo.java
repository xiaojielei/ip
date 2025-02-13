public class Todo extends Task {
    private String description;

    public Todo(String description) {
        super(description);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        if (!super.toString().contains("X")) {
            return super.toString().replace("[ ][ ]", "[T][ ]");
        } else {
            return super.toString().replace("[ ]", "[T]");
        }
    }
}
