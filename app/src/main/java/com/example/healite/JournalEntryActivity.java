package com.example.healite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.JournalEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

public class JournalEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText title, generalEntry, positiveEntry, notSoPositiveEntry;
    private ImageButton checkMark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        title = findViewById(R.id.entrytitle);
        generalEntry = findViewById(R.id.generalEntry);
        positiveEntry = findViewById(R.id.positiveEntry);
        notSoPositiveEntry = findViewById(R.id.notSoPositiveEntry);

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

    private void saveJournalEntry() {
        String noteTitle = title.getText().toString().trim();
        String noteGeneral = generalEntry.getText().toString().trim();
        String notePositive = positiveEntry.getText().toString().trim();
        String noteNotPositive = notSoPositiveEntry.getText().toString().trim();
        String dateString = "";
        //Date noteDate = null;

        Calendar calendar;
        SimpleDateFormat simpleDateFormat;

        // noteTitle, notePositive and noteNotPositive can be empty/optional entries.
        if (noteGeneral.isEmpty()) {
            generalEntry.setError("General entry cannot be empty");
            generalEntry.requestFocus();
        }

        try {
            // date the user is creating note
            calendar = Calendar.getInstance();
            simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            dateString = simpleDateFormat.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //dateFormat.format(noteDate);

        // create Journal Entry object
        JournalEntry newNote = new JournalEntry(noteTitle, dateString, noteGeneral, notePositive, noteNotPositive);

        // add to firebase database
        FirebaseDatabase.getInstance().getReference("Journal Entry")
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