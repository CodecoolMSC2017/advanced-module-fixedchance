package com.codecool.fixedchance.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "This user is already in the system with an other role.")
public class WrongRoleSelectionException extends Exception {

    public WrongRoleSelectionException() {
    }

    public WrongRoleSelectionException(String message) {
        super(message);
    }
}
