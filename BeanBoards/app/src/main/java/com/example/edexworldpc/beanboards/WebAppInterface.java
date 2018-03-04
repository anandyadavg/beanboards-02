package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Edexworld pc on 2/18/2018.
 */

public class WebAppInterface extends AppCompatActivity{
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void navigateHome(String toast) {
        if(toast.equals("home"))
        {
         Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
        return;
    }
}
