package com.arthurpaiva96.reminderapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM reminder")
    List<Reminder> getAllReminders();

    @Query("SELECT * FROM reminder WHERE id = :reminderId LIMIT 1")
    Reminder getReminderById(long reminderId);

    @Insert
    long save(Reminder newReminder);

    @Update
    void update(Reminder reminderToBeEdited);

    @Delete
    void delete(Reminder reminder);

}
