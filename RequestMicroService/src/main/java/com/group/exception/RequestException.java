package com.group.exception;

import lombok.Getter;

@Getter
public class RequestException extends RuntimeException{
    private final EErrorType errorType;

    public RequestException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public RequestException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
