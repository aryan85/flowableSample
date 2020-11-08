package com.smartmind.flowable.exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause){
        super(message,cause);
    }

}
