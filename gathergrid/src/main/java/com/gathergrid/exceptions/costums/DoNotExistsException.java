package com.gathergrid.exceptions.costums;

public class DoNotExistsException extends RuntimeException {

    private final String error;

    public DoNotExistsException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
