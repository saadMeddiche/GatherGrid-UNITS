package com.gathergrid.exceptions.costums;

public class NotMatchedException extends RuntimeException {

    private final String error;

    public NotMatchedException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
