package com.travel.model;

public class TourException extends Exception {
    public TourException() {
        super();
    }

    public TourException(String message) {
        super(message);
    }

    public TourException(String message, Throwable cause) {
        super(message, cause);
    }

    public TourException(Throwable cause) {
        super(cause);
    }

    protected TourException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
