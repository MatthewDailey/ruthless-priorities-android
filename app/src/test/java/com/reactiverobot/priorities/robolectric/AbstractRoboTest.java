package com.reactiverobot.priorities.robolectric;

import android.app.Application;

import com.reactiverobot.priorities.BuildConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import roboguice.RoboGuice;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public abstract class AbstractRoboTest {

    protected Application context;

    @Before
    public void setup() {
        this.context = RuntimeEnvironment.application;
    }

    @After
    public void teardown() {
        RoboGuice.Util.reset();
        Robolectric.reset();
    }
}
