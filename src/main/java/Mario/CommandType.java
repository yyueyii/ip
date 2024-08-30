package Mario;

public enum CommandType {
    LIST("list"),
    EVENT("event"),
    TODO("todo"),
    DEADLINE("deadline"),
    MARK("mark"),
    UNMARK("unmark"),
    REMOVE("remove"),
    BYE("bye");

    private final String commandString;

    CommandType(String commandString) {
        this.commandString = commandString;
    }

    public String getCommandString() {
        return commandString;
    }

    public static CommandType fromString(String commandString) {
        for (CommandType command : CommandType.values()) {
            if (command.getCommandString().equals(commandString)) {
                return command;
            }
        }
        throw new IllegalArgumentException();
    }

}
