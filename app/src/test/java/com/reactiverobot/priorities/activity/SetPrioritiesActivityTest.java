package com.reactiverobot.priorities.activity;

import android.widget.LinearLayout;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SetPrioritiesActivityTest extends AbstractRoboTest {

    @Test
    public void testActivityStarts() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        setPrioritiesActivity.finish();
        assertNotNull(setPrioritiesActivity);
    }

    @Test
    public void testTopPriorityLayoutExists() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout topPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.top_priorities_layout);
        assertNotNull(topPriorityLayout);

        assertEquals(4, topPriorityLayout.getChildCount());
    }

    @Test
    public void testNotPriorityLayoutExists() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout notPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        assertNotNull(notPriorityLayout);

        assertEquals(2, notPriorityLayout.getChildCount());
    }

}
