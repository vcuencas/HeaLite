package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.healite.Model.Questionnaire;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class QuestionnaireActivity extends AppCompatActivity {
    private int q1_age;
    private String q2_gender = "";
    private int q3_mentalHealthRate;
    private int q4_physicalFitness;
    private int q5_relationSatisfaction;
    private int q6_wellRested;
    private int q7_sleepHours;
    private int q8_toughTime;
    private int q9_change;

    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        // Question 4 Mental Heath Rate
        seekBar = (SeekBar)findViewById(R.id.MentalHealthRate);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                q3_mentalHealthRate = i;
                Log.d("testing", "Mental Heath Rate == " + String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void submitButtonHandler(View view) {
        if (view.getId() == R.id.submitButton) {
            saveQuestionnaire();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    public void radioButtonHandler(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton)view).isChecked();

        // Question 1:      What is your age?
        // Answer Choices:  1 == 18-28, 2 == 29-39, 3 == 40-50, 4 == 51+
        switch (view.getId()) {
            case R.id.Q1_1:         // 18-28
                if (checked)
                    q1_age = 1;
                break;
            case R.id.Q1_2:         // 29-39
                if (checked)
                    q1_age = 2;
                break;
            case R.id.Q1_3:         // 40-50
                if (checked)
                    q1_age = 3;
                break;
            case R.id.Q1_4:         // 51+
                if (checked)
                    q1_age = 4;
                break;
        }

        // Question 2:      What is your gender?
        // Answer Choices:  male, female, other
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    q2_gender = "male";
                break;
            case R.id.female:
                if (checked)
                    q2_gender = "female";
                break;
            case R.id.other:
                if (checked)
                    q2_gender = "other";
                break;
        }

        // Question 4:      How satisfied are you with your fitness activity?
        // Answer Choices:  1 == Not at all, 2 == Moderately, 3 == Completely
        switch (view.getId()) {
            case R.id.Q4_1:         // Completely
                if (checked)
                    q4_physicalFitness = 3;
                break;
            case R.id.Q4_2:         // Moderately
                if (checked)
                    q4_physicalFitness = 2;
                break;
            case R.id.Q4_3:         // Not at all
                if (checked)
                    q4_physicalFitness = 1;
                break;
        }

        // Question 5:      Do you feel satisfied with your relationships and family?
        // Answer Choices:  1 == No, 2 == Sometimes, 3 == Yes
        switch (view.getId()) {
            case R.id.Q5_1:         // Yes
                if (checked)
                    q5_relationSatisfaction = 3;
                break;
            case R.id.Q5_2:         // No
                if (checked)
                    q5_relationSatisfaction = 1;
                break;
            case R.id.Q5_3:         // Sometimes
                if (checked)
                    q5_relationSatisfaction = 2;
                break;
        }

        // Question 6:      Do you feel well-rested after waking up?
        // Answer Choices:  1 == Rarely, 2 == Sometimes, 3 == Always
        switch (view.getId()) {
            case R.id.Q6_1:         // Sometimes
                if (checked)
                    q6_wellRested = 2;
                break;
            case R.id.Q6_2:         // Always
                if (checked)
                    q6_wellRested = 3;
                break;
            case R.id.Q6_3:         // Rarely
                if (checked)
                    q6_wellRested = 1;
                break;
        }

        // Question 7:      How many hours do you sleep per night?
        // Answer Choices:  1 == Less than 5, 2 == 5-7, 3 == 8-10, 4 == 10+
        switch (view.getId()) {
            case R.id.Q7_1:         // Less than 5
                if (checked)
                    q7_sleepHours = 1;
                break;
            case R.id.Q7_2:         // 5-7
                if (checked)
                    q7_sleepHours = 2;
                break;
            case R.id.Q7_3:         // 8-10
                if (checked)
                    q7_sleepHours = 3;
                break;
            case R.id.Q7_4:         // 10+
                if (checked)
                    q7_sleepHours = 4;
                break;
        }

        // Question 8:      Are you going through tough times?
        // Answer Choices:  1 == Yes, 2 == No
        switch (view.getId()) {
            case R.id.Q8_1:         // Yes
                if (checked)
                    q8_toughTime = 1;
                break;
            case R.id.Q8_2:         // No
                if (checked)
                    q8_toughTime = 2;
                break;
        }

        // Question 9:      What single change would you like to have to improve your life right now?
        // Answer Choices:  1 == More peacefulness, 2 == More productivity, 3 == More sleep, 4 == More energy
        switch (view.getId()) {
            case R.id.Q9_1:         // More peacefulness
                if (checked)
                    q9_change = 1;
                break;
            case R.id.Q9_2:         // More productivity
                if (checked)
                    q9_change = 2;
                break;
            case R.id.Q9_3:         // More sleep
                if (checked)
                    q9_change = 3;
                break;
            case R.id.Q9_4:         // More energy
                if (checked)
                    q9_change = 4;
                break;
        }
    }

    private void saveQuestionnaire () {
        // Create Questionnaire
        Questionnaire q = new Questionnaire(q1_age, q2_gender, q3_mentalHealthRate, q4_physicalFitness, q5_relationSatisfaction, q6_wellRested,
                q7_sleepHours, q8_toughTime, q9_change);

        // Add to Firebase
        FirebaseDatabase.getInstance().getReference("Questionnaire")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(q).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(QuestionnaireActivity.this, "Questionnaire successfully saved", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(QuestionnaireActivity.this, "Error in saving the questionnaire. Please try again.", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
