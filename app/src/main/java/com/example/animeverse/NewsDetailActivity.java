package com.example.animeverse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetailActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        webView = (WebView)findViewById(R.id.webview);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("News Portal");
        pd.setMessage("Loading.....");

        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view,url,favicon);
                pd.show();
            }
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view,url);
                pd.dismiss();
            }
        }
        );

        String linkdata = getIntent().getStringExtra("linkvalue");
        webView.loadUrl(linkdata);
    }
}