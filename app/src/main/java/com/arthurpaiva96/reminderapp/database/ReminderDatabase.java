package com.arthurpaiva96.reminderapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.arthurpaiva96.reminderapp.database.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;

@Database(entities = {Reminder.class}, version = 1, exportSchema = false)
public abstract class ReminderDatabase extends RoomDatabase {

    private static final String DATABASE_FILE_NAME = "reminder.db";

    public abstract ReminderDAO getReminderDAO();

    public static ReminderDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, ReminderDatabase.class, DATABASE_FILE_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
