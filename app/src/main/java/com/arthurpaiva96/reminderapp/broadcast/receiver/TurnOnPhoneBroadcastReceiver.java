package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.arthurpaiva96.reminderapp.broadcast.ReminderAlarmManager;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TurnOnPhoneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        //TODO set-up todays alarms


        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(context);

        List<Reminder> reminderList = reminderDatabase.getRoomReminderDAO().getAllReminders();
        List<Reminder> remindersToSetUpAlarms = new ArrayList<>();

        for(Reminder reminder : reminderList){

            if(reminder.reminderIsTodayInTheFuture()){
                remindersToSetUpAlarms.add(reminder);
            };
        }

        if(!remindersToSetUpAlarms.isEmpty()){

            ReminderAlarmManager alarmManager = new ReminderAlarmManager(context,remindersToSetUpAlarms);
            alarmManager.setUpAllRemindersAlarms();
        }
    }

}
