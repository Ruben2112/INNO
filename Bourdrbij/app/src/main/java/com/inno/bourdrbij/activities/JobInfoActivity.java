package com.inno.bourdrbij.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Job;
import com.inno.bourdrbij.views.MetamorphousTextView;

public class JobInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(null);

        Intent intent = getIntent();
        Job job = (Job) intent.getSerializableExtra("Job");

        MetamorphousTextView txtJobName = (MetamorphousTextView) findViewById(R.id.et_title);
        txtJobName.setText(job.getName());

        MetamorphousTextView txtJobDescription = (MetamorphousTextView) findViewById(R.id.et_description);
        txtJobDescription.setText(job.getDescription());

        MetamorphousTextView txtJobReward = (MetamorphousTextView) findViewById(R.id.et_reward);
        txtJobDescription.setText(job.getReward());

        Button btRecommend = (Button) findViewById(R.id.bt_recommend);
        Button btSendMessage = (Button) findViewById(R.id.bt_send_message);

        Typeface mm = Typeface.createFromAsset(getAssets(),"fonts/Metamorphous-Regular.otf");
        btRecommend.setTypeface(mm);
        btSendMessage.setTypeface(mm);

        btRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JobInfoActivity.this, RecommendActivity.class);
                startActivity(i);
            }
        });



        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));
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
