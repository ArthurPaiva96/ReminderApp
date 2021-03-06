package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.arthurpaiva96.reminderapp.broadcast.service.ReminderAlarmService;

import java.util.Objects;

public class TurnOnPhoneBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(Objects.equals(intent.getAction(), Intent.ACTION_BOOT_COMPLETED))
            context.startService(new Intent(context, ReminderAlarmService.class));

    }

}
