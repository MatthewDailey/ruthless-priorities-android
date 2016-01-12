package com.reactiverobot.priorities.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.inject.Inject;
import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.activity.SetPrioritiesActivity;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import roboguice.receiver.RoboBroadcastReceiver;

public class RuthlessPriorityReminder extends RoboBroadcastReceiver {

    private static final int PRIORITY_REMINDER_ID = 4242552;

    @Inject NotificationManager notificationManager;

    protected void handleReceive(Context context, Intent intent) {
        PendingIntent launchSetPrioritiesActivityIntent = PendingIntent.getActivity(context, -1,
                new Intent(context, SetPrioritiesActivity.class),
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationbBuilder = new NotificationCompat.Builder(context)
                .setContentTitle("Ruthless Priorities")
                .setContentInfo("Set your top priorities for today.")
                .setContentIntent(launchSetPrioritiesActivityIntent)
                .setOngoing(true)
                .setAutoCancel(true)
                .setColor(0xBA0000)
                .setSmallIcon(R.drawable.blank_skull);

        RuthlessPrefs.fromContext(context).clearPriorities();

        notificationManager.notify(PRIORITY_REMINDER_ID, notificationbBuilder.build());
    }

}
