public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String input) {
        super(input);
    }

    @Override
    public String toString() {
        if (!super.toString().contains("X")) {
            return super.toString().replace("[ ][ ]", "[D][ ]") + " (by: " + by + ")";
        } else {
            return super.toString().replace("[ ]", "[D]") + " (by: " + by + ")";
        }
    }
}
