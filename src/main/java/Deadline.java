public class Deadline extends Task {
    private String deadline;
    private String type;

    public Deadline (String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.type = "D";
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
        return "[D]" + status + " " + name + "(by:" + this.deadline + ")";
    }

}
