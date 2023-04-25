package com.group.exception;

import lombok.Getter;

@Getter
public class CompanyManagerException extends RuntimeException{
    private final EErrorType errorType;

    public CompanyManagerException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyManagerException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
