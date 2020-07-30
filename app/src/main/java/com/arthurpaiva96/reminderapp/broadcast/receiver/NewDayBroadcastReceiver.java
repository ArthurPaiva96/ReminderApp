package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.arthurpaiva96.reminderapp.broadcast.ReminderAlarmManager;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.database.dao.ReminderDAO;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.List;

public class NewDayBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO when date changes, turn-off all alarms and create new ones
        ReminderDatabase database = ReminderDatabase.getInstance(context);
        List<Reminder> allReminders = database.getRoomReminderDAO().getAllReminders();

        ReminderAlarmManager reminderAlarmManager = new ReminderAlarmManager(context, allReminders);
        reminderAlarmManager.deleteAllReminders();

    }
}
