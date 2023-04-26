package com.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonalMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalMicroServiceApplication.class);
    }
}