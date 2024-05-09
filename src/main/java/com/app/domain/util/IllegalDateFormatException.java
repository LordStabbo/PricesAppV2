package com.app.domain.util;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class IllegalDateFormatException extends RuntimeException {
    public IllegalDateFormatException(String message) {
        super(message);
    }
}
