package com.example.healite.Model;

import java.util.Calendar;
import java.util.Date;

public class JournalEntry {

    private String general;
    private Date todaydate;
    private int dayRate;
    private int moodRate;

    public JournalEntry() {

    }
    public JournalEntry(Date todaydate, int dayRate, int moodRate, String general) {
        this.todaydate = todaydate;
        this.dayRate = dayRate;
        this.moodRate = moodRate;
        this.general = general;
    }

    public Date getTodayDate() { return todaydate; }

    public int getMoodRate() { return moodRate; }

    public int getDayRate() {
        return dayRate;
    }

    public String getGeneral() {
        return general;
    }

    public void setDayRate(int dayRate) {
        this.dayRate = dayRate;
    }

    public void setMoodRate(int moodRate) {
        this.moodRate = moodRate;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public void setTodaydate(Date todaydate) {
        this.todaydate = todaydate;
    }
}
