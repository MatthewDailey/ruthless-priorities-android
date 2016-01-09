package com.reactiverobot.priorities.activity;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;
import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.roboguice.shaded.goole.common.collect.Lists;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ReadPrioritiesActivityTest extends AbstractRoboTest {

    @Test
    public void testActivityStarts() {
        ReadPrioritiesActivity readPrioritiesActivity = Robolectric.setupActivity(ReadPrioritiesActivity.class);
        readPrioritiesActivity.finish();
        assertNotNull(readPrioritiesActivity);
    }

    @Test
    public void testPrioritiesNotSet() {
        ReadPrioritiesActivity readPrioritiesActivity = Robolectric.setupActivity(ReadPrioritiesActivity.class);
        LinearLayout topPrioritiesLayout = (LinearLayout) readPrioritiesActivity.findViewById(
                R.id.top_priorities_layout);
        assertEquals(1, topPrioritiesLayout.getChildCount());

        LinearLayout notPrioritiesLayout = (LinearLayout) readPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        assertEquals(1, notPrioritiesLayout.getChildCount());
    }

    @Test
    public void testTopPrioritiesSet() {
        RuthlessPrefs.fromContext(context).setTopPriorities(Lists.newArrayList("p1", "p2"));

        ReadPrioritiesActivity readPrioritiesActivity = Robolectric.setupActivity(ReadPrioritiesActivity.class);
        LinearLayout topPrioritiesLayout = (LinearLayout) readPrioritiesActivity.findViewById(
                R.id.top_priorities_layout);
        assertEquals(3, topPrioritiesLayout.getChildCount());
        TextView secondPriority = (TextView) topPrioritiesLayout.getChildAt(2);
        assertEquals("p2", secondPriority.getText());
    }

    @Test
    public void testNotPrioritiesSet() {
        RuthlessPrefs.fromContext(context).setNotPriorities(Lists.newArrayList("p1", "p2"));

        ReadPrioritiesActivity readPrioritiesActivity = Robolectric.setupActivity(ReadPrioritiesActivity.class);
        LinearLayout notPrioritiesLayout = (LinearLayout) readPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        assertEquals(3, notPrioritiesLayout.getChildCount());
        TextView secondPriority = (TextView) notPrioritiesLayout.getChildAt(2);
        assertEquals("p2", secondPriority.getText());
    }

}