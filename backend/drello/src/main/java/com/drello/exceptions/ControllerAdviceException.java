package com.drello.exceptions;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * A controller advice class that handles exceptions and provides custom
 * responses.
 */
@ControllerAdvice
public class ControllerAdviceException extends ResponseEntityExceptionHandler {
    /**
     * Handles the runtime exception and returns a ResponseEntity object.
     *
     * @param ex      the runtime exception
     * @param request the web request
     * @return a ResponseEntity object containing the error details
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRunTimeException(
            RuntimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the exception when an entity is not found.
     *
     * @param ex      the exception that was thrown
     * @param request the web request
     * @return the response entity containing the error information
     */

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("message", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
