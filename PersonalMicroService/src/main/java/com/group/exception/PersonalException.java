package com.group.exception;

import lombok.Getter;

@Getter
public class PersonalException extends RuntimeException{
    private final EErrorType errorType;

    public PersonalException(EErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public PersonalException(EErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }

}
