package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuestionnaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
//        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void submitbuttonHandler(View view) {
        if (view.getId() == R.id.submitButton) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
        }
    }
