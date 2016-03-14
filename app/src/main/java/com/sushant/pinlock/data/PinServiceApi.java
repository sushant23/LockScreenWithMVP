package com.sushant.pinlock.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sushant.pinlock.util.Callback;

/**
 * Created by braindigit on 3/10/16.
 */
public interface PinServiceApi {
    void getSavedPin(@NonNull Callback<String> callback);

    void savePin(@NonNull String pin, @Nullable Callback<Boolean> pinSavedCallback);
}
