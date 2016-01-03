package com.reactiverobot.priorities;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class RobolectricWorksTest {

    @Test
    public void basicPassingTest() {
        // It passes by default!
    }
}
