package com.codecool.fixedchance.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Please fill in all fields")
public class MissingRegistrationInfoException extends Exception {

    public MissingRegistrationInfoException(){
    }

    public MissingRegistrationInfoException(String message) {
        super(message);
    }
}