package shaiytan.tssaclient.model;

/**
 * Created by Shaiytan on 05.07.2017.
 */

public class AuthResult {
    private boolean success;
    private String token;

    public AuthResult(boolean success, String token) {
        this.success = success;
        this.token = token;
    }

    public boolean isSuccessful() {
        return success;
    }

    public String getAuthToken() {
        return token;
    }
}
