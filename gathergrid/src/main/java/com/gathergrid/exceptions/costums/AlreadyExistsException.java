package com.gathergrid.exceptions.costums;

public class AlreadyExistsException extends RuntimeException {

    private final String error;

    public AlreadyExistsException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
