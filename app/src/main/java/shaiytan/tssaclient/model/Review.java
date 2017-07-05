package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 05.07.2017.
 */

public class Review {
    private int id;
    private int product;
    private UserData created_by;
    private int rate;
    private String text;

    public int getID() {
        return id;
    }
    public int getProductID() {
        return product;
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
        private int id;
        private String username;
    }
}
