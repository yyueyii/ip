import java.util.ArrayList;
import java.util.Scanner;

public class Mario {
    public static void main(String[] args) {
        String logo = "-------------------------------------\n"
                + "It's-a me, Mario! \n"
                + "How can I help you today?\n"
                + "-------------------------------------\n";

        String goodbye = "-------------------------------------\n"
                + "     Buh-bye, see you soon!\n"
                + "-------------------------------------\n";
        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String input;
        TaskList lst = new TaskList();



        do {
            input = scanner.nextLine();

            if (input.equals("list")) {
                ArrayList<String> taskLst = lst.getTasks();
                int len = taskLst.size();
                if (len == 0) {
                    System.out.println("-------------------------------------\n"
                            + "     There's nothing planned yet!\n"
                            + "-------------------------------------\n");
                }
                System.out.println("-------------------------------------");
                for (int i = 0; i < len; i++) {
                    int order = i + 1;
                    System.out.println("     " + order + ". " + taskLst.get(i));
                }
                System.out.println("-------------------------------------");

            }
            else if (!input.equals("bye") && !input.equals("Bye")) {
                lst.addTask(input);

                String output = "-------------------------------------\n"
                        + "     added: " + input + "\n"
                        + "-------------------------------------\n";
                System.out.println(output);
            }

        }
        while (!input.equals("bye") && !input.equals("Bye"));

        System.out.println(goodbye);
        scanner.close();

    }
}
