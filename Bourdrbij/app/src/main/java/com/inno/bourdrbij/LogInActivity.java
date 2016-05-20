package com.inno.bourdrbij;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btRegister = (Button) findViewById(R.id.bt_register);
        Button btLogin = (Button) findViewById(R.id.bt_login);
        EditText etEmail = (EditText) findViewById(R.id.et_email);
        EditText etPassword = (EditText) findViewById(R.id.et_password);

        Typeface mm = Typeface.createFromAsset(getAssets(),"fonts/Metamorphous-Regular.otf");
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
                Intent i = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(i);
            }
        });


    }
}
