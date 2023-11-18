package com.example.animeverse.IntroUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.animeverse.R;

public class Introactivity extends AppCompatActivity {
    private Button buttonSignUp;
    private Button buttonGoogleSignIn;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introactivity);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonGoogleSignIn = findViewById(R.id.buttonGoogleSignIn);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }

            private void openSignUpActivity() {
                Intent intent = new Intent(Introactivity.this, Signup.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }

            private void openLoginActivity() {
                Intent intent = new Intent(Introactivity.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
}