package com.inno.bourdrbij.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.inno.bourdrbij.R;
import com.inno.bourdrbij.adapters.NavigationDrawerAdapter;
import com.inno.bourdrbij.models.DrawerItem;
import com.inno.bourdrbij.models.Encounter;
import com.inno.bourdrbij.views.MetamorphousTextView;

import java.util.ArrayList;

/**
 * Created by sebas on 6/3/2016.
 */
public class EncounterActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private Toolbar toolbar;
    private ArrayList<DrawerItem> dataList;
    private GoogleMap mMap;

    private Encounter encounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encounter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(null);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.statusbar));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupNavigationDrawer();

        //TODO: Encounters ophalen, encounter API bestaat nog niet

        setupViews();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (encounter != null){
            LatLng encounterLocation = new LatLng(encounter.getLatitude(), encounter.getLongitude());
            mMap.addMarker(new MarkerOptions().position(encounterLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(encounterLocation, 12));
        }
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

    private void setupViews() {
        if (encounter != null) {
            Button btnInvite = (Button) findViewById(R.id.btnInvite);
            btnInvite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    encounter.invite(encounter.getReceiverProfile().getUsername());
                }
            });
            Button btnIgnore = (Button) findViewById(R.id.btnIgnore);
            btnIgnore.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    encounter.ignore(encounter.getReceiverProfile().getUsername());
                }
            });

            MetamorphousTextView textTime = (MetamorphousTextView) findViewById(R.id.tvTimeMet);
            textTime.setText(encounter.getDate().toString());

            MetamorphousTextView encounterText = (MetamorphousTextView) findViewById(R.id.tvEncounterText);
            if (encounter.getReceiverProfile().getUsername() != null) {
                encounterText.setText(encounter.getReceiverProfile().getUsername());
            }
            ImageView profileImage = (ImageView) findViewById(R.id.ivProfileImage);
            // TODO: Set image. imagePath String in Profile moet een bytearray returnen
        }
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
}
