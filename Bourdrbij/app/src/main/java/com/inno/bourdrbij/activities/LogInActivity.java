package com.inno.bourdrbij.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.servercommunication.HTTPManager;

public class LogInActivity extends Activity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));

        Button btRegister = (Button) findViewById(R.id.bt_register);
        Button btLogin = (Button) findViewById(R.id.bt_login);
        final EditText etEmail = (EditText) findViewById(R.id.et_email);
        final EditText etPassword = (EditText) findViewById(R.id.et_password);

        Typeface mm = Typeface.createFromAsset(getAssets(), "fonts/Metamorphous-Regular.otf");
        btLogin.setTypeface(mm);
        btRegister.setTypeface(mm);
        etEmail.setTypeface(mm);
        etPassword.setTypeface(mm);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etEmail.getText() != null && etPassword.getText() != null) {


                    Intent i = new Intent(LogInActivity.this, OwnProfileActivity.class);
                    startActivity(i);

                    // temporarily store placeholder user id for current logged in user
                    SharedPreferences sharedPreferences = getSharedPreferences("currentUser", Context.MODE_PRIVATE);
                    sharedPreferences.edit().putInt("ProfileId", 666).apply();
                } else {
                    etEmail.setError("Fill in all fields");
                    etPassword.setError("Fill in all fields");
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
