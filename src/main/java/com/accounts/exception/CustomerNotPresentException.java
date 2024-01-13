package com.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotPresentException extends RuntimeException{

    public CustomerNotPresentException(String message) {
        super(message);
    }
}
