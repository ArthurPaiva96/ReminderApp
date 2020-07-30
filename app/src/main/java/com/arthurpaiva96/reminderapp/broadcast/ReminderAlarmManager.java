package com.arthurpaiva96.reminderapp.broadcast;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arthurpaiva96.reminderapp.broadcast.receiver.ReminderBroadcastReceiver;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.Calendar;
import java.util.List;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.KEY_REMINDER_EXTRA;


public class ReminderAlarmManager {
    private AlarmManager alarmManager;
    private List<Reminder> remindersToSetUpAsAlarms;
    private Context context;

    public ReminderAlarmManager(Context context, List<Reminder> remindersToSetUpAsAlarms) {

        this.context = context;
        this.alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        this.remindersToSetUpAsAlarms = remindersToSetUpAsAlarms;

    }

    public void setUpAllRemindersAlarms() {
        for (Reminder reminder : this.remindersToSetUpAsAlarms) {
            long timeInMillis = this.calculateTimeInMillis(reminder.getHour());
            Log.i("NONONONONO", "setUpAllRemindersAlarms: " + timeInMillis);
            this.setUpAlarm(reminder, timeInMillis);
        }
    }

    private long calculateTimeInMillis(String hour) {

        Calendar reminderDate = Calendar.getInstance();
        reminderDate.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour.substring(0, 2)));
        reminderDate.set(Calendar.MINUTE, Integer.parseInt(hour.substring(3,5)));

        return reminderDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
    }

    private void setUpAlarm(Reminder reminder, long timeRemaningToTheReminder) {

        PendingIntent pendingIntent = setupIntent(reminder);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeRemaningToTheReminder, pendingIntent);

    }

    private PendingIntent setupIntent(Reminder reminder) {
        Intent intent = new Intent(context, ReminderBroadcastReceiver.class).putExtra(KEY_REMINDER_EXTRA, reminder.getId());
        return PendingIntent.getBroadcast(this.context, reminder.getId(), intent, PendingIntent.FLAG_CANCEL_CURRENT);
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
