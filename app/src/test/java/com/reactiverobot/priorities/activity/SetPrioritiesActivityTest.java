package com.reactiverobot.priorities.activity;

import android.widget.EditText;
import android.widget.LinearLayout;

import com.reactiverobot.priorities.R;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;
import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.roboguice.shaded.goole.common.collect.Lists;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowToast;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

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

        assertEquals(2, topPriorityLayout.getChildCount());
    }

    @Test
    public void testNotPriorityLayoutExists() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout notPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        assertNotNull(notPriorityLayout);

        assertEquals(2, notPriorityLayout.getChildCount());
    }

    @Test
    public void testDoesNotSaveTopPrioritiesWithSaveMenuButton() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout topPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.top_priorities_layout);
        ((EditText) topPriorityLayout.getChildAt(1)).setText("p1");

        assertTrue(RuthlessPrefs.fromContext(context).getTopPriorities().isEmpty());

        shadowOf(setPrioritiesActivity).clickMenuItem(R.id.action_save);

        assertEquals(Lists.newArrayList(), RuthlessPrefs.fromContext(context).getTopPriorities());

        assertNotNull(ShadowToast.getLatestToast());
    }

    @Test
    public void testDoesNotSaveNotPrioritiesWithSaveMenuButton() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout notPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        ((EditText) notPriorityLayout.getChildAt(1)).setText("p1");

        assertTrue(RuthlessPrefs.fromContext(context).getTopPriorities().isEmpty());

        shadowOf(setPrioritiesActivity).clickMenuItem(R.id.action_save);

        assertEquals(Lists.newArrayList(), RuthlessPrefs.fromContext(context).getNotPriorities());

        assertNotNull(ShadowToast.getLatestToast());
    }

    @Test
    public void testSaveTopAndNotPrioritiesWithSaveMenuButton() {
        SetPrioritiesActivity setPrioritiesActivity = Robolectric.setupActivity(SetPrioritiesActivity.class);
        LinearLayout topPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.top_priorities_layout);
        ((EditText) topPriorityLayout.getChildAt(1)).setText("p1");
        LinearLayout notPriorityLayout = (LinearLayout) setPrioritiesActivity.findViewById(
                R.id.not_priorities_layout);
        ((EditText) notPriorityLayout.getChildAt(1)).setText("n1");

        assertTrue(RuthlessPrefs.fromContext(context).getTopPriorities().isEmpty());

        shadowOf(setPrioritiesActivity).clickMenuItem(R.id.action_save);

        List<String> expectedTopPriorities = Lists.newArrayList("p1");
        assertEquals(expectedTopPriorities, RuthlessPrefs.fromContext(context).getTopPriorities());

        List<String> expectedNotPriorities = Lists.newArrayList("n1");
        assertEquals(expectedNotPriorities, RuthlessPrefs.fromContext(context).getNotPriorities());

        assertNull(ShadowToast.getLatestToast());
    }

}
