package com.group.exception;

import lombok.Getter;

@Getter
public class ElasticServiceException extends RuntimeException{

    private final EErrorType errorType;

    public ElasticServiceException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;

    }
    public ElasticServiceException(EErrorType errorType, String message){
        super(message);
        this.errorType=errorType;

    }

}
