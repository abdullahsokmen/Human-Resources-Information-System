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
    INVALID_PARAMETER(1000,"Invalid parameter entered", BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(1001,"The value you entered does not match the desired value.", BAD_REQUEST),
    METHOD_NOT_VALID_ARGUMENT_ERROR(1002,"Missing parameter submission in URL",BAD_REQUEST),
    USER_NOT_FOUND(2001,"User not found", BAD_REQUEST),
    INVALID_TOKEN(1004,"Invalid token",HttpStatus.BAD_REQUEST),
    NOT_DECODED(1005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    HTTP_MESSAGE_NOT_READABLE(1006,"Http message not readable",BAD_REQUEST),
    TOKEN_NOT_CREATED(1007,"Token can not be created", INTERNAL_SERVER_ERROR),
    UNEXPECTED_ERROR(1008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR),



    REGISTER_ERROR_PASSWORD_UNMATCH(1100,"Passwords not matched", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1101,"This Username already taken", BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1102,"Username or Password are incorrect", BAD_REQUEST),




    EMAIL_ALREADY_TAKEN(3000,"User not found", BAD_REQUEST),
    PHONE_ALREADY_TAKEN(3001,"User not found", BAD_REQUEST),

    MAIL_SEND_ERROR(102,"Mail could not send", INTERNAL_SERVER_ERROR),


    ;


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
