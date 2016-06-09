package com.inno.bourdrbij.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.inno.bourdrbij.R;
import com.inno.bourdrbij.adapters.EventsAdapter;
import com.inno.bourdrbij.adapters.NavigationDrawerAdapter;
import com.inno.bourdrbij.models.DrawerItem;
import com.inno.bourdrbij.models.Event;

import java.util.ArrayList;
import java.util.Date;

public class EventsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar toolbar;
    private ArrayList<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(null);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));

        setupNavigationDrawer();
        setupMockData();
    }

    private void setupMockData() {
        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event(0, new ArrayList<Integer>(), "Barbecue", "Rick thuis", "", new Date(), new Date()));
        events.add(new Event(0, new ArrayList<Integer>(), "Filmavond", "Ilse thuis", "", new Date(), new Date()));
        events.add(new Event(0, new ArrayList<Integer>(), "Nieuwe GoT", "Martin thuis", "", new Date(), new Date()));

        final EventsAdapter adapter = new EventsAdapter(this, events);
        ListView lvEvents = (ListView) findViewById(R.id.lv_events);
        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event item = adapter.getItem(position);

                Intent intent = new Intent(EventsActivity.this, EventInfoActivity.class);
                //TODO: send id to next activity
                //intent.putExtra("EventID", item.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add_event) {
            Intent i = new Intent(this, NewEventActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawer() {
        dataList = new ArrayList<>();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        addNavigationItems(dataList);

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(this, R.layout.drawer_action,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    private void selectItem(int position) {
        DrawerItem item = dataList.get(position);
        Intent i;
        switch (item.getItemText()) {
            case "Mijn profiel":
                i = new Intent(this, OwnProfileActivity.class);
                startActivity(i);
                break;
            case "Klussen":
                i = new Intent(this, JobsActivity.class);
                startActivity(i);
                break;
            case "Vrienden":
                i = new Intent(this, FriendsListActivity.class);
                startActivity(i);
                break;
            case "Evenementen":
                i = new Intent(this, EventsActivity.class);
                startActivity(i);
                break;
            case "Ontmoetingen":
                i = new Intent(this, EncounterActivity.class);
                startActivity(i);
                break;
        }

        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    private void addNavigationItems(ArrayList<DrawerItem> dataList) {
        // add navigationitems
        dataList.add(new DrawerItem("Mijn profiel", ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_24dp)));
        dataList.add(new DrawerItem("Vrienden", ContextCompat.getDrawable(this, R.drawable.ic_group_black_24dp)));
        dataList.add(new DrawerItem("Evenementen", ContextCompat.getDrawable(this, R.drawable.ic_event_black_24dp)));
        dataList.add(new DrawerItem("Klussen", ContextCompat.getDrawable(this, R.drawable.ic_format_paint_black_24dp)));
        dataList.add(new DrawerItem("Ontmoetingen", ContextCompat.getDrawable(this, R.drawable.ic_group_add_black_24dp)));
        dataList.add(new DrawerItem("Invites", ContextCompat.getDrawable(this, R.drawable.ic_email_black_24dp)));
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            selectItem(position);
        }
    }
}
