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

        do {
            input = scanner.nextLine();

            if (!input.equals("bye") && !input.equals("Bye")) {
                String output = "-------------------------------------\n"
                        + "     " + input + "\n"
                        + "-------------------------------------\n";
                System.out.println(output);
            }

        }
        while (!input.equals("bye") && !input.equals("Bye"));

        System.out.println(goodbye);
        scanner.close();

    }
}
