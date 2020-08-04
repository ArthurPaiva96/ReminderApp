package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.arthurpaiva96.reminderapp.broadcast.service.ReminderAlarmService;

public class RestartReminderAlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(new Intent(context, ReminderAlarmService.class));
        } else {
            context.startService(new Intent(context, ReminderAlarmService.class));
        }
    }
}
