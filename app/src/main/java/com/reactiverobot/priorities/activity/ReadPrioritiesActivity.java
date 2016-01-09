package com.reactiverobot.priorities.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

public class ReadPrioritiesActivity extends RoboActionBarActivity {

    @InjectView(R.id.top_priorities_layout) LinearLayout topPrioritiyLayout;
    @InjectView(R.id.not_priorities_layout) LinearLayout notPrioritiyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RuthlessPrefs prefs = RuthlessPrefs.fromContext(this);
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
