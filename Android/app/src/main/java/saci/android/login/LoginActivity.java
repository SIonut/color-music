package saci.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.register.RegisterAccountActivity;
import saci.android.colors.ColorsActivity;
import saci.android.register.RegisterAccountController;

public class LoginActivity extends AppCompatActivity implements ChangeActivity {

    private LoginController loginController;

    private Button loginButton;
    private TextView createAccount;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginButton = (Button) findViewById(R.id.create);
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        createAccount = (TextView) findViewById(R.id.account);

        createAccount();
        changeActivity();

    }

    private void createAccount() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newAccountIntent = new Intent(LoginActivity.this, RegisterAccountActivity.class);
                startActivity(newAccountIntent);
            }
        });
    }

    @Override
    public void changeActivity() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credentials();

                if (loginController.verifyCredentials()) {
                    Intent loginIntent = new Intent(LoginActivity.this, ColorsActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void credentials() {
        JSONObject credentials = new JSONObject();

        try {
            credentials.put("username", String.valueOf(mEmail.getText()));
            credentials.put("password", String.valueOf(mPassword.getText()));

            loginController = new LoginController(this, credentials);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
