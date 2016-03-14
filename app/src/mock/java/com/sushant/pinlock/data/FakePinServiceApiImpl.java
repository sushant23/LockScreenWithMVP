package com.sushant.pinlock.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sushant.pinlock.util.Callback;

/**
 * Created by braindigit on 3/10/16.
 */
public class FakePinServiceApiImpl implements PinServiceApi {

    private static String SAVED_PIN = "1234";

    @Override
    public void getSavedPin(@NonNull Callback<String> callback) {
        callback.onSuccess(SAVED_PIN);
    }

    @Override
    public void savePin(@NonNull String pin, @Nullable Callback<Boolean> pinSavedCallback) {
        if (pinSavedCallback != null)
            pinSavedCallback.onSuccess(true);
    }
}
