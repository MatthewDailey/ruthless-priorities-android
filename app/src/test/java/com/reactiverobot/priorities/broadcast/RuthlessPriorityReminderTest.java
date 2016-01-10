package com.reactiverobot.priorities.broadcast;

import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

public class RuthlessPriorityReminderTest extends AbstractRoboTest {

    @Test
    public void testBroadcastRecieverRegistered() {
        List<ShadowApplication.Wrapper> registeredReceivers = shadowOf(context).getRegisteredReceivers();

        assertFalse(registeredReceivers.isEmpty());

        boolean receiverFound = false;
        for (ShadowApplication.Wrapper wrapper : registeredReceivers) {
            if (!receiverFound)
                receiverFound = RuthlessPriorityReminder.class.getSimpleName().equals(
                        wrapper.broadcastReceiver.getClass().getSimpleName());
        }

        assertTrue(receiverFound);
    }

}