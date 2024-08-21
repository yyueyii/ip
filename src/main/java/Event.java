public class Event extends Task {
    private String start;
    private String end;
    private String type;

    public Event (String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
        this.type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getName() {
        String status;
        if (isCompleted) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[E]" + status + " " + name + "(from:" + this.start + "to:" + this.end + ")";
    }

}
