package com.group.exception;

import lombok.Getter;

@Getter
public class AuthManagerException extends RuntimeException{
    private final EErrorType errorType;

    public AuthManagerException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AuthManagerException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
