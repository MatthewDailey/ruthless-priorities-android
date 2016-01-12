package com.reactiverobot.priorities.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.broadcast.RuthlessPriorityReminder;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import java.util.Calendar;
import java.util.List;

import roboguice.activity.RoboActionBarActivity;

public class ReadPrioritiesActivity extends RoboActionBarActivity {

    @Inject AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (RuthlessPrefs.fromContext(this).isFirstStartUp()) {
            setDailyReminder();
            RuthlessPrefs.fromContext(this).reportStartedUp();
        }
    }

    private void setDailyReminder() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 6);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        Intent showNotificationIntent = new Intent(this, RuthlessPriorityReminder.class);
        showNotificationIntent.setAction(RuthlessPriorityReminder.SHOW_NOTIFICATION_ACTION);
        PendingIntent pendingShowNotificationIntent =
                PendingIntent.getBroadcast(this, 0, showNotificationIntent, PendingIntent.FLAG_ONE_SHOT);

        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), 1000*60*60*24,
                pendingShowNotificationIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(this);
        if (prefs.getTopPriorities().isEmpty() && prefs.getNotPriorities().isEmpty()) {
            setNoPrioritiesSavedContentView();
        } else {
            setPrioritiesContentView(prefs);
        }
    }

    private void setNoPrioritiesSavedContentView() {
        setContentView(R.layout.activity_no_priorities_set);
        Button setPrioritiesButton = (Button) findViewById(R.id.set_priorities_button);

        setPrioritiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchSetPrioritiesActivity();
            }
        });
    }

    private void setPrioritiesContentView(RuthlessPrefs prefs) {
        setContentView(R.layout.activity_main);
        LinearLayout topPrioritiyLayout = (LinearLayout) findViewById(R.id.top_priorities_layout);
        LinearLayout notPrioritiyLayout = (LinearLayout) findViewById(R.id.not_priorities_layout);

        addPriorityTextViews(prefs.getTopPriorities(), topPrioritiyLayout);
        addPriorityTextViews(prefs.getNotPriorities(), notPrioritiyLayout);
    }

    private void addPriorityTextViews(List<String> priorities, LinearLayout layout) {
        for (String priority : priorities) {
            TextView prioritiesText = new TextView(this);
            prioritiesText.setText(priority);
            prioritiesText.setPadding(0, 20, 0, 0);
            prioritiesText.setTextSize(25);
            layout.addView(prioritiesText);
        }
    }

    private void launchSetPrioritiesActivity() {
        Intent setPrioritiesIntent = new Intent(this, SetPrioritiesActivity.class);
        startActivity(setPrioritiesIntent);
    }

}
