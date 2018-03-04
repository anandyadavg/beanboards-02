package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
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
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LandingPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String UserAction;
    WebView wBview;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        SharedPreferences navSession = getSharedPreferences("ÜserAction", Context.MODE_PRIVATE);
        UserAction = navSession.getString("userTheme", null);

        setTitle(UserAction);

        progress = (ProgressBar)findViewById(R.id.progressBar);
        progress.setMax(100);
        progress.setVisibility(View.VISIBLE);

        wBview = (WebView)findViewById(R.id.wBview);
        WebSettings webSettings = wBview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wBview.addJavascriptInterface(new Object(){
            @JavascriptInterface
            public void navigateHome(String toast) {
                if(toast.equals("home"))
                {
                    startActivity(new Intent(LandingPage.this, MainActivity.class));
                }
                else if(toast.contains("peek"))
                {
                    Toast.makeText(LandingPage.this, toast, Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LandingPage.this, BoardsView.class));
                }
                return;
            }
        }, "Android");
        String url = "https://bbm-staging-194118.appspot.com/MyBoards?module="+UserAction.toString();
        //String url = "http://10.0.2.2:3000/MyBoards?module="+UserAction.toString();

        wBview.loadUrl(url);
        //wBview.postUrl(url);

        wBview.setWebViewClient(new MyAppWebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);

                findViewById(R.id.wBview).setVisibility(View.VISIBLE);
            }
            public void onReceivedError(final WebView view, int errorCode, String description,
                                        final String failingUrl) {
                //control you layout, show something like a retry button, and
                //call view.loadUrl(failingUrl) to reload.
                wBview.loadUrl("about:blank");
                Toast.makeText(LandingPage.this, "Please connect to internet",Toast.LENGTH_LONG).show();
                //startActivity(new Intent(getContext(), NoInternet.class));
                //super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        //Toast.makeText(this, UserAction, Toast.LENGTH_SHORT).show();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
