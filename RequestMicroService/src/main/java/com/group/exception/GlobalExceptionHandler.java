package com.group.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ErrorMessage createErrorMessage(EErrorType errorType, Exception exception){
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

  /*  @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException exception){
        EErrorType errorType = EErrorType.UNEXPECTED_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }*/

    @ExceptionHandler(RequestServiceException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(RequestServiceException ex){

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<ErrorMessage> handleManagerException(RequestException ex){

        EErrorType errorType=ex.getErrorType();
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType,ex),httpStatus);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        EErrorType errorType=EErrorType.INVALID_PARAMETER;
        List<String> fields=new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(e->fields.add(e.getField()+": "+e.getDefaultMessage()));
        ErrorMessage errorMessage=createErrorMessage(errorType,exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage,errorType.getHttpStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        EErrorType errorType = EErrorType.HTTP_MESSAGE_NOT_READABLE;
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        EErrorType errorType = EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {
        EErrorType errorType = EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        EErrorType errorType = EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ErrorMessage> handlePsqlException(DataIntegrityViolationException exception){
        EErrorType errorType=EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }

 /*   @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
        EErrorType errorType = EErrorType.UNEXPECTED_ERROR;
        List<String> fields = new ArrayList<>();
        fields.add(exception.getMessage());
        ErrorMessage errorMessage = createErrorMessage(errorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(createErrorMessage(errorType, exception), errorType.getHttpStatus());
    }*/
}
