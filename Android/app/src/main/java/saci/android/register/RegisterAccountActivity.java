package saci.android.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.colors.ColorsActivity;
import saci.android.dtos.UserDto;

/**
 * Created by Corina on 5/25/2017.
 */
public class RegisterAccountActivity extends AppCompatActivity implements ChangeActivity {

    private RegisterController registerController;

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

                registerController.createAccount();
                Intent createAccountIntent = new Intent(RegisterAccountActivity.this, ColorsActivity.class);
                startActivity(createAccountIntent);
            }
        });
    }

    private void credentials() {
        UserDto userDto = new UserDto();

        userDto.setPassword(String.valueOf(mPassword.getText()));
        userDto.setUsername(String.valueOf(mEmail.getText()));

        registerController = new RegisterController(this, userDto);

    }
}
