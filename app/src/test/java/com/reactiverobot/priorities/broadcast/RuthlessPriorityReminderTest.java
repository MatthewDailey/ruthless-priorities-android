package com.reactiverobot.priorities.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.reactiverobot.priorities.prefs.RuthlessPrefs;
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
        assertTrue(getRuthlessPriorityReminderReceiver().isPresent());
    }

    private Optional<BroadcastReceiver> getRuthlessPriorityReminderReceiver() {
        List<ShadowApplication.Wrapper> registeredReceivers =
                shadowOf(context).getRegisteredReceivers();

        assertFalse(registeredReceivers.isEmpty());

        for (ShadowApplication.Wrapper wrapper : registeredReceivers) {
            if (RuthlessPriorityReminder.class.getSimpleName().equals(
                        wrapper.broadcastReceiver.getClass().getSimpleName())) {
                return Optional.of(wrapper.broadcastReceiver);
            }
        }
        return Optional.absent();
    }

    @Test
    public void testBroadcastCausesNotificationToLaunchAndHide() {
        Intent broadcastIntent = new Intent(context, RuthlessPriorityReminder.class);
        broadcastIntent.setAction(RuthlessPriorityReminder.SHOW_NOTIFICATION_ACTION);

        getRuthlessPriorityReminderReceiver().get().onReceive(context, broadcastIntent);

        ShadowNotificationManager shadowNotificationManager = shadowOf(
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE));
        List<Notification> allNotifications = shadowNotificationManager.getAllNotifications();
        assertEquals(1, allNotifications.size());

        Intent broadcastHideIntent = new Intent(context, RuthlessPriorityReminder.class);
        broadcastHideIntent.setAction(RuthlessPriorityReminder.HIDE_NOTIFICATION_ACTION);

        getRuthlessPriorityReminderReceiver().get().onReceive(context, broadcastHideIntent);

        allNotifications = shadowNotificationManager.getAllNotifications();
        assertEquals(0, allNotifications.size());
    }

    @Test
    public void testBroadcastCausesPrioritiesToClear() {
        Intent broadcastIntent = new Intent(context, RuthlessPriorityReminder.class);
        broadcastIntent.setAction(RuthlessPriorityReminder.SHOW_NOTIFICATION_ACTION);

        RuthlessPrefs prefs = RuthlessPrefs.fromContext(context);
        prefs.setTopPriorities(Lists.newArrayList("p1", "p2"));
        prefs.setNotPriorities(Lists.newArrayList("n1", "n2"));

        getRuthlessPriorityReminderReceiver().get().onReceive(context, broadcastIntent);

        assertTrue(prefs.getTopPriorities().isEmpty());
        assertTrue(prefs.getNotPriorities().isEmpty());
    }

}
