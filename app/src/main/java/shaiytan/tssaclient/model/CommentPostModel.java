package shaiytan.tssaclient.model;

import java.io.IOException;

import retrofit2.Response;
import shaiytan.tssaclient.TSSAClientApp;

/**
 * Created by Shaiytan on 06.07.2017.
 */

public class CommentPostModel {
    private ProductsAPI api;

    public CommentPostModel() {
        api= TSSAClientApp.getAPI();
    }
    public int postComment(int id,Comment data,String token) {
        int result=-1;
        try {
            Response<CommentResult> response = api.postReview(id, data, " Token " + token).execute();
            if(response.isSuccessful()) result=response.body().getReviewID();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
