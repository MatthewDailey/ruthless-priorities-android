package com.reactiverobot.priorities.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.reactiverobot.priorities.broadcast.RuthlessPriorityReminder;

import java.util.Calendar;

public final class ReminderUtils {
    public static void setDailyReminder(Context context, AlarmManager alarmManager) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 6);

        Intent showNotificationIntent = new Intent(context, RuthlessPriorityReminder.class);
        showNotificationIntent.setAction(RuthlessPriorityReminder.SHOW_NOTIFICATION_ACTION);
        PendingIntent pendingShowNotificationIntent =
                PendingIntent.getBroadcast(context, 0, showNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingShowNotificationIntent);
        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingShowNotificationIntent);
    }
}
