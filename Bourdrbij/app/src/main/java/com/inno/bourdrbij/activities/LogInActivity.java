package com.inno.bourdrbij.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import org.json.*;
import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Profile;
import com.inno.bourdrbij.servercommunication.HTTPManager;
import com.inno.bourdrbij.servercommunication.HTTPRestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;


import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.client.methods.HttpPatch;

public class LogInActivity extends Activity {

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
        EditText etEmail = (EditText) findViewById(R.id.et_email);
        EditText etPassword = (EditText) findViewById(R.id.et_password);

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
                //Intent i = new Intent(LogInActivity.this, MainActivity.class);
                //startActivity(i);

               Profile test = (Profile) HTTPManager.doGet("profile/13");
                try{
                    Thread.sleep(3000);
                    System.out.println("JEUW:" + test.getId());
                }catch(Exception ex){

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
