package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    boolean doubleBackToExitPressedOnce=false;
    ImageView img_retrotimer, img_story_teller, img_sprint_runner, img_kanban_winner, img_product_owner;
    ImageView img_score_keeper, img_notifications, img_time_tracker, img_team;
    SharedPreferences navSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // Rotating circle popup-> bottom righthand corner
                /* Dashboard
                alerts
                notifications
                share button
                Settings
                User Profile
                email
                calendar
                 */


            }
        });
        // Establishing shared preference
        navSession = getSharedPreferences("ÜserAction", Context.MODE_PRIVATE);

        // inflating view
        img_retrotimer = (ImageView)findViewById(R.id.img_retro);
        img_notifications = (ImageView)findViewById(R.id.img_notifications);
        img_kanban_winner = (ImageView)findViewById(R.id.img_kanban_winner);
        img_product_owner = (ImageView)findViewById(R.id.img_product_owner);
        img_score_keeper = (ImageView)findViewById(R.id.img_score_keeper);
        img_sprint_runner = (ImageView)findViewById(R.id.img_sprint_runner);
        img_story_teller = (ImageView)findViewById(R.id.img_story_teller);
        img_team = (ImageView)findViewById(R.id.img_team);
        img_time_tracker = (ImageView)findViewById(R.id.img_time_tracker);

        // Setting on-click listener
        img_retrotimer.setOnClickListener(this);
        img_notifications.setOnClickListener(this);
        img_kanban_winner.setOnClickListener(this);
        img_product_owner.setOnClickListener(this);
        img_score_keeper.setOnClickListener(this);
        img_sprint_runner.setOnClickListener(this);
        img_story_teller.setOnClickListener(this);
        img_team.setOnClickListener(this);
        img_time_tracker.setOnClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_backlogs) {
            // Handle the camera action
        } else if (id == R.id.nav_kanban) {

        } else if (id == R.id.nav_backlogs) {

        } else if (id == R.id.nav_epic) {

        } else if (id == R.id.nav_retro_boards) {

        }
        else*/ if(id == R.id.nav_logout){
            // Log out
            SharedPreferences sharedPreferences = getSharedPreferences("LoginPreference", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            SharedPreferences userAction = getSharedPreferences("ÜserAction", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit_userAction = sharedPreferences.edit();
            edit_userAction.clear();
            edit_userAction.commit();

            startActivity(new Intent(this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_retro:
                SharedPreferences.Editor edit = navSession.edit();
                edit.putString("userTheme","Retro Timer");
                edit.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_notifications:
                SharedPreferences.Editor edit_img_notification = navSession.edit();
                edit_img_notification.putString("userTheme","Alerts Messenger");
                edit_img_notification.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_kanban_winner:
                SharedPreferences.Editor edit_kanban = navSession.edit();
                edit_kanban.putString("userTheme","Kanban Winner");
                edit_kanban.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_product_owner:
                SharedPreferences.Editor edit_product = navSession.edit();
                edit_product.putString("userTheme","Product Owner");
                edit_product.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_score_keeper:
                SharedPreferences.Editor edit_score = navSession.edit();
                edit_score.putString("userTheme","Scrum Master");
                edit_score.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_sprint_runner:
                SharedPreferences.Editor edit_sprint = navSession.edit();
                edit_sprint.putString("userTheme","Sprint Runner");
                edit_sprint.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_story_teller:
                SharedPreferences.Editor edit_story = navSession.edit();
                edit_story.putString("userTheme","Story Teller");
                edit_story.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_team:
                SharedPreferences.Editor edit_team = navSession.edit();
                edit_team.putString("userTheme","My Teams");
                edit_team.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
            case R.id.img_time_tracker:
                SharedPreferences.Editor edit_time_tracker = navSession.edit();
                edit_time_tracker.putString("userTheme","Time Tracker");
                edit_time_tracker.commit();
                startActivity(new Intent(this, CheckAuthentication.class));
                break;
        }
    }
}
