class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        if (!super.toString().contains("X")) {
            return super.toString().replace("[ ][ ]", "[E][ ]") + " (from: " + from + " to: " + to + ")";
        } else {
            return super.toString().replace("[ ]", "[E]") + " (from: " + from + " to: " + to + ")";
        }
    }
}

