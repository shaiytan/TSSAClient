package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 06.07.2017.
 */

public class Comment {
    private int rate;
    private String text;

    public Comment(int rate, String text) {
        this.rate = rate;
        this.text = text;
    }

    public int getRate() {
        return rate;
    }

    public String getCommentText() {
        return text;
    }
}
