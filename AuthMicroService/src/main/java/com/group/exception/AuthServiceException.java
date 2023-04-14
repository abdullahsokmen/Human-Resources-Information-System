package com.group.exception;

import lombok.Getter;

@Getter
public class AuthServiceException extends RuntimeException{
    private final EErrorType errorType;

    public AuthServiceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AuthServiceException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
