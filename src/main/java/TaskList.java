import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String task) {
        this.taskList.add(task);
    }

    public ArrayList<String> getTasks() {
        return this.taskList;

    }


}
