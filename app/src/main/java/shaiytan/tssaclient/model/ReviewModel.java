package shaiytan.tssaclient.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import shaiytan.tssaclient.TSSAClientApp;

/**
 * Created by Shaiytan on 05.07.2017.
 */

public class ReviewModel {
    private ProductsAPI api;
    public ReviewModel() {
        api = TSSAClientApp.getAPI();
    }
    public List<Review> getReviews(int id)
    {
        List<Review> reviews;
        try {
            Response<Review[]> response = api.getReviews(id).execute();
            if (response.isSuccessful()) reviews = Arrays.asList(response.body());
            else reviews = Collections.emptyList();
        } catch (IOException e) {
            reviews = Collections.emptyList();
        }
        return reviews;
    }
}
