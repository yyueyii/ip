public class Task {
    protected boolean isCompleted;
    protected String name;

    public Task(String name) {
        this.isCompleted = false;
        this.name = name;
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
}
