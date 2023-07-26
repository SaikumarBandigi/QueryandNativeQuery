package com.sonu.collections.atm;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CustomerAuthenticationException extends RuntimeException {
    public CustomerAuthenticationException(String message) {
        super(message);
    }
}
