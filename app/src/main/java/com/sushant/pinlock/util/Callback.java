package com.sushant.pinlock.util;

import com.sushant.pinlock.exception.ErrorBundle;

/**
 * Created by braindigit on 3/10/16.
 */
public interface Callback<T> {

    void onSuccess(T result);

    void onError(ErrorBundle e);
}
