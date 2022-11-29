package com.example.healite;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healite.Model.JournalEntry;
import com.example.healite.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CalendarFragment extends Fragment {

    private TextView dateSelected;
    private CalendarView calendar;
    private FirebaseUser user;
    private DatabaseReference journalDB;
    private String userID;
    private TextView journalLog;
    String date;
    String stringDate;
    private List<JournalEntry> notesList = new ArrayList<>();

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
        journalLog = view.findViewById(R.id.journalEntryLog);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");



        user = FirebaseAuth.getInstance().getCurrentUser();
        journalDB = FirebaseDatabase.getInstance().getReference().child("Journal Entry");
        userID = user.getUid();

        journalDB.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //showData(snapshot);
                
                calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                        month++; // fixing month offset.
                        date = month + "/" + dayOfMonth + "/" + year;
                        dateSelected.setText(date);

                        //Date noteDate_ = snapshot.child("todaydate").getValue(Date.class);
                        //int rateDay = snapshot.child("dayRate").getValue(Integer.class);
//                        JournalEntry note = snapshot.getValue(JournalEntry.class);
//                        assert note != null;
//                        int rateDay = note.getDayRate();
                        //System.out.println(rateDay);
                        //stringDate = simpleDateFormat.format(noteDate_);
                        //System.out.println(stringDate);
                        //journalLog.setText(rateDay);

//                        for (DataSnapshot noteSnapshot : snapshot.getChildren()) {
//                            int rateDay = noteSnapshot.child("dayRate").getValue(Integer.class);
//                            int rateMood = noteSnapshot.child("moodRate").getValue(Integer.class);
//                            String genDes = noteSnapshot.child("general").getValue(String.class);
//                            Date noteDate = noteSnapshot.child("todaydate").getValue(Date.class);
//
//                            stringDate = simpleDateFormat.format(noteDate);
//
//                            System.out.println(stringDate);
//                            System.out.println("\n" + date);
//
//                            if (Objects.equals(date, stringDate)) {
//                                JournalEntry noteAdd = new JournalEntry(noteDate, rateDay, rateMood, genDes);
//                                notesList.add(noteAdd);
//
//                            }
//                        }
                    }
                });
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){
            }
        });
    }
}