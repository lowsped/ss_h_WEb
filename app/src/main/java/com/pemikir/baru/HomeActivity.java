package com.pemikir.baru;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);


        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

// Prepare the Interstitial Ad
        interstitial = new InterstitialAd(HomeActivity.this);
// Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

        interstitial.loadAd(adRequest);
// Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
            public void displayInterstitial() {
// If Ads are loaded, show Interstitial else show nothing.
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Drawerlayout switch indicator that the icon leftmost Toolbar
        ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.lbl_open, R.string.lbl_close);
        mActionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mActionBarDrawerToggle);


        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), sh1.class);
                startActivity(i);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), web.class);
                startActivity(i);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), web1.class);
                startActivity(i);
            }
        });



        // Set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                String msgString = "";
                int id = menuItem.getItemId();

                switch (menuItem.getItemId()) {
                    case R.id.navItemHome:
                        msgString = (String) menuItem.getTitle();
                        Intent intentku= new Intent(HomeActivity.this,sh1.class);
                        startActivity(intentku);
                        break;
                    case R.id.navItemProfile:
                        msgString = (String) menuItem.getTitle();
                        Intent intentk= new Intent(HomeActivity.this,test.class);
                        startActivity(intentk);
                        break;
                    case R.id.navItemInBox:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.navItemSetting:
                        msgString = (String) menuItem.getTitle();
                        break;
                    case R.id.navItemLogout:
                        msgString = (String) menuItem.getTitle();
                        break;
                }

                // Menu item clicked on, and close Drawerlayout
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                Toast.makeText(getApplicationContext(), msgString, Toast.LENGTH_LONG).show();

                return true;
            }
        });


        float scale =  getApplicationContext().getResources().getDisplayMetrics().density;
        Toast.makeText(getApplicationContext(), getDensity(scale), Toast.LENGTH_LONG).show();
    }


    private String getDensity(float scale) {
        if (scale == 0.75f)
            return "Low Density : ldpi";
        if (scale == 1.0f)
            return "Medium Density : mdpi";
        if (scale == 1.5f)
            return "High Density : hdpi";
        if (scale == 2.0f)
            return "WELCOME TO FREE SSH";
        if (scale == 3.0f)
            return "Double Extra High Density : xxhpi";
        if (scale == 4.0f)
            return "Triple Extra High Density : xxxhpi";

        else
            return "WELCOME TO FREE SSH" + scale;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void onBackPressed() {
        exit();//Pergi ke method exit
    }
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure want to Exit?")
                .setCancelable(false)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                //jika pilih no
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            Intent intentku= new Intent(HomeActivity.this,about.class);
            startActivity(intentku);
            Toast.makeText(this, "You clicked on about", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.action_search) {
            Intent intentku= new Intent(HomeActivity.this,sh1.class);
            startActivity(intentku);
            Toast.makeText(this, "You clicked on share", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

