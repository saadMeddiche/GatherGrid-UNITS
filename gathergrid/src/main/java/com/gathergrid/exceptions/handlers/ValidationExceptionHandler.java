package com.gathergrid.exceptions.handlers;

import java.util.List;

import com.gathergrid.exceptions.costums.ValidationException;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class ValidationExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Exception exception, HttpServletRequest request) {

        ValidationException validationException = (ValidationException) exception;

        request.getSession().setAttribute("errors", List.of(validationException.getErrors()));

        request.setAttribute("errors", validationException.getErrors());

    }

    @Override
    public String getMessage() {
        return "Some Thing Went Wrong With The Validation";
    }

}
