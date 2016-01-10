package com.reactiverobot.priorities.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;

import org.roboguice.shaded.goole.common.collect.Lists;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;


public class SetPrioritiesActivity extends RoboActionBarActivity {

    @InjectView(R.id.top_priorities_layout) LinearLayout topPrioritiyLayout;
    @InjectView(R.id.not_priorities_layout) LinearLayout notPrioritiyLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            RuthlessPrefs.fromContext(this).setTopPriorities(readPrioritiesFromLayout(topPrioritiyLayout));
            RuthlessPrefs.fromContext(this).setNotPriorities(readPrioritiesFromLayout(notPrioritiyLayout));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * @return A list of all non-empty strings in child EditText fields of the input layout.
     */
    private List<String> readPrioritiesFromLayout(LinearLayout layout) {
        List<String> topPriorities = Lists.newArrayList();

        for (int childIndexOfPriorityInput = 0;
             childIndexOfPriorityInput < layout.getChildCount();
             childIndexOfPriorityInput++) {

            View priorityInputView = layout.getChildAt(childIndexOfPriorityInput);
            if (priorityInputView instanceof EditText) {
                String inputContents = ((EditText) priorityInputView).getText().toString();
                if (!inputContents.isEmpty()) {
                    topPriorities.add(inputContents);
                }
            }
        }
        return topPriorities;
    }
}
