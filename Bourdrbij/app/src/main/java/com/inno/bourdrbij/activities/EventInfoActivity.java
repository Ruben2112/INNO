package com.inno.bourdrbij.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.models.Event;
import com.inno.bourdrbij.views.MetamorphousTextView;

public class EventInfoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(null);

        Intent intent = getIntent();
        Event event = (Event) intent.getSerializableExtra("Job");

        MetamorphousTextView txtEventName = (MetamorphousTextView) findViewById(R.id.tv_title);
        txtEventName.setText(event.getName());

        MetamorphousTextView txtEventDescription = (MetamorphousTextView) findViewById(R.id.tv_description);
        txtEventDescription.setText(event.getDescription());

        MetamorphousTextView txtEventLocation = (MetamorphousTextView) findViewById(R.id.tv_location);
        txtEventLocation.setText(event.getLocation());

        MetamorphousTextView txtEventBeginDate = (MetamorphousTextView) findViewById(R.id.tv_begindate);
        txtEventBeginDate.setText(event.getStartTime().toString());

        MetamorphousTextView txtEventEndDate = (MetamorphousTextView) findViewById(R.id.tv_enddate);
        txtEventEndDate.setText(event.getEndTime().toString());

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
