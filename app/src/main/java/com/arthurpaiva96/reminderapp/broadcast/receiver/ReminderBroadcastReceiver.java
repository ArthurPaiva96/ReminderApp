package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.model.Reminder;

import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.DEFAULT_ID;
import static com.arthurpaiva96.reminderapp.ui.activity.ConstantsActivities.KEY_REMINDER_EXTRA;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //TODO set up notification and other things(alarm and e-mail)
        this.createNotificationChannel(context);
        int reminderToNotifyId = (int) intent.getSerializableExtra(KEY_REMINDER_EXTRA);
        ReminderDatabase database = ReminderDatabase.getInstance(context);

        Reminder reminderToNotify = database.getRoomReminderDAO().getReminderById(reminderToNotifyId);

        if(reminderToNotify != null) sendNotification(context, reminderToNotify);

    }

    private void sendNotification(Context context, Reminder reminderToNotify) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "TEMP")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(reminderToNotify.getTitle())
                .setContentText(reminderToNotify.getDescription())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(reminderToNotify.getDescription()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        this.notificationManager.notify(reminderToNotify.getId(), builder.build());
    }

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TEMP";
            String description = "TEMP";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("TEMP", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
