package com.reactiverobot.priorities.broadcast;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.inject.Inject;

public class RuthlessPriorityReminder extends BroadcastReceiver {

    @Inject NotificationManager notificationManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TEST", String.valueOf(notificationManager.hashCode()));
    }

}
