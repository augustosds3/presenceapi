package com.presence.control.presenceapi.domain.exception;

public class LocalAlreadyExistsException extends RuntimeException {

    public LocalAlreadyExistsException(String message){
        super(message);
    }

}
