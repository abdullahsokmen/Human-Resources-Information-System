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
    MUSTERI_BULUNAMADI(1003, "Aradınız müşteri sistemde kayıt değildir.", INTERNAL_SERVER_ERROR),
    REGISTER_ERROR_PASSWORD_UNMATCH(1004, "Girilen parolalar uyusmamaktadir.", BAD_REQUEST),
    REGISTER_ERROR_USERNAME(1005,"Bu kullanici adi daha once alinmistir." ,BAD_REQUEST),
    LOGIN_ERROR_USERNAME_PASSWORD(1006,"Kullanici adi ya da sifre hatalidir." ,BAD_REQUEST),
    INVALID_TOKEN(1007,"Gecersiz token." ,BAD_REQUEST),
    METHOD_MIS_MATCH_ERROR(2002,"Giris yaptiginiz deger, istenilen degerle uyusmamaktadir.",BAD_REQUEST),
    INVALID_PARAMETER(3001, "Geçersiz parametre giriş yaptınız", BAD_REQUEST);
    private int code;
    private String message;
    private HttpStatus httpStatus;
}
