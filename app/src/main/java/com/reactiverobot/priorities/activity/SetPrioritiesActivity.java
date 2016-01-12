package com.reactiverobot.priorities.activity;

import android.app.AlarmManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.inject.Inject;
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

        addEditTextToLayout(topPrioritiyLayout);
        addEditTextToLayout(notPrioritiyLayout);
    }

    private EditText addEditTextToLayout(final LinearLayout layout) {
        EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);
        editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        editText.setImeActionLabel("Next", KeyEvent.KEYCODE_ENTER);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                EditText newEditText = addEditTextToLayout(layout);
                newEditText.requestFocus();
                return true;
            }
        });
        layout.addView(editText);
        return editText;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            RuthlessPrefs.fromContext(this).setTopPriorities(readPrioritiesFromLayout(topPrioritiyLayout));
            RuthlessPrefs.fromContext(this).setNotPriorities(readPrioritiesFromLayout(notPrioritiyLayout));
            finish();
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
