package com.app.infrastructure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.domain.util.IllegalDateFormatException;
import com.app.domain.util.PriceNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(IllegalDateFormatException.class)
    public ResponseEntity<String> handleIllegalDateFormat(IllegalDateFormatException iae) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: The date format introduced is Illegal");
    }

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity<String> noPriceFound(PriceNotFoundException iae) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("ERROR: No Price was found");
    }
}
