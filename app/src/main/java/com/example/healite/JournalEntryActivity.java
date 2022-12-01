package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.JournalEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JournalEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextGeneralEntry;
    private int day = 0, mood = 0;
    private ImageButton checkMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        editTextGeneralEntry = findViewById(R.id.generalEntry);

        checkMark = findViewById(R.id.save_note_btn);
        checkMark.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.save_note_btn) {
            saveJournalEntry();
            startActivity(new Intent(this, ProfileActivity.class));
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton)view).isChecked();

        //Check which radio button from DayRate was clicked
        switch(view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    day = 1;
                break;
            case R.id.radioButton2:
                if (checked)
                    day = 2;
                break;
            case R.id.radioButton3:
                if (checked)
                    day = 3;
                break;
            case R.id.radioButton4:
                if (checked)
                    day = 4;
                    break;
            case R.id.radioButton5:
                if (checked)
                    day = 5;
                break;
        }
        //Check which radio button from MoodRate was clicked
        switch(view.getId()) {
            case R.id.radioButton6:
                if (checked)
                    mood = 1;
                    break;
            case R.id.radioButton7:
                if (checked)
                    mood = 2;
                    break;
            case R.id.radioButton8:
                if (checked)
                    mood = 3;
                    break;
            case R.id.radioButton9:
                if (checked)
                    mood = 4;
                    break;
            case R.id.radioButton10:
                if (checked)
                    mood = 5;
                    break;
        }
    }
    private void saveJournalEntry() {
        String noteGeneral = editTextGeneralEntry.getText().toString().trim();

        String dateString = "";

        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        // noteTitle, notePositive and noteNotPositive can be empty/optional entries.
        if (noteGeneral.isEmpty()) {
            editTextGeneralEntry.setError("General entry cannot be empty");
            editTextGeneralEntry.requestFocus();
        }

        try {
            // date the user is creating note
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            dateString = simpleDateFormat.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create Journal Entry object
        JournalEntry newNote = new JournalEntry(dateString, day, mood, noteGeneral);

        // add to firebase database
        FirebaseDatabase.getInstance().getReference("Entry Journal")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push()
                .setValue(newNote).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(JournalEntryActivity.this, "Journal entry has been added successfully!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(JournalEntryActivity.this, "Failed to add journal entry. Try again!", Toast.LENGTH_LONG).show();
                    }
                });
    }

}