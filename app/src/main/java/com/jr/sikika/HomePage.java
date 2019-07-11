package com.jr.sikika;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jr.sikika.fragments.Articles;
import com.jr.sikika.fragments.CreativeArts;
import com.jr.sikika.fragments.Drama;
import com.jr.sikika.fragments.Films;
import com.jr.sikika.fragments.StreamLive;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fragment = new Articles();
        displayFragment(fragment);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_layout);
        customizeActionBar(R.id.articles);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        customizeActionBar(id);

        if(id == R.id.articles){
            fragment = new Articles();
            displayFragment(fragment);
        }
        if(id == R.id.films){
            fragment = new Films();
            displayFragment(fragment);
        }
        if(id == R.id.drama){
            fragment = new Drama();
            displayFragment(fragment);
        }
        if(id == R.id.streamLive){
            fragment = new StreamLive();
            displayFragment(fragment);
        }
        if(id == R.id.creativeArts){
            fragment = new CreativeArts();
            displayFragment(fragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void displayFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.homePageFrame, fragment);
        fragmentTransaction.commit();
    }

    public void  customizeActionBar(int id){
        View view = getSupportActionBar().getCustomView();
        TextView title = view.findViewById(R.id.actionBarTitle);

        if(id == R.id.articles){
            title.setText(getResources().getString(R.string.articles));
        }
        if(id == R.id.films){
            title.setText(getResources().getString(R.string.film));
        }
        if(id == R.id.drama){
            title.setText(getResources().getString(R.string.drama));
        }
        if(id == R.id.streamLive){
            title.setText(getResources().getString(R.string.stream_live));
        }
        if(id == R.id.creativeArts){
            title.setText(getResources().getString(R.string.creative_arts));
        }

    }
}
