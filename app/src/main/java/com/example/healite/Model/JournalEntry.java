package com.example.healite.Model;

import java.time.LocalDate;
import java.util.Date;

public class JournalEntry {

    public String title, general, positive, notSoPositive;
    public String dayMonthYear;

    public JournalEntry(String title, String dayMonthYear, String general, String positive, String notSoPositive) {
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
