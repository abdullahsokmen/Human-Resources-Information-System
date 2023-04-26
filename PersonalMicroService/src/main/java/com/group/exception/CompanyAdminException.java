package com.group.exception;

import lombok.Getter;

@Getter
public class CompanyAdminException extends RuntimeException{
    private final EErrorType errorType;

    public CompanyAdminException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public CompanyAdminException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
