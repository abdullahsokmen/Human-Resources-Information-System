package com.group.utility;

import java.util.UUID;

public class Generator {

    public static String randomActivationCode() {
        Long number = (long) Math.floor(Math.random() * 9_000L) + 1_000L;
        String accountNumber =number.toString();
        return accountNumber;
    }
    public static String randomPassword() {
        String code= UUID.randomUUID().toString();
        String [] data =code.split("-");
        String newCode="";
        for(String  string :data){
            newCode+=string.charAt(0);
            newCode+=string.charAt(1);
        }
        return newCode;
    }
}
