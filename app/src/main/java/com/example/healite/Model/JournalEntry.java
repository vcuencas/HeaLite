package com.example.healite.Model;

import java.util.Calendar;
import java.util.Date;

public class JournalEntry {

    private String general;
    private String todaydate;
    private int dayRate;
    private int moodRate;

    public JournalEntry(String todaydate, int dayRate, int moodRate, String general) {
        this.todaydate = todaydate;
        this.dayRate = dayRate;
        this.moodRate = moodRate;
        this.general = general;
    }

    public String getTodayDate() { return todaydate; }

    public int getMoodRate() { return moodRate; }

    public int getDayRate() {
        return dayRate;
    }

    public String getGeneral() {
        return general;
    }
}
