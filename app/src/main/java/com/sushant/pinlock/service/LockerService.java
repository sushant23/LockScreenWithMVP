package com.sushant.pinlock.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.sushant.pinlock.util.KeyGuardUtil;
import com.sushant.pinlock.view.MainViewPager;

/**
 * Created by braindigit on 3/14/16.
 */
public class LockerService extends Service {

    private static LockerService lockerService = null;

    private MyBinder myBinder = new MyBinder();

    private WindowManager windowManager;
    private BroadcastReceiver callReceiver;
    private WindowManager.LayoutParams playerParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ERROR,
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            PixelFormat.TRANSLUCENT);

    private boolean isViewAttached = false;
    private MainViewPager mainViewPager;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public WindowManager getWindowManager() {
        return windowManager;
    }

    public boolean hasView() {
        return isViewAttached;
    }

    public class MyBinder extends Binder{

        public LockerService getService(){
            return LockerService.this;
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lockerService = this;
        playerParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN|
                WindowManager.LayoutParams.FLAG_FULLSCREEN|
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD|
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        KeyGuardUtil.init(this);
        mainViewPager = new MainViewPager(this);
        callReceiver = new CallsReceiver();
        IntentFilter callFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
        registerReceiver(callReceiver, callFilter);
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        registerReceiver(lockScreenReceiver, filter);

    }

    public static LockerService getInstance() {
        return lockerService;
    }

    BroadcastReceiver lockScreenReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

                screenOnBradCastReceived();
            }
        }
    };

    public void screenOnBradCastReceived() {
        showView();
    }


    class CallsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                removeView();
            } else {
                screenOnBradCastReceived();
            }
        }
    }

    public void removeView() {
        if (isViewAttached) {
            windowManager.removeView(mainViewPager);
            KeyGuardUtil.getInstance().disableKeyGuard();
            isViewAttached = false;
        }
    }

    public void showView() {
        if (!isViewAttached) {
            windowManager.addView(mainViewPager, playerParams);
            KeyGuardUtil.getInstance().disableKeyGuard();
            isViewAttached = true;
        }
    }
}
