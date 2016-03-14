package com.sushant.pinlock.exception;

import android.content.Context;

import com.sushant.pinlock.R;

/**
 * Created by braindigit on 3/10/16.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory(){

    }

    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);


        return message;
    }
}
