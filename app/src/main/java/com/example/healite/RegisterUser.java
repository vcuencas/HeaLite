package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, registerUser, login;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        login = findViewById(R.id.BackToLogin);
        login.setOnClickListener(this);

        editTextFirstName = findViewById(R.id.firstName);
        editTextLastName = findViewById(R.id.lastName);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        progressBar = findViewById(R.id.progressBar);
    }

    // this function handles the clicking of buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
            case R.id.BackToLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.registerUser:
                registerUser();
                startActivity(new Intent(this, PreSignUp.class));
                break;
        }
    }

    // this function registers the user and adds it into the database
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();

        if (firstName.isEmpty()) {
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("Last name is required!");
            editTextLastName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please provide a valid email address!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum password length should be at least 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                         Users user = new Users(firstName, lastName, email);

                        FirebaseDatabase.getInstance().getReference("ExistingUsers")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(RegisterUser.this, "1. Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                    }
                            progressBar.setVisibility(View.GONE);
                        });
                    } else {
                        Toast.makeText(RegisterUser.this, "2. Failed to register! Try again!", Toast.LENGTH_LONG).show();
                        //Log.e("FirebaseAuth", "Failed login", task.getException());
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}