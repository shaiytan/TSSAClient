package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 05.07.2017.
 */

public class Review {
    private int id;
    private UserData created_by;
    private int rate;
    private String text;

    public Review(int id, UserData created_by, int rate, String text) {
        this.id = id;
        this.created_by = created_by;
        this.rate = rate;
        this.text = text;
    }

    public int getID() {
        return id;
    }
    public String getUser() {
        return created_by.username;
    }
    public int getRate() {
        return rate;
    }
    public String getText() {
        return text;
    }
    private class UserData {
        private String username;

        public UserData(String username) {
            this.username = username;
        }
    }
}
