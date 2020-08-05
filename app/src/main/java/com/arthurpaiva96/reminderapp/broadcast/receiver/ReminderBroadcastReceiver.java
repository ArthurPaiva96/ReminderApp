package com.arthurpaiva96.reminderapp.broadcast.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.arthurpaiva96.reminderapp.R;
import com.arthurpaiva96.reminderapp.broadcast.EmailSender;
import com.arthurpaiva96.reminderapp.database.EmailDatabase;
import com.arthurpaiva96.reminderapp.database.ReminderDatabase;
import com.arthurpaiva96.reminderapp.model.EmailReminder;
import com.arthurpaiva96.reminderapp.model.Reminder;

import java.util.List;

import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.KEY_REMINDER_EXTRA;
import static com.arthurpaiva96.reminderapp.ConstantsReminderApp.NOTIFICATION_CHANNEL;

public class ReminderBroadcastReceiver extends BroadcastReceiver {

    private NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.createNotificationChannel(context);
        int reminderToNotifyId = (int) intent.getSerializableExtra(KEY_REMINDER_EXTRA);
        ReminderDatabase reminderDatabase = ReminderDatabase.getInstance(context);
        EmailDatabase emailDatabase = EmailDatabase.getInstance(context);

        List<EmailReminder> emailReminder = emailDatabase.getEmailDAO().getAllEmails();

        Reminder reminderToNotify = reminderDatabase.getReminderDAO().getReminderById(reminderToNotifyId);

        if(reminderToNotify != null) sendNotification(context, reminderToNotify);

        if(thereIsInternetConnection(context) && emailReminder.size() > 0 && reminderToNotify != null)
            sendNotificationEmail(emailReminder.get(0), reminderToNotify);

    }

    private void sendNotificationEmail(EmailReminder emailReminder, Reminder reminderToNotify) {
        EmailSender emailSender = new EmailSender(
                emailReminder.getEmail(),
                emailReminder.getPassword(),
                reminderToNotify.getTitle(),
                reminderToNotify.getDescription());


        emailSender.sendReminderEmail();
    }

    private boolean thereIsInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //TODO customize notification
    private void sendNotification(Context context, Reminder reminderToNotify) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(reminderToNotify.getTitle())
                .setContentText(reminderToNotify.getDescription())
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(reminderToNotify.getDescription()))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));

        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_INSISTENT;

        this.notificationManager.notify(reminderToNotify.getId(), notification);
    }

    //Code from android documentation
    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_CHANNEL, importance);
            channel.setDescription(NOTIFICATION_CHANNEL);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
