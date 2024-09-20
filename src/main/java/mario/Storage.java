package mario;

import mario.Tasks.*;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DIRECTORY_PATH = "./data/";
    private static final String FILE_PATH = DIRECTORY_PATH + "mario.txt";

    private void ensureDirectoryExists() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /**
     * Writes formatted tasks into the mario.txt file.
     * @param tasks
     * @throws IOException
     */
    public void saveTasks(TaskList tasks) throws IOException {
        ensureDirectoryExists();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks.getAllTasks()) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        }
    }

    /**
     * Reads the tasks from the mario.txt file.
     * @return List of tasks.
     * @throws IOException
     */
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    /**
     * Parses a single line of task and create tasks accordingly.
     * @param line
     * @return
     */
    private Task parseTaskLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            String type = parts[0];
            boolean isCompleted = parts[1].equals("1");
            String name = parts[2];

            switch (type) {
            case "T":
                return createTodoTask(name, isCompleted);
            case "D":
                String deadline = parts[3];
                return createDeadlineTask(name, deadline, isCompleted);
            case "E":
                String duration = parts[3];
                String[] splitDuration = duration.split("-");
                String start = splitDuration[0];
                String end = splitDuration[1];
                return createEventTask(name, start, end, isCompleted);
            case "F":
                String taskDuration = parts[3];
                return createFixedDurationTask(name, taskDuration, isCompleted);
            default:
                return null;
            }
        }
        return null;
    }

    private Task createTodoTask(String name, boolean isCompleted) {
        return new Todo(name, isCompleted);
    }

    private Task createDeadlineTask(String name, String deadline, boolean isCompleted) {
        return new Deadline(name, deadline, isCompleted);
    }

    private Task createEventTask(String name, String start, String end, boolean isCompleted) {
        return new Event(name, start, end, isCompleted);
    }

    private Task createFixedDurationTask(String name, String duration, boolean isCompleted) {
        return new FixedDuration(name, duration, isCompleted);
    }

}
