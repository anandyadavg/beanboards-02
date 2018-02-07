package com.example.edexworldpc.beanboards;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class WorkFlowFragments extends Fragment {

    WebView wBview;
    ProgressBar progress;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_flow_fragments, container, false);
    }

    @Override
    public void onViewCreated(final View gview, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        progress = (ProgressBar)gview.findViewById(R.id.progressBar);
        progress.setMax(100);
        progress.setVisibility(View.VISIBLE);

        wBview = (WebView) gview.findViewById(R.id.wBview);
        WebSettings webSettings = wBview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = "http://10.0.2.2:3000/WorkFlow";

        wBview.loadUrl(url);
        //wBview.postUrl(url);

        wBview.setWebViewClient(new MyAppWebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                progress.setVisibility(View.GONE);

                gview.findViewById(R.id.wBview).setVisibility(View.VISIBLE);
            }
            public void onReceivedError(final WebView view, int errorCode, String description,
                                        final String failingUrl) {
                //control you layout, show something like a retry button, and
                //call view.loadUrl(failingUrl) to reload.
                wBview.loadUrl("about:blank");
                Toast.makeText(getContext(), "Please connect to internet",Toast.LENGTH_LONG).show();
                //startActivity(new Intent(getContext(), NoInternet.class));
                //super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
    }

}
