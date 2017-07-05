package shaiytan.tssaclient.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import shaiytan.tssaclient.R;
import shaiytan.tssaclient.model.AuthModel;
import shaiytan.tssaclient.model.AuthRequest;
import shaiytan.tssaclient.model.AuthResult;

public class AuthActivity extends AppCompatActivity {
    public static final String ACTION = "action";
    private String action;
    private EditText usernameView;
    private EditText passwordView;
    private AuthModel authModel;
    private String username;
    private String password;
    private SwitchCompat registerSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        registerSwitch = (SwitchCompat) findViewById(R.id.register_switch);
        registerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkMode();
            }
        });
        checkMode();
        authModel = new AuthModel();
    }
    private void checkMode() {
        action = registerSwitch.isChecked()?AuthModel.REGISTER:AuthModel.SIGN_IN;
        setTitle(action);
        Button submit = (Button) findViewById(R.id.sign_in);
        submit.setText(action);
        usernameView.setText("");
        passwordView.setText("");
    }

    public void onSubmit(View view) {
        username = usernameView.getText().toString();
        password = passwordView.getText().toString();
        new AsyncTask<Void,Void,AuthResult>(){
            @Override
            protected AuthResult doInBackground(Void... params) {
                if (username.isEmpty()||password.isEmpty())
                    return null;
                return authModel.submit(action, new AuthRequest(username, password));
            }

            @Override
            protected void onPostExecute(AuthResult authResult) {
                if(authResult==null || !authResult.isSuccessful())
                    Toast.makeText(AuthActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                else finishSuccess(authResult);
            }
        }.execute();
    }
    private void finishSuccess(AuthResult result)
    {
        Intent intent = new Intent();
        intent.putExtra("token",result.getAuthToken());
        intent.putExtra("username",username);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
