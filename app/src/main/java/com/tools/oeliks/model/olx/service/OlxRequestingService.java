package com.tools.oeliks.model.olx.service;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OlxRequestingService extends JobIntentService {

    public static final int JOB_ID = 0xF1;

    private ScheduledExecutorService service;

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
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
    }
}
