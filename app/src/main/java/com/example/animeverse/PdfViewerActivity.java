package com.example.animeverse;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;

public class PdfViewerActivity extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        pdfView = findViewById(R.id.pdfView);

        String pdfUrl = getIntent().getStringExtra("pdfUrl");
        Log.d("PdfViewerActivity", "pdfUrl: " + pdfUrl); // Add this line for debugging
        if (pdfUrl != null) {
            displayPdfFromUrl(pdfUrl);
        } else {
            // Handle the case where pdfUrl is null, e.g., show an error message or finish the activity.
        }
    }

    private void displayPdfFromUrl(String pdfUrl) {
        pdfView.fromUri(Uri.parse(pdfUrl))
                .enableSwipe(true) // Enable swipe navigation
                .swipeHorizontal(false) // Set horizontal scrolling
                .defaultPage(0)
                .load();
    }
}