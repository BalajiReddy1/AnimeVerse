package com.example.animeverse.IntroUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.animeverse.HelperClass;
import com.example.animeverse.Home;
import com.example.animeverse.LoginActivity;
import com.example.animeverse.R;
import com.example.animeverse.WaitVerify;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText signupName, signupUsername, signupEmail, signupPassword,signupContact;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);
        signupContact = findViewById(R.id.signup_contact);

        mAuth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String name = signupName.getText().toString();
                String email = signupEmail.getText().toString();
                String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                String contact = signupContact.getText().toString();

                // Perform validation
                if (name.isEmpty()) {
                    Toast.makeText(Signup.this, "Name cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(Signup.this, "Username cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if (contact.isEmpty()) {
                    Toast.makeText(Signup.this, "Contact No. cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(Signup.this, "Email cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(Signup.this, "Invalid email address!!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(Signup.this, "Password cannot be empty!!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(Signup.this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
                } else {
                    // If validation passes, create user account and save data
                    createAccount(name, email, username, password,contact);
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    // Helper method to validate email format
    private boolean isValidEmail(CharSequence target) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    // Helper method to create a user account
    private void createAccount(final String name, final String email, final String username, String password,String contact) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Save user data to Firebase Realtime Database
                            database = FirebaseDatabase.getInstance();
                            reference = database.getReference("users");
                            HelperClass helperClass = new HelperClass(name, email, username, password, contact);
                            reference.child(username).setValue(helperClass);

                            Toast.makeText(Signup.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Signup.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Signup.this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
