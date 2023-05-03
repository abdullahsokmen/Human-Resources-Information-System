package com.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RequestMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RequestMicroServiceApplication.class);
    }
}