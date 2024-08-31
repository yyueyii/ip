package Mario;

public class Ui {
    private final String LINE = "-----------------------------------------------";

    public Ui() {

    }


    public String betweenLines(String content) {
        String SPACE = "     ";
        return LINE + "\n" + SPACE + content + "\n" + LINE;
    }

    public String getLine() {
        return this.LINE + "\n";
    }
}
