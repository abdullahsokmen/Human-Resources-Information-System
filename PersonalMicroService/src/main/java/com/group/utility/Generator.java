package com.group.utility;

public class Generator {

    public static String randomActivationCode() {
        Long number = (long) Math.floor(Math.random() * 9_000L) + 1_000L;
        String accountNumber =number.toString();
        return accountNumber;
    }
}
