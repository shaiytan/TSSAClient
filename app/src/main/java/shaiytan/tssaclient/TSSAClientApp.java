package shaiytan.tssaclient;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shaiytan.tssaclient.model.ProductsAPI;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public class TSSAClientApp extends Application {
    private static ProductsAPI api;

    public static ProductsAPI getAPI() {
        return api;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://smktesting.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(ProductsAPI.class);
    }
}
