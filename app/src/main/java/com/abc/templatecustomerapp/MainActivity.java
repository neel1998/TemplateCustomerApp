package com.abc.templatecustomerapp;

import android.content.Intent;
import android.os.Bundle;

import com.abc.templatecustomerapp.Model.User;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ListView listView = findViewById(R.id.items_list_view);

        User user = User.getUserInstance();
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_name)).setText("Hey, " + user.getName());
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.nav_header_email)).setText(user.getEmail());


        if (drawer != null) {
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_orders:
                        startActivity(new Intent(MainActivity.this, OrdersActivity.class));
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout:
                Network.clearUserData(this);
                startActivity(new Intent(this, LoginActivity.class));
                break;
            default:
                break;
        }
        return false;
    }
}
