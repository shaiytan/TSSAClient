package shaiytan.tssaclient.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public interface ProductsAPI {
    @GET("api/products/?format=json")
    Call<Product[]> getProducts();
    @GET("api/reviews/{id}?format=json")
    Call<Review[]> getReviews(@Path("id")int id);
    @POST("api/register/?format=json")
    Call<AuthResult> register(@Body AuthRequest data);
    @POST("api/login/?format=json")
    Call<AuthResult> login(@Body AuthRequest data);
    @POST("api/reviews/{id}?format=json")
    Call<CommentResult> postReview(@Path("id")int id,@Body Comment data,@Header("Authorization")String token);
}
