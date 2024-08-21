public class ToDo extends Task {
    String type;
    public ToDo (String name) {
        super(name);
        this.type = "T";
    }

    @Override
    public String getName() {
        String status;
        if (isCompleted) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
        return "[T]" + status + " " + name;
    }

}
