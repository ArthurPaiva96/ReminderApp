package com.arthurpaiva96.reminderapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.arthurpaiva96.reminderapp.broadcast.service.ReminderAlarmService;

public class ReminderApplication extends Application {

    Intent mServiceIntent;

    @Override
    public void onCreate() {
        super.onCreate();

        ReminderAlarmService mReminderAlarmService = new ReminderAlarmService();
        mServiceIntent = new Intent(this, mReminderAlarmService.getClass());
        if (!isMyServiceRunning(mReminderAlarmService.getClass())) {
            startService(mServiceIntent);
        }
    }

    private boolean isMyServiceRunning(Class<? extends ReminderAlarmService> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {

            if (serviceClass.getName().equals(service.service.getClassName()))
                return true;

        }

        return false;
    }

}
