package com.arthurpaiva96.reminderapp.broadcast.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.arthurpaiva96.reminderapp.broadcast.ReminderAlarmManager;
import com.arthurpaiva96.reminderapp.broadcast.receiver.RestartReminderAlarmBroadcastReceiver;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.List;

public class ReminderAlarmService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            startMyOwnForeground();
        else
            startForeground(1, new Notification());

        loadRemindersAlarms();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return Service.START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        callRestartBroadcast();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        this.callRestartBroadcast();
    }

    private void loadRemindersAlarms() {
        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(this);

        List<Reminder> reminderList = reminderDatabase.getReminderDAO().getAllReminders();

        new ReminderAlarmManager(this, reminderList).setUpAllRemindersAlarms();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        //TODO customize
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    private void callRestartBroadcast() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("restartservice");
        broadcastIntent.setClass(this, RestartReminderAlarmBroadcastReceiver.class);
        this.sendBroadcast(broadcastIntent);
    }
}
