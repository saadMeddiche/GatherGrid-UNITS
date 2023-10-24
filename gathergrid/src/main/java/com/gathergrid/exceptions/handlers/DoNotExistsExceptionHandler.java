package com.gathergrid.exceptions.handlers;

import java.util.List;

import com.gathergrid.exceptions.costums.DoNotExistsException;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

public class DoNotExistsExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Exception exception, HttpServletRequest request) {

        DoNotExistsException DoNotExistsExceptionHandler = (DoNotExistsException) exception;

        request.getSession().setAttribute("errors", List.of(DoNotExistsExceptionHandler.getError()));

        request.setAttribute("errors", List.of(DoNotExistsExceptionHandler.getError()));

    }

    @Override
    public String getMessage() {
        return "This Record Do Not Exist";
    }
}
