package com.gathergrid.exceptions.handlers;

import java.util.List;

import com.gathergrid.exceptions.costums.AlreadyExistsException;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class AlreadyExistsExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Exception exception, HttpServletRequest request) {

        AlreadyExistsException alreadyExistsExceptionHandler = (AlreadyExistsException) exception;

        request.getSession().setAttribute("errors", List.of(alreadyExistsExceptionHandler.getError()));

        request.setAttribute("errors", List.of(alreadyExistsExceptionHandler.getError()));

    }

    @Override
    public String getMessage() {
        return "This Record Already Exists";
    }

}
