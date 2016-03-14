package com.sushant.pinlock.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sushant.pinlock.util.Callback;

/**
 * Created by braindigit on 3/14/16.
 */
public class PinServiceApiImpl implements PinServiceApi{
    @Override
    public void getSavedPin(@NonNull Callback<String> callback) {
        callback.onSuccess("1234");
    }

    @Override
    public void savePin(@NonNull String pin, @Nullable Callback<Boolean> pinSavedCallback) {
        if(pinSavedCallback != null)
            pinSavedCallback.onSuccess(true);
    }
}
