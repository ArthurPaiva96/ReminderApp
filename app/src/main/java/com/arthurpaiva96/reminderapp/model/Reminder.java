package com.arthurpaiva96.reminderapp.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity
public class Reminder implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
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

    public boolean reminderIsInTheFuture() {

        Calendar today = Calendar.getInstance();
        Calendar reminderDate = this.getReminderDateAsCalendar();

        return reminderDate.getTimeInMillis() - today.getTimeInMillis() > 0;
    }

    public Calendar getReminderDateAsCalendar() {

        Calendar reminderDate = Calendar.getInstance();

        reminderDate.set(Calendar.DAY_OF_MONTH, Integer.parseInt(
                this.getDate().substring(0,2)));
        reminderDate.set(Calendar.MONTH, Integer.parseInt(
                this.getDate().substring(3,5))-1);
        reminderDate.set(Calendar.YEAR, Integer.parseInt(
                this.getDate().substring(6)));

        reminderDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(
                this.getHour().substring(0,2)));
        reminderDate.set(Calendar.MINUTE, Integer.parseInt(
                this.getHour().substring(3)));
        reminderDate.set(Calendar.SECOND, 0);

        return reminderDate;
    }

    public boolean allFieldsAreNotNull() {

        return this.getTitle() != null && this.getDescription() != null
                && this.getDate() != null && this.getHour() != null
                && !this.getDate().isEmpty() && !this.getHour().isEmpty();
    }
}
