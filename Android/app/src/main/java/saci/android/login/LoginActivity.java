package saci.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.register.RegisterAccountActivity;
import saci.android.colors.ColorsActivity;

public class LoginActivity extends AppCompatActivity implements ChangeActivity {

    private LoginController loginController;

    private Button loginButton;
    private TextView createAccount;
    private EditText emailText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginController = new LoginController();

        loginButton = (Button) findViewById(R.id.create);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        createAccount = (TextView) findViewById(R.id.account);

        createAccount();
        changeActivity();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//         Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
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
                if (loginController.verifyCredentials(emailText.getText().toString(), passwordText.getText().toString())) {
                    Intent loginIntent = new Intent(LoginActivity.this, ColorsActivity.class);
                    startActivity(loginIntent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid user!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
