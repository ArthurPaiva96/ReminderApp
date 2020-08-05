package com.arthurpaiva96.reminderapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.arthurpaiva96.reminderapp.database.dao.EmailDAO;
import com.arthurpaiva96.reminderapp.model.EmailReminder;

@Database(entities = {EmailReminder.class}, version = 1, exportSchema = false)
public abstract class EmailDatabase extends RoomDatabase {
    private static final String DATABASE_FILE_NAME = "email.db";

    public abstract EmailDAO getEmailDAO();

    public static EmailDatabase getInstance(Context context){
        return Room.databaseBuilder(context, EmailDatabase.class, DATABASE_FILE_NAME)
                .allowMainThreadQueries()
                .build();
    }

}
