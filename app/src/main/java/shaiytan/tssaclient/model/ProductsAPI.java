package shaiytan.tssaclient.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public interface ProductsAPI {
    @GET("api/products/?format=json")
    Call<Product[]> getProducts();
    @GET("api/reviews/{id}?format=json")
    Call<Review[]> getReviews(@Path("id")int id);
}
