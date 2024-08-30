import javafx.scene.web.HTMLEditorSkin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mario {

    private static TaskList lst;
    private Parser parser = new Parser(this);
    private static final Ui ui = new Ui();
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

    public static void listTasks() {
        int len = lst.length();
        if (len == 0) {
            System.out.println(ui.betweenLines(
                    "There's nothing panned yet!"));
        } else {
            System.out.println(ui.getLine()
                    + "     Here you go:");

            for (int i = 0; i < len; i++) {
                int order = i + 1;
                System.out.println("     " + order + ". "
                        + lst.getTask(order).getName());
            }
            System.out.println(ui.getLine());
        }
    }

    public static void addTodoTask(String name) throws IOException {
        ToDo todo = new ToDo(name);
        lst.addTask(todo);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                 "All right! I've added this task: \n"
                + "        " + todo.getName() + "\n"
                + "     Now you have " + lst.length()
                + " task(s) in your list!\n"));
    }

    public static void addDeadlineTask(String name, String deadline) throws IOException {
        Deadline dl = new Deadline(name, deadline);
        lst.addTask(dl);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + dl.getName() + "\n"
                + "     Now you have " + lst.length()
                        + " task(s) in your list!\n"));
    }

    public static void addEventTask(String name, String start, String end) throws IOException {
        Event event = new Event(name, start, end);
        lst.addTask(event);
        saveToStorage(lst);
        System.out.print(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + event.getName() + "\n"
                + "     Now you have " + lst.length() + " task(s) in your list!\n"));

    }

    public int getNumTasks() {
        return this.lst.length();
    }

    public void markTask(int num) throws IOException {
        lst.markCompleted(num);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                 "Okey-dokey! I've marked this task as done: \n"
                + "       " + lst.getTask(num).getName() + "\n"));
    }


    public void unmarkTask(int num) throws IOException {
        lst.markUncompleted(num);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've marked this task as not done yet: \n"
                + "        " + lst.getTask(num).getName() + "\n"));
    }

    public void removeTask(int num) throws IOException {
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've removed this task: \n"
                + "        " + lst.getTask(num).getName() + "\n"
                + "     Now you have " + (lst.length()-1)
                        + " task(s) in your list!\n"));
        lst.removeTask(num);
        saveToStorage(lst);

    }

    public void bye() {
        System.out.println(ui.betweenLines("Buh-bye! See you soon!" ));
    }

    public static void saveToStorage(TaskList lst) throws IOException {
        storage.saveTasks(lst);
    }

}
