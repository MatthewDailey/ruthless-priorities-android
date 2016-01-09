package com.reactiverobot.priorities.activity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.inject.Inject;
import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;


public class SetPrioritiesActivity extends RoboActionBarActivity {

    @InjectView(R.id.top_priorities_layout) LinearLayout topPrioritiyLayout;
    @InjectView(R.id.not_priorities_layout) LinearLayout notPrioritiyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editing);

        RuthlessPrefs prefs = RuthlessPrefs.fromContext(this);

        addEditTextToLayout(prefs.getTopPrioritiesCount(), topPrioritiyLayout);
        addEditTextToLayout(prefs.getNotPrioritiesCount(), notPrioritiyLayout);
    }

    private void addEditTextToLayout(int editTextCount, LinearLayout layout) {
        for (int prefIndex = 0; prefIndex < editTextCount; prefIndex++) {
            layout.addView(new EditText(this));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
