package com.group.exception;

import lombok.Getter;

@Getter
public class RequestServiceException extends RuntimeException{
    private final EErrorType errorType;

    public RequestServiceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public RequestServiceException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
