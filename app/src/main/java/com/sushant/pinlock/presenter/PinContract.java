package com.sushant.pinlock.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.IOException;

/**
 * Created by braindigit on 3/10/16.
 */
public interface PinContract {

    interface View {

        void showPinMismatchError(String message);

        Context getContext();

        void correctPinEntered();
    }

    interface UserActionsListener {

        void tryUnlock(String pin);
    }
}
