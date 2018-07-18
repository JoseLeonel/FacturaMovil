package com.emprendesoftcr.fisco;

public class GettingTokenException extends Exception {

    private final int statusCode;

    public static GettingTokenException instance(String message, int statusCode) {
        return new GettingTokenException(message, statusCode);
    }

    public static GettingTokenException instance(String message, Throwable throwable, int statusCode) {
        return new GettingTokenException(message, throwable, statusCode);
    }

    private GettingTokenException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    private GettingTokenException(String message, Throwable throwable, int statusCode) {
        super(message, throwable);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
