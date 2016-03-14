package com.sushant.pinlock.exception;

/**
 * Created by braindigit on 3/10/16.
 */
public interface ErrorBundle {

    Exception getException();

    String getErrorMessage();

}
