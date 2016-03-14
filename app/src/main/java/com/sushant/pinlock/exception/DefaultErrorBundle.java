package com.sushant.pinlock.exception;

/**
 * Created by braindigit on 3/10/16.
 */
public class DefaultErrorBundle implements ErrorBundle{

    private Exception exception;
    private static final String DEFAULT_ERROR_MESSAGE = "Unknown Error";

    public DefaultErrorBundle(Exception exception){
        this.exception = exception;
    }

    @Override
    public Exception getException() {
        return exception;
    }

    @Override
    public String getErrorMessage() {
        return (exception != null) ? this.exception.getMessage() : DEFAULT_ERROR_MESSAGE;
    }
}
