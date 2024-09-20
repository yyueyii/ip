package mario.tasks;

public class FixedDuration extends Task {
    private final String duration;
    private String type;

    public FixedDuration(String name, String duration) {
        super(name);
        this.duration = duration;
        this.type = "F";
    }

    public FixedDuration(String name, String duration, boolean isCompleted) {
        super(name, isCompleted);
        this.duration = duration;
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
        return "[F]" + status + " " + name + " (needs: " + this.duration + ")";
    }
    @Override
    public String toFileFormat() {
        int status = this.isCompleted ? 1 : 0;
        return "F " + "| " + status + " | "
                + this.name + " | " + this.duration;
    }

}
