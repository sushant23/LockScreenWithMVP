package com.sushant.pinlock;

import android.content.Intent;
import android.os.IBinder;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ServiceTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import com.sushant.pinlock.service.LockerService;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

/**
 * Created by braindigit on 3/14/16.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class LockerServiceTest {
//
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();


    @Test
    public void testBroadCastShowsView() throws TimeoutException {
//         Create the service Intent.
        Intent serviceIntent =
                new Intent(InstrumentationRegistry.getTargetContext(),
                        LockerService.class);

        IBinder binder = mServiceRule.bindService(serviceIntent);

        final LockerService lockerService = ((LockerService.MyBinder)binder).getService();



        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                lockerService.screenOnBradCastReceived();
                assertTrue(lockerService.hasView());
            }
        });

    }
}
