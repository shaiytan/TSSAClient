package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public class Product {
    private int id;
    private String img;
    private String text;
    private String title;

    public Product(int id, String img, String text, String title) {
        this.id = id;
        this.img = img;
        this.text = text;
        this.title = title;
    }
    public int getId() {
        return id;
    }
    public String getImageID() {
        return img;
    }
    public String getText() {
        return text;
    }
    public String getTitle() {
        return title;
    }
}
