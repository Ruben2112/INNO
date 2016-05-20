package com.inno.bourdrbij;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogInActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btRegister = (Button) findViewById(R.id.bt_register);
        Button btLogin = (Button) findViewById(R.id.bt_login);
        TextView tvLogin = (TextView) findViewById(R.id.tv_login);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        
        Typeface mm = Typeface.createFromAsset(getAssets(),"fonts/Metamorphous-Regular.otf");
        Typeface bs = Typeface.createFromAsset(getAssets(),"fonts/Big-Surprise.ttf");
        btLogin.setTypeface(mm);
        btRegister.setTypeface(mm);
        tvLogin.setTypeface(bs);

    }
}
