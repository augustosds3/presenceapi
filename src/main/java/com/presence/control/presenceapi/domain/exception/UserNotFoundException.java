package com.presence.control.presenceapi.domain.exception;

public class UserNotFoundException  extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

}