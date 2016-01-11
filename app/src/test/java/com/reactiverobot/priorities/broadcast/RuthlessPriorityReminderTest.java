package com.reactiverobot.priorities.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.reactiverobot.priorities.robolectric.AbstractRoboTest;

import org.junit.Test;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowNotificationManager;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

public class RuthlessPriorityReminderTest extends AbstractRoboTest {

    @Test
    public void testBroadcastRecieverRegistered() {
        List<ShadowApplication.Wrapper> registeredReceivers =
                shadowOf(context).getRegisteredReceivers();

        assertFalse(registeredReceivers.isEmpty());

        boolean receiverFound = false;
        for (ShadowApplication.Wrapper wrapper : registeredReceivers) {
            if (!receiverFound) {
                receiverFound = RuthlessPriorityReminder.class.getSimpleName().equals(
                        wrapper.broadcastReceiver.getClass().getSimpleName());
            }
        }

        assertTrue(receiverFound);
    }

    @Test
    public void testBroadcastCausesNotificationToLaunch() {
        Intent broadcastIntent = new Intent(null, RuthlessPriorityReminder.class);
        List<BroadcastReceiver> receiversForIntent =
                shadowOf(context).getReceiversForIntent(broadcastIntent);
        assertEquals(1, receiversForIntent.size());

        receiversForIntent.get(0).onReceive(context, broadcastIntent);

        ShadowNotificationManager shadowNotificationManager = shadowOf(
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE));
        List<Notification> allNotifications = shadowNotificationManager.getAllNotifications();
        assertEquals(1, allNotifications.size());
    }

}