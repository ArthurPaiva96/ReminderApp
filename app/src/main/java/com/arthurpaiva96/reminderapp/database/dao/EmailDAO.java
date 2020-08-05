package com.arthurpaiva96.reminderapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.arthurpaiva96.reminderapp.model.EmailReminder;

import java.util.List;

@Dao
public interface EmailDAO {

    @Query("SELECT * FROM EmailReminder")
    List<EmailReminder> getAllEmails();

    @Insert
    void save(EmailReminder newEmailReminder);

    @Delete
    void delete(EmailReminder emailReminder);

}
