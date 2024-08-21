import java.util.ArrayList;
import java.util.Scanner;

public class Mario {
    public static void main(String[] args) {
        String logo = "-----------------------------------------------\n"
                + "It's-a me, Mario! \n"
                + "How can I help you today?\n"
                + "-----------------------------------------------";

        String goodbye = "-----------------------------------------------\n"
                + "     Buh-bye, see you soon!\n"
                + "-----------------------------------------------\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String input;
        TaskList lst = new TaskList();
        String output = "";

        do {
            input = scanner.nextLine();
            String command = input.split(" ")[0];
            if (input.equals("list")) {
                int len = lst.length();
                if (len == 0) {
                    output = "     There's nothing planned yet!\n";
                } else {
                    output = "     Here you go:\n";
                    for (int i = 0; i < len; i++) {
                        String status;
                        int order = i + 1;

                        output += "     " + order + ". "
                                + lst.getTask(order).getName() + "\n";
                    }
                }
            } else if (command.equals("mark") || command.equals("unmark") || command.equals("remove")) {
                if (input.split("\\s+").length == 1) {
                    output = "     Mamma mia! The task number cannot be empty, let me know which task to remove!\n";
                } else {
                    String second = input.split("\\s+")[1];
                    int order;
                    try {
                        order = Integer.parseInt(second);
                    } catch (NumberFormatException e) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Please enter a valid integer number!\n"
                                + "-----------------------------------------------");
                        continue;
                    }
                    int lstLen = lst.length();
                    if (order < 1) {
                        output = "     Oh no, that is not a valid number!\n";
                    } else if (order > lstLen) {
                        output = "     Oh no, there's only " + lstLen + " item(s) on the list!\n";

                    } else {
                        if (command.equals("mark")) {
                            lst.markCompleted(order);

                            output = "     Okey-dokey! I've marked this task as done: \n"
                                    + "       " + lst.getTask(order).getName() + "\n";
                        } else if (command.equals("unmark")) {
                            lst.markUncompleted(order);
                            output = "     Okey-dokey! I've marked this task as not done yet: \n"
                                    + "        " + lst.getTask(order).getName() + "\n";
                        } else {
                            output = "     Okey-dokey! I've removed this task: \n"
                                    +  "        " + lst.getTask(order).getName() + "\n"
                                    + "     Now you have " + lst.length() + " task(s) in your list!\n";
                            lst.removeTask(order);
                        }
                    }
                }

            } else if (command.equals("todo")) {
                if (input.split("\\s+").length == 1) {
                    output = "     Mamma mia! The todo name cannot be empty!\n";
                } else {
                    String name = input.substring(5);
                    ToDo todo = new ToDo(name);
                    lst.addTask(todo);
                    output = "     All right! I've added this task: \n"
                            + "        " + todo.getName() + "\n"
                            + "     Now you have " + lst.length() + " task(s) in your list!\n";
                }
            } else if (command.equals("deadline")) {
                if (input.split("\\s+").length == 1) {
                    output = "     Mamma mia! The deadline name cannot be empty!\n";
                } else {
                    String info = input.substring(9);
                    String name;
                    String deadline;
                    try {
                        name = info.split("/by")[0];
                        deadline = info.split("/by")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! There's no deadline.\n"
                                + "     Add one with the /by keyword!\n"
                                + "-----------------------------------------------\n");
                        continue;
                    }
                    Deadline deadlineTask = new Deadline(name, deadline);
                    lst.addTask(deadlineTask);
                    output = "     All right! I've added this task: \n"
                            + "        " + deadlineTask.getName() + "\n"
                            + "     Now you have " + lst.length() + " task(s) in your list!\n";
                }
            } else if (command.equals("event")) {
                if (input.split("\\s+").length == 1) {
                    output = "     Mamma mia! The event name cannot be empty!\n";
                } else {
                    String info;
                    String name;
                    String duration;
                    String start;
                    String end;
                    try {
                        info = input.substring(6);
                        name = info.split("/from")[0];
                        duration = info.split("/from")[1];
                        start = duration.split("/to")[0];
                        end = duration.split("/to")[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! There's no start and/or end time.\n"
                                + "     Add them with the /from and /to keywords!\n"
                                + "-----------------------------------------------\n");
                        continue;
                    }
                    Event event = new Event(name, start, end);
                    lst.addTask(event);
                    output = "     All right! I've added this task: \n"
                            + "        " + event.getName() + "\n"
                            + "     Now you have " + lst.length() + " task(s) in your list!\n";

                }
            }
            else if (!input.equals("bye") && !input.equals("Bye")) {
                 output = "     Mamma mia! I don't know what that means :( \n";
            }

            System.out.println("-----------------------------------------------\n"
                    + output
                    + "-----------------------------------------------");
            output = "";
        }
        while (!input.equals("bye") && !input.equals("Bye"));

        System.out.println(goodbye);
        scanner.close();

    }
}
