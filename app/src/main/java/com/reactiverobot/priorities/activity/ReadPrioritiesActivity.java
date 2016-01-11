package com.reactiverobot.priorities.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.broadcast.RuthlessPriorityReminder;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

public class ReadPrioritiesActivity extends RoboActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent broadcastIntent = new Intent(this, RuthlessPriorityReminder.class);
        sendBroadcast(broadcastIntent);
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
            layout.addView(prioritiesText);
        }
    }

    private void launchSetPrioritiesActivity() {
        Intent setPrioritiesIntent = new Intent(this, SetPrioritiesActivity.class);
        startActivity(setPrioritiesIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_read_priorities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
