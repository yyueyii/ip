import javafx.scene.web.HTMLEditorSkin;

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
                + "-----------------------------------------------";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String input;
        TaskList lst = new TaskList();

        do {
            input = scanner.nextLine();
            String commandString = input.split(" ")[0];
            CommandType command;
            try {
                 command = CommandType.fromString(commandString);
            } catch (IllegalArgumentException e) {
                System.out.println("-----------------------------------------------\n" +
                        "     Mamma mia! I don't know what that means :(\n"
                + "-----------------------------------------------");
                continue;
            }

            switch(command) {
                case LIST:
                    int len = lst.length();
                    if (len == 0) {
                        System.out.println("-----------------------------------------------\n"
                                + "     There's nothing planned yet!\n"
                                + "-----------------------------------------------");
                    } else {
                        System.out.println("-----------------------------------------------\n"
                                + "     Here you go:");

                        for (int i = 0; i < len; i++) {
                            int order = i + 1;
                            System.out.println("     " + order + ". "
                                    + lst.getTask(order).getName());
                        }
                        System.out.println("-----------------------------------------------");
                    }
                    break;

                case MARK:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! The task number cannot be empty, \n     let me know which task to mark!\n"
                                + "-----------------------------------------------");
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
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, that is not a valid number!\n"
                                    + "-----------------------------------------------");
                        } else if (order > lstLen) {
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, there's only " + lstLen + " item(s) on the list!\n"
                                    + "-----------------------------------------------");
                        } else {
                            lst.markCompleted(order);
                            System.out.println("-----------------------------------------------\n"
                                    + "     Okey-dokey! I've marked this task as done: \n"
                                    + "       " + lst.getTask(order).getName() + "\n"
                                    + "-----------------------------------------------");
                        }
                    }
                    break;
                case UNMARK:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! The task number cannot be empty, \n     let me know which task to unmark!\n"
                                + "-----------------------------------------------");
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
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, that is not a valid number!\n"
                                    + "-----------------------------------------------");
                        } else if (order > lstLen) {
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, there's only " + lstLen + " item(s) on the list!\n"
                                    + "-----------------------------------------------");

                        } else {
                            lst.markUncompleted(order);
                            System.out.println("-----------------------------------------------\n" +
                                    "     Okey-dokey! I've marked this task as not done yet: \n"
                                    + "        " + lst.getTask(order).getName() + "\n"
                                    + "-----------------------------------------------");
                        }
                    }
                    break;
                case REMOVE:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! The task number cannot be empty, \n     let me know which task to remove!\n"
                                + "-----------------------------------------------");
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
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, that is not a valid number!\n"
                                    + "-----------------------------------------------");
                        } else if (order > lstLen) {
                            System.out.println("-----------------------------------------------\n"
                                    + "     Oh no, there's only " + lstLen + " item(s) on the list!\n"
                                    + "-----------------------------------------------");
                        } else {
                            System.out.println("-----------------------------------------------\n" +
                                    "     Okey-dokey! I've removed this task: \n"
                                    + "        " + lst.getTask(order).getName() + "\n"
                                    + "     Now you have " + lst.length() + " task(s) in your list!\n"
                                    + "-----------------------------------------------");
                            lst.removeTask(order);
                        }
                    }
                    break;
                case TODO:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n" +
                                "     Mamma mia! The todo name cannot be empty!\n"
                                + "-----------------------------------------------");
                    } else {
                        String name = input.substring(5);
                        ToDo todo = new ToDo(name);
                        lst.addTask(todo);
                        System.out.println("-----------------------------------------------\n"
                                + "     All right! I've added this task: \n"
                                + "        " + todo.getName() + "\n"
                                + "     Now you have " + lst.length()
                                + " task(s) in your list!\n"
                                + "-----------------------------------------------");
                    }
                    break;
                case DEADLINE:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n" +
                                "     Mamma mia! The deadline name cannot be empty!\n"
                                + "-----------------------------------------------");
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
                                    + "-----------------------------------------------");
                            continue;
                        }
                        Deadline deadlineTask = new Deadline(name, deadline);
                        lst.addTask(deadlineTask);
                        System.out.println("-----------------------------------------------\n" +
                                "     All right! I've added this task: \n"
                                + "        " + deadlineTask.getName() + "\n"
                                + "     Now you have " + lst.length() + " task(s) in your list!\n"
                                + "-----------------------------------------------");
                    }
                    break;
                case EVENT:
                    if (input.split("\\s+").length == 1) {
                        System.out.println("-----------------------------------------------\n"
                                + "     Mamma mia! The event name cannot be empty!\n"
                                + "-----------------------------------------------");

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
                                    + "-----------------------------------------------");
                            continue;
                        }
                        Event event = new Event(name, start, end);
                        lst.addTask(event);
                        System.out.print("-----------------------------------------------\n" +
                                "     All right! I've added this task: \n"
                                + "        " + event.getName() + "\n"
                                + "     Now you have " + lst.length() + " task(s) in your list!\n"
                                + "-----------------------------------------------");

                    }
                case BYE:
                    break;
            }
        }
        while (!input.equals("bye") && !input.equals("Bye"));

        System.out.println(goodbye);
        scanner.close();

    }
}
