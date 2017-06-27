package saci.android.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import saci.android.ChangeActivity;
import saci.android.CustomPreferences;
import saci.android.R;
import saci.android.dtos.Oauth2Response;
import saci.android.dtos.UserDto;
import saci.android.register.RegisterAccountActivity;
import saci.android.colors.ColorsActivity;

public class LoginActivity extends AppCompatActivity implements ChangeActivity {

    private LoginController loginController;

    private Button loginButton;
    private TextView createAccount;
    private EditText mEmail;
    private EditText mPassword;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        preferences = this.getApplicationContext().getSharedPreferences("saci.android", Context.MODE_PRIVATE);

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
                setCredentials();
                loginController.verifyCredentials(onLoginSuccess());
            }
        });
    }

    private void setCredentials() {
        UserDto userDto = new UserDto();

        userDto.setUsername(String.valueOf(mEmail.getText()));
        userDto.setPassword(String.valueOf(mPassword.getText()));

        loginController = new LoginController(this, userDto);
    }

    private Callback<Oauth2Response> onLoginSuccess() {
        return new Callback<Oauth2Response>() {
            @Override
            public void onResponse(retrofit2.Call<Oauth2Response> call, Response<Oauth2Response> response) {
                if (response.body() != null) {
                    Intent loginIntent = new Intent(LoginActivity.this, ColorsActivity.class);
                    startActivity(loginIntent);

                    preferences.edit().putString(CustomPreferences.ACCESS_TOKEN, response.body().getAccessToken()).apply();
                    preferences.edit().putString(CustomPreferences.REFRESH_TOKEN, response.body().getRefreshToken()).apply();
                    loginController.fetchUser();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Oauth2Response> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Invalid user!", Toast.LENGTH_LONG).show();
            }
        };
    }
}
