package com.reactiverobot.priorities.activity;

import com.reactiverobot.priorities.MainActivity;
import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.robolectric.Robolectric;

import static org.junit.Assert.assertNotNull;

public class MainActivityTest extends AbstractRoboTest {

    @Test
    public void testActivityStarts() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.finish();
        assertNotNull(mainActivity);
    }
}
