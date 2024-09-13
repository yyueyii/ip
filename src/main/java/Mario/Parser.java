package Mario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private final Mario mario;

    public Parser(Mario mario) {
        this.mario = mario;
    }

    /**
     * Handles user inputs.
     * @param input User input.
     * @throws IOException If input is invalid.
     */
    public String parseCommand(String input) throws IOException {
        String commandString = input.split(" ")[0];
        CommandType command;
        try {
            command = CommandType.fromString(commandString);
        } catch (IllegalArgumentException e) {
            return "Oh no! I don't know what that means...";
        }


        switch (command) {
        case LIST:
            return handleListTasks();
        case EVENT:
            return handleEventTask(input);
        case TODO:
            return handleAddTodoTask(input);
        case DEADLINE:
            return handleAddDeadlineTask(input);
        case MARK:
            return handleMarkTask(input);
        case UNMARK:
            return handleUnmarkTask(input);
        case REMOVE:
            return handleRemoveTask(input);
        case BYE:
            return handleBye();
        case FIND:
            return handleFind(input);
        default:
            return handleInvalidInput();
        }
    }

    /**
     * Calls {@link Mario#listTasks()}.
     */
    public String handleListTasks() {
        return Mario.listTasks();
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addTodoTask(String)}.
     *
     * @param input
     * @return
     * @throws IOException If input is invalid
     */
    public String handleAddTodoTask(String input) throws IOException {
        String[] parts = input.split(" ", 2);
        if (parts.length == 1) {
           return "Mamma mia! Please include a valid todo name.";
        } else {
            assert parts.length > 1 : "Task name is missing";
            String taskName = parts[1];
            return Mario.addTodoTask(taskName);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addDeadlineTask(String, String)}.
     * @param input
     */
    public String handleAddDeadlineTask(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            return "Mamma mia! Please include task name and deadline!";
        }
        String content = parts[1];
        try {
            String[] deadlineParts = content.split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new ArrayIndexOutOfBoundsException();
            }

            String name = deadlineParts[0].trim();
            String deadline = deadlineParts[1].trim();
            LocalDateTime formattedDeadline = parseDateTime(deadline);

            if (formattedDeadline != null) {
                return Mario.addDeadlineTask(name, formattedDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")));
            } else {
                assert deadlineParts.length >= 3 : "Missing components";
                return Mario.addDeadlineTask(name, deadline);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Mamma mia! There's no deadline."
                    + " \nAdd one with the /by keyword!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#addEventTask(String, String, String)}.
     * @param input
     */
    public String handleEventTask(String input) {
        if (input.split("\\s+").length == 1) {
            return "Mamma mia! The event name cannot be empty!\n";

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
                start = duration.split("/to")[0].trim();

                end = duration.split("/to")[1].trim();

                LocalDateTime formattedStart = parseDateTime(start);
                LocalDateTime formattedEnd = parseDateTime(end);

                if (formattedEnd != null && formattedStart != null) {
                    return mario.addEventTask(name, formattedStart.format(
                            DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")),
                            formattedEnd.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm")));
                } else {
                    return mario.addEventTask(name, start, end);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return "Mamma mia! There's no start and/or end time.\n"
                        + "     Add them with the /from and /to keywords!\n";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#markTask(int)}.
     * @param input
     */
    public String handleMarkTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            return "Please enter a valid number!";
        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                return "Please enter an integer between 0 and "
                            + (mario.getNumTasks() + 1);
            }
            assert (number <= mario.getNumTasks()) : "Number entered out of range";
            return mario.markTask(number);
        } catch (NumberFormatException e) {
            return "Mamma mia! Please enter a valid integer.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#unmarkTask(int)}.
     * @param input
     */
    public String handleUnmarkTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            return "Please enter a valid number!";

        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                return "Please enter an integer between 0 and "
                        + (mario.getNumTasks() + 1);
            }
            assert (number <= mario.getNumTasks()) : "Number entered out of range";
            return mario.unmarkTask(number);
        } catch (NumberFormatException e) {
            return "Mamma mia! Please enter a valid integer.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if input is valid, if so calls {@link Mario#removeTask(int)}
     * @param input
     */
    public String handleRemoveTask(String input) {
        String[] n = input.split(" ");
        if (n.length != 2) {
            return "Please enter a valid number!";
        }
        try {
            int number = Integer.parseInt(n[1]);
            if (number > mario.getNumTasks() || number < 1) {
                return "Please enter an integer between 0 and "
                        + (mario.getNumTasks() + 1);
            }
            assert (number <= mario.getNumTasks()) : "Number entered out of range";
            return mario.removeTask(number);
        } catch (NumberFormatException e) {
            return "Mamma mia! Please enter a valid integer.";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Calls {@link Mario#bye()}
     */
    public String handleBye() {
        return mario.bye();
    }

    /**
     * Handles invalid input and prompts new user input.
     */
    public String handleInvalidInput() {
        return "I don't understand what that means :(";
    }

    /**
     * Converts valid date time string to type LocalDateTime.
     * @param dateTimeString In the format yyyy-MM-dd HHmm.
     * @return
     */
    public static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Calls {@link Mario#findTask(String)}.
     * @param input
     */
    public String handleFind(String input) {
        String keyword = input.split(" ", 2)[1];
        return mario.findTask(keyword);
    }

}
