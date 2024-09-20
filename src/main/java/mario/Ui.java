package mario;

public class Ui {
    private final String LINE = "-----------------------------------------------";

    public Ui() {

    }

    /**
     * Displays string to be between two lines.
     * @param content
     */
    public String printSeparatorLines(String content) {
        String SPACE = "     ";
        return LINE + "\n" + SPACE + content + "\n" + LINE;
    }

    public String getLine() {
        return this.LINE + "\n";
    }
}
