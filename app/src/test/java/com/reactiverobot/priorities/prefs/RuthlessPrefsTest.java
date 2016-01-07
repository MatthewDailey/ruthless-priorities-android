package com.reactiverobot.priorities.prefs;

import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RuthlessPrefsTest extends AbstractRoboTest {

    @Test
    public void testInstantiatePrefs() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        assertNotNull(prefs);
    }

    @Test
    public void testDefaultNumberOfPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        assertEquals(3, prefs.getTopPrioritiesCount());
    }

    @Test
    public void testSetNumberOfPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        prefs.setTopPrioritiesCount(1);
        assertEquals(1, prefs.getTopPrioritiesCount());
    }

    @Test
    public void testDefaultNumberOfNonPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        assertEquals(1, prefs.getNotPrioritiesCount());
    }

    @Test
    public void testSetNumberOfNonPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        prefs.setNotPrioritiesCount(2);
        assertEquals(2, prefs.getNotPrioritiesCount());
    }
}