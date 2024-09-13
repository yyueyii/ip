package Mario;

import Mario.Tasks.Deadline;
import Mario.Tasks.Event;
import Mario.Tasks.Task;
import Mario.Tasks.Todo;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Mario {

    /** Task List */
    private static TaskList lst;
    /** Parser that interprets user input */
    private Parser parser = new Parser(this);
    /** Ui which formats chatbot responses */
    private static final Ui ui = new Ui();
    /** Stores task history */
    private static final Storage storage = new Storage();

    public Mario() throws IOException {
        if(storage.loadTasks() == null || storage.loadTasks().isEmpty()) {
            lst = new TaskList();
        } else {
            lst = new TaskList((ArrayList<Task>) storage.loadTasks());
        }
    }

    public static void main(String[] args) throws IOException {
        Mario mario = new Mario();
        mario.run();
    }

    /**
     * Executes the main loop of the chatbot.
     * Prints the welcome message and continuously sends user input to Parser
     *
     * @throws IOException
     */
    public void run() throws IOException {
        String logo = ui.getLine()
                + "It's-a me, Mario! \n"
                + "How can I help you today?\n"
                + ui.getLine();

        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            parser.parseCommand((input));

        }
        while (!input.equals("bye") && !input.equals("Bye"));

        scanner.close();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws IOException {
        return parser.parseCommand(input);
    }

    /**
     * Returns a formatted list of tasks.
     */
    public static String listTasks() {
        int len = lst.length();
        if (len == 0) {
            return "There's nothing planned yet!";
        }
        String reply = "Here you go:\n";
        for (int i = 0; i < len; i++) {
            int order = i + 1;
            reply += "      " + order + ". "
                    + lst.getTask(order).getName() + "\n";
        }
        return reply;

    }

    /**
     * Adds a todo task to the Task list
     * @param name of task
     * @throws IOException
     */
    public static String addTodoTask(String name) throws IOException {
        Todo todo = new Todo(name);
        lst.addTask(todo);
        saveToStorage(lst);
        return "All right! I've added this task: \n"
                + "        " + todo.getName() + "\n"
                + "     Now you have " + lst.length()
                + " task(s) in your list!\n";
    }


    /**
     * Adds a deadline task to the task list.
     * @param name
     * @param deadline
     * @throws IOException
     */
    public static String addDeadlineTask(String name, String deadline) throws IOException {
        Deadline dl = new Deadline(name, deadline);
        lst.addTask(dl);
        saveToStorage(lst);
        return "All right! I've added this task: \n"
                + "        " + dl.getName() + "\n"
                + "     Now you have " + lst.length()
                        + " task(s) in your list!\n";
    }

    /**
     * Adds an event task to the task list.
     * @param start Starting time of the event.
     * @param end Ending time of the event.
     */
    public static String addEventTask(String name, String start, String end) throws IOException {
        Event event = new Event(name, start, end);
        lst.addTask(event);
        saveToStorage(lst);
        return "All right! I've added this task: \n"
                + "        " + event.getName() + "\n"
                + "     Now you have " + lst.length()
                + " task(s) in your list!\n";
    }

    public int getNumTasks() {
        return this.lst.length();
    }

    /**
     * Marks task as completed.
     * @param num Index of the task on the task list, starting from 1.
     */
    public String markTask(int num) throws IOException {
        lst.markCompleted(num);
        saveToStorage(lst);
        return "Okey-dokey! I've marked this task as done: \n"
                + "       " + lst.getTask(num).getName() + "\n";
    }


    /**
     * Unmarks task as completed.
     * @param num Index of the task on the task list, starting from 1.
     * @throws IOException If num is invalid.
     */
    public String unmarkTask(int num) throws IOException {
        lst.markUncompleted(num);
        saveToStorage(lst);
        return "Okey-dokey! I've marked this task as not done yet: \n"
                + "        " + lst.getTask(num).getName() + "\n";
    }

    /**
     * Removes task from the task list.
     * @param num Index of the task on the task list, starting from 1.
     * @throws IOException If num is invalid.
     */
    public String removeTask(int num) throws IOException {
        String response = "Okey-dokey! I've removed this task: \n"
                + "        " + lst.getTask(num).getName() + "\n"
                + "     Now you have " + (lst.length()-1)
                        + " task(s) in your list!\n";
        lst.removeTask(num);
        saveToStorage(lst);
        return response;
    }

    /**
     * Prints the goodbye message and ends the programme.
     */
    public String bye() {
        return "Buh-bye! See you soon!" ;
    }

    /**
     * Updates storage to contain the latest task list information.
     * @param lst List of tasks in the task list
     */
    public static void saveToStorage(TaskList lst) throws IOException {
        storage.saveTasks(lst);
    }

    /**
     * Displays tasks with the keyword in their name.
     *
     */
    public String findTask(String keyword) {
        List<Task> res = lst.getAllTasks().stream()
                                        .filter(task -> task.getName().contains(keyword))
                                        .collect(Collectors.toList());

        if (res.isEmpty()) {
            return "Oh no! There's no such task.";
        }
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < res.size(); i++) {
            Task task = res.get(i);
            int id = i + 1;
            response += id +  ". " + task.getName() + "\n";
        }
        return response;
    }

}
