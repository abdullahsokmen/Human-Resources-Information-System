package com.group.exception;

import lombok.Getter;

@Getter
public class AdminServiceException extends RuntimeException{
    private final EErrorType errorType;

    public AdminServiceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdminServiceException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
