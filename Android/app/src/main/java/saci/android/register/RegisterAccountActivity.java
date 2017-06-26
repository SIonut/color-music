package saci.android.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.colors.ColorsActivity;

/**
 * Created by Corina on 5/25/2017.
 */
public class RegisterAccountActivity extends AppCompatActivity implements ChangeActivity {

    private RegisterAccountController registerAccountController;

    private EditText mEmail;
    private EditText mPassword;
    private Button createAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        createAccount = (Button) findViewById(R.id.create_account);

        changeActivity();
    }

    @Override
    public void changeActivity() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                credentials();

                if (registerAccountController.createAccount()) {
                    Intent createAccountIntent = new Intent(RegisterAccountActivity.this, ColorsActivity.class);
                    startActivity(createAccountIntent);
                } else {

                }
            }
        });
    }

    private void credentials() {
        JSONObject credentials = new JSONObject();

        try {
            credentials.put("username", String.valueOf(mEmail.getText()));
            credentials.put("password", String.valueOf(mPassword.getText()));

            registerAccountController = new RegisterAccountController(this, credentials);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
