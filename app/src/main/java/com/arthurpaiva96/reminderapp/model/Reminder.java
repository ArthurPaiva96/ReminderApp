package com.arthurpaiva96.reminderapp.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.DEFAULT_ID;

public class Reminder implements Serializable {

    private int id = DEFAULT_ID;
    private String title;
    private String description;
    private String date;

    public Reminder() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    private String hour;

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

    public void setId(int idCounter) {
        this.id = idCounter;
    }

    public int getId() {
        return id;
    }
}
