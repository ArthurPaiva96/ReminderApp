package com.arthurpaiva96.reminderapp.dao;

import com.arthurpaiva96.reminderapp.model.Reminder;
import java.util.ArrayList;
import java.util.List;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.DEFAULT_ID;

public class ReminderDAO {

    private final static List<Reminder> reminderList = new ArrayList<>();
    private static int idCounter = 0;

    public void save(Reminder newReminder) {
        idCounter++;
        newReminder.setId(idCounter);
        reminderList.add(newReminder);
    }

    public List<Reminder> getAllReminders() {
        return new ArrayList<>(reminderList);
    }

    public void editReminder(Reminder reminder){

        if(reminder.getId() == DEFAULT_ID){
            this.save(reminder);
        }else {

            for (Reminder r : reminderList) {
                if (r.getId() == reminder.getId()) {
                    reminderList.set(reminderList.indexOf(r), reminder);
                    break;
                }
            }

        }
    }

    public void deleteAReminder(Reminder itemAtPosition) {
        if(itemAtPosition != null) reminderList.remove(itemAtPosition);
    }
}
