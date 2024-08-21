import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    public Task getTask(int n) {
        return this.taskList.get(n - 1);
    }

    public void markCompleted(int n) {
        Task task = this.taskList.get(n - 1);
        task.markCompleted();
    }

    public void markUncompleted(int n) {
        Task task = this.taskList.get(n - 1);
        task.markUncompleted();
    }

    public int length() {
        return this.taskList.size();
    }

    public void removeTask(int n) {
        this.taskList.remove(n - 1);
    }


}
