package com.example.healite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.JournalEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CalendarFragment extends Fragment {

    private TextView dateSelected, moodRate, dayRate, description;
    //private TextView text;
    private CalendarView calendar;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    private ListView listView;
    private String date, dayString, moodString, generalString;
    private ArrayList<JournalEntry> notesList = new ArrayList<>();

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateSelected = view.findViewById(R.id.dateSelected);
        calendar = view.findViewById(R.id.full_calendar);
        moodRate = view.findViewById(R.id.textView_moodRate);
        dayRate = view.findViewById(R.id.textView_dayRate);
        description = view.findViewById(R.id.textView_general);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Entry Journal");
        userID = user.getUid();

        reference.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    for (DataSnapshot ds : task.getResult().getChildren()) {
                        int dRate = ds.child("dayRate").getValue(Integer.class);
                        int mRate = ds.child("moodRate").getValue(Integer.class);
                        String general = ds.child("general").getValue(String.class);
                        String noteDate = ds.child("todayDate").getValue(String.class);

                        JournalEntry note = new JournalEntry(noteDate, dRate, mRate, general);
                        notesList.add(note);
                    }
                }
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                date = (month + 1) + "-" + dayOfMonth + "-" + year;
                dateSelected.setText(date);

                for (JournalEntry currentNote : notesList) {
                    if (currentNote.getTodayDate().compareTo(date) == 0) {
                        dayString = "Day Rate: " + currentNote.getDayRate();
                        moodString = "Mood Rate: " + currentNote.getMoodRate();
                        generalString = "Description: " + currentNote.getGeneral();
                    }
                }

                dayRate.setText(dayString);
                moodRate.setText(moodString);
                description.setText(generalString);
            }
        });
    }
}
