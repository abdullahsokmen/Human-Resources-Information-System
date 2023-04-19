package com.group.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EErrorType {

    REGISTER_ERROR_PASSWORD_UNMATCH(1004,"Passwords not matched", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"This Username already taken", BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Username or Password are incorrect", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(2003,"Missing parameter submission in URL",BAD_REQUEST),
    INVALID_PARAMETER(3001,"Invalid parameter entered", BAD_REQUEST),
    USER_NOT_FOUND(3001,"User not found", BAD_REQUEST),
    INVALID_TOKEN(3002,"Invalid token",HttpStatus.BAD_REQUEST),

    EMAIL_ALREADY_TAKEN(3001,"User not found", BAD_REQUEST),
    PHONE_ALREADY_TAKEN(3001,"User not found", BAD_REQUEST),

    ACTIVATE_CODE_ERROR(4113,"Activation Code Error",HttpStatus.BAD_REQUEST),


    ;


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
