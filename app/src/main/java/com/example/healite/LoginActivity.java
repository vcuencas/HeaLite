package com.example.healite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextEmail, editTextPassword;
    private Button signIn, register, forgotPassword;

    private FirebaseAuth mAuth;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("ExistingUsers");
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        register = findViewById(R.id.signUp);
        register.setOnClickListener(this);

        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

        editTextEmail = findViewById(R.id.emailInput);
        editTextPassword = findViewById(R.id.passwordInput);

        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp:
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.signIn:
                userLogin();
                break;
            case R.id.forgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    // this function logs in the user.
    private void userLogin() {

        final String password = editTextPassword.getText().toString().trim();

        final String email = editTextEmail.getText().toString().trim();

        if (!verifyEmailAddress() | !verifyPassword()) {
            return;
        }
        else {
            isUser();
        }
    }

    // this function verifies the user credentials entered are correct by matching a user already registered.
    private void isUser() {
        String userEnteredEmail = editTextEmail.getText().toString().trim();
        String userEnteredPassword = editTextPassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(userEnteredEmail, userEnteredPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    // this function verifies the user's password when registering to make sure it is not empty and has more than 6 characters.
    private Boolean verifyPassword() {
        final String password = editTextPassword.getText().toString().trim();

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum password length should be at least 6 characters!");
            editTextPassword.requestFocus();
            return false;
        }
        return true;
    }

    // this function verifies the user's email when registering to make sure it is not empty and it is an actual email address.
    private Boolean verifyEmailAddress() {
        final String email = editTextEmail.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email!");
            editTextEmail.requestFocus();
            return false;
        }
        return true;
    }
}