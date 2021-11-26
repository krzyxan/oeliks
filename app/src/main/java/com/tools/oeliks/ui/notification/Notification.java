package com.tools.oeliks.ui.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.tools.oeliks.R;

public class Notification {

    public static final String CHANNEL_1_ID = "usingLocationCh1";
    private final NotificationManager notificationManager;

    public Notification(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
        createNotificationChannels();
    }

    private void createNotificationChannels() {
        NotificationChannel channel1 = new NotificationChannel(
                CHANNEL_1_ID,
                "New items available",
                NotificationManager.IMPORTANCE_HIGH
        );
        channel1.setDescription("Notification about new items available");
        notificationManager.createNotificationChannel(channel1);
    }

    public void addNotification(Context context, String message) {

        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);

        PendingIntent actionIntent = PendingIntent.getBroadcast(context,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification notification = new NotificationCompat.Builder(context, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.favicon)
                .setContentTitle("Oeliks")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .setOngoing(false)
                .setSilent(false)
//                .addAction(R.drawable.favicon, "Stop collecting", actionIntent)
                .build();

        notificationManager.notify(1, notification);
    }

    public void closeAllNotifications() {
        notificationManager.cancelAll();
    }

}
