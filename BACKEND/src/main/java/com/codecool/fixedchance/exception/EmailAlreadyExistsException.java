package com.codecool.fixedchance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This email address is already in use!")
public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException() {
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
