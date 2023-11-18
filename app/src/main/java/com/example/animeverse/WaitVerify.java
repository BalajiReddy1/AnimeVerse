package com.example.animeverse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animeverse.IntroUI.SignIn;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WaitVerify extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textViewStatus;
    private Button buttonSignIn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_verify);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar);
        textViewStatus = findViewById(R.id.textViewStatus);
        buttonSignIn = findViewById(R.id.buttonSignIn);

        // Hide the "Sign In" button until verification is complete
        buttonSignIn.setVisibility(View.GONE);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if (user.isEmailVerified()) {
                        // If email is verified, redirect to the sign-in page
                        redirectToSignInPage();
                    } else {
                        // If email is not verified yet, show a toast message
                        Toast.makeText(WaitVerify.this, "Please verify your email before signing in.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        // Add AuthStateListener when the activity starts
        mAuth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        // Remove AuthStateListener when the activity stops
        mAuth.removeAuthStateListener(authStateListener);
    }

    private void redirectToSignInPage() {
        // Redirect the user to the sign-in page
        Intent intent = new Intent(WaitVerify.this, SignIn.class);
        startActivity(intent);
        finish(); // Finish the WaitVerificationActivity
    }
}