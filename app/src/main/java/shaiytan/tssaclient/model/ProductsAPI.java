package shaiytan.tssaclient.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public interface ProductsAPI {
    @GET("api/products/?format=json")
    Call<Product[]> getProducts();
}
