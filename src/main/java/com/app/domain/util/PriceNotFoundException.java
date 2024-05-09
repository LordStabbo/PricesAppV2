package com.app.domain.util;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(String message) {
        super(message);
    }
}
