package saci.android.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import saci.android.ChangeActivity;
import saci.android.R;
import saci.android.colors.ColorsActivity;

/**
 * Created by Corina on 5/25/2017.
 */

public class NewAccountActivity extends AppCompatActivity implements ChangeActivity {

    private NewAccountController newAccountController;

    private Button createAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);

        createAccount = (Button) findViewById(R.id.create_account);
        newAccountController = new NewAccountController();

        changeActivity();
    }

    @Override
    public void changeActivity() {
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newAccountController.createAccout()) {
                    Intent createAccountIntent = new Intent(NewAccountActivity.this, ColorsActivity.class);
                    startActivity(createAccountIntent);
                }
            }
        });
    }
}
