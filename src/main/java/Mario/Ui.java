package Mario;

public class Ui {
    private final String LINE = "-----------------------------------------------";
    private final String SPACE = "     ";

    public Ui() {

    }


    public String betweenLines(String content) {
        return LINE + "\n" + SPACE + content + "\n" + LINE;
    }

    public String getLine() {
        return this.LINE + "\n";
    }
}
