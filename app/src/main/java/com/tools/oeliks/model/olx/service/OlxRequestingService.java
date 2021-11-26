package com.tools.oeliks.model.olx.service;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import com.tools.oeliks.ui.notification.Notification;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OlxRequestingService extends JobIntentService {

    public static final int JOB_ID = 0xF1;

    private ScheduledExecutorService service;

    private Notification notification;

    private Handler handler = new Handler();

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        notification = new Notification(this.getSystemService(NotificationManager.class));


        System.out.println(" - - - -  - - - -  - - - -  Handling work!  - - - -  - - - -  - - - - ");
        stopScheduler();
        runScheduler();
    }

    public void stopScheduler() {
        if (service != null && !service.isShutdown()) {
            service.shutdownNow();
        }
    }

    private void runScheduler() {
        service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(this::requestOlx, 0, 10, TimeUnit.SECONDS);
    }

    private void requestOlx() {
        System.out.println(" - - - -  - - - -  - - - -  REQUESTING OLX  - - - -  - - - -  - - - - ");
        handler.post(() -> Toast.makeText(this, "NEW ITEMS AVAILABLE", Toast.LENGTH_SHORT).show());
        handler.post(() -> notification.addNotification(this, "NEW ITEMS AVAILABLE"));
    }
}
