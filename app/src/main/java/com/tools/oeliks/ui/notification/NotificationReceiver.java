package com.tools.oeliks.ui.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class NotificationReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final SharedPreferences.Editor settingsEdit = context.getSharedPreferences("settings", Context.MODE_PRIVATE).edit();
        settingsEdit.putBoolean("closeApp", true);
        settingsEdit.apply();

        System.out.println("STOPPING FROM NOTF. RECEIVER");
    }


}
