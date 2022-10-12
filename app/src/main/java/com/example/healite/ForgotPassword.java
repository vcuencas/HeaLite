package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class ForgotPassword extends AppCompatActivity {

    private Button recoverPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //recoverPassword = (Button) findViewById(R.id.recoverPassword);
    }
}