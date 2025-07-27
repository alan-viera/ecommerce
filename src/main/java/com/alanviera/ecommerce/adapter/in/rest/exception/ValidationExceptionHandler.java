package com.alanviera.ecommerce.adapter.in.rest.exception;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Invalid value for parameter '" + ex.getName() + "': " + ex.getValue());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<Object> handleConversionFailure(ConversionFailedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Parameter conversion failed: " + ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
