package Mario.Tasks;

public class Task {
    protected boolean isCompleted;
    protected String name;

    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
    }

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markUncompleted() {
        this.isCompleted = false;
    }

    public String getType() {
        return "";
    }
    public String toFileFormat() {
        return "";
    }
}
