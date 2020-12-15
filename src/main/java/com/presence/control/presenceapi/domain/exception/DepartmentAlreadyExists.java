package com.presence.control.presenceapi.domain.exception;

public class DepartmentAlreadyExists extends RuntimeException {

    public DepartmentAlreadyExists(String message){
        super(message);
    }

}
