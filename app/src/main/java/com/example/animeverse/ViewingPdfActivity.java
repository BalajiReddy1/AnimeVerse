package com.example.animeverse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;

public class ViewingPdfActivity extends AppCompatActivity {

    WebView pdfview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewing_pdf);

        pdfview = (WebView)findViewById(R.id.pdf);
        pdfview.getSettings().setJavaScriptEnabled(true);

        String part = getIntent().getStringExtra("part");
        String pdfUrl = getIntent().getStringExtra("pdfUrl");

        ProgressDialog pd = new ProgressDialog(this);

        pd.setTitle(part);
        pd.setMessage("Chapter Loading......");

        pdfview.setWebViewClient(new WebViewClient() {
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
        });
        String url = "";
        try {
            url = URLEncoder.encode(pdfUrl, "UTF-8");
        }catch (Exception ex)
        {}
        pdfview.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);

    }
}