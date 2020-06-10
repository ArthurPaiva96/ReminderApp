package com.arthurpaiva96.reminderapp.model;

import androidx.annotation.NonNull;

public class Reminder {
    private final String title;
    private final String description;
    private final String date;
    private final String hour;

    public Reminder(String title, String description, String date, String hour) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.hour = hour;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getTitle();
    }
}
