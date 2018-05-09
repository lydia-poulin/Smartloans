package com.example.user.smartloans.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.smartloans.Fragment.HistoryFragment;
import com.example.user.smartloans.Fragment.HomeFragment;
import com.example.user.smartloans.Fragment.TransferFragment;
import com.example.user.smartloans.R;
import com.example.user.smartloans.Fragment.WalletFragment;

public class SideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);




        initViews();
}

    private void initViews() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.side, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_settings) {
            Intent intent = new Intent(SideActivity.this,ProfileActivity.class);
         startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
         getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HomeFragment()).commit();
            // Handle the camera action
        } else if (id == R.id.nav_scan) {
//         getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new ScanFragment()).commit();
            Intent intent = new Intent(SideActivity.this, ScanActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {

            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new HistoryFragment()).commit();

        }else if (id == R.id.nav_moneytransfer) {

           getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new TransferFragment()).commit();




        }

         else if (id == R.id.nav_settings) {
            Intent intent = new Intent(SideActivity.this,SettingActivity.class);
            startActivity(intent);
//            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new PayhisFragment()).commit();

        }
//        else if (id == R.id.nav_transferhistory) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new TrahisFragment()).commit();
//
//        }
//        else if (id == R.id.nav_accbalance) {
//
////            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new BalanceFragment()).commit();
//
//            Intent intent = new Intent(SideActivity.this,BalanceActivity.class);
//            startActivity(intent);
//
//
//        } else if (id == R.id.nav_w2w) {
//
//            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,new WalletFragment()).commit();
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
