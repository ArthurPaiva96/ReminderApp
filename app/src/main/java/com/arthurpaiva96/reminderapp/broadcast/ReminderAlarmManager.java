package com.arthurpaiva96.reminderapp.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.arthurpaiva96.reminderapp.broadcast.receiver.ReminderBroadcastReceiver;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.Calendar;
import java.util.List;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.KEY_REMINDER_EXTRA;


public class ReminderAlarmManager {
    private final AlarmManager alarmManager;
    private final List<Reminder> remindersToSetUpAsAlarms;
    private final Context context;

    public ReminderAlarmManager(Context context, List<Reminder> remindersToSetUpAsAlarms) {

        this.context = context;
        this.alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        this.remindersToSetUpAsAlarms = remindersToSetUpAsAlarms;

    }

    public void setUpAllRemindersAlarms() {
        for (Reminder reminder : this.remindersToSetUpAsAlarms) {
            if(reminder.reminderIsInTheFuture())
                this.setUpAlarm(reminder, reminder.getReminderDateAsCalendar());
        }
    }

//    private long calculateTimeInMillis(String hour) {
//
//        Calendar reminderDate = Calendar.getInstance();
//        reminderDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.substring(0, 2)));
//        reminderDate.set(Calendar.MINUTE, Integer.parseInt(hour.substring(3,5)));
//
//        return reminderDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
//    }

    private void setUpAlarm(Reminder reminder, Calendar reminderDate) {

        PendingIntent pendingIntent = setupIntent(reminder);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminderDate.getTimeInMillis(), pendingIntent);
    }

    private PendingIntent setupIntent(Reminder reminder) {
        Intent intent = new Intent(context, ReminderBroadcastReceiver.class).putExtra(KEY_REMINDER_EXTRA, reminder.getId());
        return PendingIntent.getBroadcast(this.context, reminder.getId(), intent, 0);
    }

    public void deleteAllReminders(){
        for(Reminder reminder : this.remindersToSetUpAsAlarms){
            this.deleteIntent(reminder);
        }
    }

    private void deleteIntent(Reminder reminder){
        PendingIntent pendingIntent = setupIntent(reminder);
        alarmManager.cancel(pendingIntent);
    }



}
