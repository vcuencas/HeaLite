package com.example.healite;

import java.util.Date;

public class JournalEntry {

    public String title, general, positive, notSoPositive;
    public Date dayMonthYear;
    public JournalEntry() {

    }

    public JournalEntry(String title, Date dayMonthYear, String general, String positive, String notSoPositive) {
        this.title = title;
        this.dayMonthYear = dayMonthYear;
        this.general = general;
        this.positive = positive;
        this.notSoPositive = notSoPositive;
    }

    public String getGeneral(Date dayMonthYear) {
        return general;
    }

    public String getPositive(Date dayMonthYear) {
        return positive;
    }

    public String getNotSoPositive(Date dayMonthYear) {
        return notSoPositive;
    }
}
