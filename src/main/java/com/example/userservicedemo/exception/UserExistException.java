package com.example.userservicedemo.exception;

public class UserExistException extends RuntimeException{
    private String errorCode;

    public UserExistException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
