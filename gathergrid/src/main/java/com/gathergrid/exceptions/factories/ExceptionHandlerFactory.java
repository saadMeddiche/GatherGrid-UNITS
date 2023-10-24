package com.gathergrid.exceptions.factories;

import com.gathergrid.exceptions.costums.AlreadyExistsException;
import com.gathergrid.exceptions.costums.DoNotExistsException;
import com.gathergrid.exceptions.costums.NotMatchedException;
import com.gathergrid.exceptions.costums.ValidationException;
import com.gathergrid.exceptions.handlers.AlreadyExistsExceptionHandler;
import com.gathergrid.exceptions.handlers.DoNotExistsExceptionHandler;
import com.gathergrid.exceptions.handlers.NotMatchedExceptionHandler;
import com.gathergrid.exceptions.handlers.ValidationExceptionHandler;
import com.gathergrid.exceptions.interfaces.ExceptionHandler;

public class ExceptionHandlerFactory {

    public static ExceptionHandler getExceptionHandler(Exception exception) {

        if (exception instanceof ValidationException) {
            return new ValidationExceptionHandler();
        }

        if (exception instanceof AlreadyExistsException) {
            return new AlreadyExistsExceptionHandler();
        }

        if (exception instanceof DoNotExistsException) {
            return new DoNotExistsExceptionHandler();
        }

        if (exception instanceof NotMatchedException) {
            return new NotMatchedExceptionHandler();
        }

        return null;
    }
}
