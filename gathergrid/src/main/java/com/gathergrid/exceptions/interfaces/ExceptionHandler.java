package com.gathergrid.exceptions.interfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface ExceptionHandler {

    void handleException(Exception exception, HttpServletRequest request);

    String getMessage();
}
