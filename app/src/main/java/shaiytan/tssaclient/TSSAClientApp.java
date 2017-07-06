package shaiytan.tssaclient;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shaiytan.tssaclient.model.SiteAPI;

/**
 * Created by Shaiytan on 04.07.2017.
 */

public class TSSAClientApp extends Application {
    private static SiteAPI api;

    public static SiteAPI getAPI() {
        return api;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SiteAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SiteAPI.class);
    }
}
