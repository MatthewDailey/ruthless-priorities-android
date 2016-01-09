package com.reactiverobot.priorities.prefs;

import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.roboguice.shaded.goole.common.collect.Lists;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void testDefaultTopPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        List<String> topPriorities = prefs.getTopPriorities();
        assertTrue(topPriorities.isEmpty());
    }

    @Test
    public void testSetTopPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        List<String> testPriorities = Lists.newArrayList("p1", "p2");
        prefs.setTopPriorities(testPriorities);
        assertEquals(testPriorities, prefs.getTopPriorities());
    }

    @Test
    public void testDefaultNotPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        List<String> notPriorities = prefs.getNotPriorities();
        assertTrue(notPriorities.isEmpty());
    }

    @Test
    public void testSetNotPriorities() {
        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        List<String> testPriorities = Lists.newArrayList("p1", "p2");
        prefs.setNotPriorities(testPriorities);
        assertEquals(testPriorities, prefs.getNotPriorities());
    }
}