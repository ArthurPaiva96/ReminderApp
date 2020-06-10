package com.arthurpaiva96.reminderapp.dao;

import com.arthurpaiva96.reminderapp.model.Reminder;
import java.util.ArrayList;
import java.util.List;

public class ReminderDAO {

    private final static List<Reminder> reminderList = new ArrayList<Reminder>();

    public void save(Reminder newReminder) {
        reminderList.add(newReminder);
    }

    public List<Reminder> getAllReminders() {
        return new ArrayList<>(reminderList);
    }
}
