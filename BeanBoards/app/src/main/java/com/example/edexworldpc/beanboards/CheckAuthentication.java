package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Edexworld pc on 2/3/2018.
 */

public class CheckAuthentication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences, navSession;
        sharedPreferences = getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE);
        String pref = sharedPreferences.getString("email", null);

        if(pref == null)
        {
            startActivity(new Intent(this, LoginActivity.class));
        }
        else
        {
         //   startActivity(new Intent(this, MainActivity.class));
            startActivity(new Intent(this, LandingPage2.class));
        }
    }
}
