import javafx.scene.web.HTMLEditorSkin;

import java.util.ArrayList;
import java.util.Scanner;

public class Mario {

    private static final TaskList lst = new TaskList();
    private Parser parser = new Parser(this);
    private static final Ui ui = new Ui();

    public Mario() {
    }
    public static void main(String[] args) {
        Mario mario = new Mario();
        mario.run();
    }
    
    public void run() {
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

    public static void addTodoTask(String name) {
        ToDo todo = new ToDo(name);
        lst.addTask(todo);
        System.out.println(ui.betweenLines(
                 "All right! I've added this task: \n"
                + "        " + todo.getName() + "\n"
                + "     Now you have " + lst.length()
                + " task(s) in your list!\n"));
    }

    public static void addDeadlineTask(String name, String deadline) {
        Deadline dl = new Deadline(name, deadline);
        lst.addTask(dl);
        System.out.println(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + dl.getName() + "\n"
                + "     Now you have " + lst.length()
                        + " task(s) in your list!\n"));
    }

    public static void addEventTask(String name, String start, String end) {
        Event event = new Event(name, start, end);
        lst.addTask(event);
        System.out.print(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + event.getName() + "\n"
                + "     Now you have " + lst.length() + " task(s) in your list!\n"));

    }

    public int getNumTasks() {
        return this.lst.length();
    }

    public void markTask(int num) {
        lst.markCompleted(num);
        System.out.println(ui.betweenLines(
                 "Okey-dokey! I've marked this task as done: \n"
                + "       " + lst.getTask(num).getName() + "\n"));
    }


    public void unmarkTask(int num) {
        lst.markUncompleted(num);
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've marked this task as not done yet: \n"
                + "        " + lst.getTask(num).getName() + "\n"));
    }

    public void removeTask(int num) {
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've removed this task: \n"
                + "        " + lst.getTask(num).getName() + "\n"
                + "     Now you have " + (lst.length()-1)
                        + " task(s) in your list!\n"));
        lst.removeTask(num);
    }

    public void bye() {
        System.out.println(ui.betweenLines("Buh-bye! See you soon!" ));
    }

}
