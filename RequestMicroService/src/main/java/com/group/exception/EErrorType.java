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
       INVALID_TOKEN(1004,"Invalid token",HttpStatus.BAD_REQUEST),
    NOT_DECODED(1005,"Token can not decoded", INTERNAL_SERVER_ERROR),
    HTTP_MESSAGE_NOT_READABLE(1006,"Http message not readable",BAD_REQUEST),
    UNEXPECTED_ERROR(1008,"Unexpected Error Occured", INTERNAL_SERVER_ERROR),
    PERSONAL_NOT_EXIST(200,"Personal Not Exist", BAD_REQUEST),
    ADVANCE_PAYMENT_NOT_EXIST(201,"Advance Payment Not Exist", BAD_REQUEST),
    DAYOFF_NOT_EXIST(202,"DayOff request Not Exist", BAD_REQUEST),
    EXPENDITURE_NOT_EXIST(203,"Expenditure request Not Exist", BAD_REQUEST),
    SALARY_PRICE_ERROR(5000,"Advance payment request cannot be more than 3x your salary", BAD_REQUEST),
    USER_NOT_ACTIVE(1105,"User not active", BAD_REQUEST),
    PENDING_ERROR(1106,"Can not be requested", BAD_REQUEST),










    ;


    private int code;
    private String message;
    private HttpStatus httpStatus;
}
