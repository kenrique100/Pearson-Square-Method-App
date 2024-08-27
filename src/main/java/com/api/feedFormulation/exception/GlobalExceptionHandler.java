package com.api.feedFormulation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the application. This class handles exceptions
 * thrown by any part of the application and returns appropriate HTTP responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // Handles InvalidInputException and returns a BAD_REQUEST response.

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
        // Return a BAD_REQUEST response with the exception message
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
