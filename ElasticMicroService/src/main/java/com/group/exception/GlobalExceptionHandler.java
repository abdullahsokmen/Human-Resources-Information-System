package com.group.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     *Tum istinalarin uzerinden gectigi bir method olusturuyorum ve hata mesajini burada donuyorum.
     */
    private ErrorMessage createErrorMessage(EErrorType errorType,Exception exception){
        System.out.println("Hata olustu.....: "+exception.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }

    /**
     * @ExceptionHandler ->Uygulama icinde olusacak hatanin turunu bizden alarak onun yakalamasini saglar.
     * Boylece yakaladigi istisnayi methoda gecer.
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleException(Exception exception){
        return ResponseEntity.badRequest().body("Uygulamada beklenmeyen bir hata olustu....: "+exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ElasticServiceException.class)
    public ResponseEntity<ErrorMessage> handleSatisManagerException(ElasticServiceException exception){
        EErrorType errorType = exception.getErrorType();
        HttpStatus httpStatus = errorType.getHttpStatus();
        return new ResponseEntity<>(createErrorMessage(errorType,exception),httpStatus);
    }

    @ResponseBody
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorMessage> handleInvalidFormatException(InvalidFormatException exception){
        EErrorType errorType = EErrorType.INVALID_PARAMETER;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<ErrorMessage> handle(MethodArgumentTypeMismatchException exception){
        EErrorType errorType = EErrorType.METHOD_MIS_MATCH_ERROR;
        return new ResponseEntity<>(createErrorMessage(errorType,exception),errorType.getHttpStatus());
    }
}
