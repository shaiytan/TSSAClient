package shaiytan.tssaclient.model;

import retrofit2.Response;
import shaiytan.tssaclient.TSSAClientApp;

/**
 * Created by Shaiytan on 05.07.2017.
 */

public class AuthModel {
    private ProductsAPI api;
    public static final String SIGN_IN = "Sign In";
    public static final String REGISTER = "Register";
    public AuthModel() {
        api = TSSAClientApp.getAPI();
    }
    public AuthResult submit(String action, AuthRequest data)
    {
        Response<AuthResult> response = null;
        try {
            if (action.equals(SIGN_IN)) {
                response = api.login(data).execute();
            }
            if (action.equals(REGISTER)){
                response = api.register(data).execute();
            }
        } catch (Exception e){
            return null;
        }
        if (response.isSuccessful()) return response.body();
        return null;
    }
}
