package com.sushant.pinlock.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.sushant.pinlock.util.Callback;

/**
 * Created by braindigit on 3/10/16.
 */
public class PinRepositoryImpl implements PinRepository {

    private PinServiceApi pinServiceApi;

    public PinRepositoryImpl(PinServiceApi pinServiceApi) {
        this.pinServiceApi = pinServiceApi;
    }

    @Override
    public void getSavedPin(@NonNull Callback<String> callback) {
        pinServiceApi.getSavedPin(callback);
    }

    @Override
    public void savePin(@NonNull String pin, @Nullable Callback<Boolean> pinSavedCallback) {
        pinServiceApi.savePin(pin, pinSavedCallback);
    }
}
