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
    PERSONAL_NOT_FOUND(1003, "The personnel you have called is not registered in the system..", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004, "Entered passwords do not match.", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"This username has already been taken." ,BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Username or password is incorrect." ,BAD_REQUEST),
    INVALID_TOKEN(1007,"Invalid token." ,BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"The value you entered does not match the desired value.",BAD_REQUEST),
    INVALID_PARAMETER(3001, "Invalid parameter entered", BAD_REQUEST),
    NOT_DECODED(1008,"Token can not decoded", INTERNAL_SERVER_ERROR),
    DAY_OFF_NOT_FOUND(1009,"Day off not found", BAD_REQUEST),
    ADVANCE_PAYMENT_NOT_FOUND(1010,"Advance payment not found", BAD_REQUEST),
    EXPENDITURE_NOT_FOUND(1011,"Expenditure not found", BAD_REQUEST),

    ;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
