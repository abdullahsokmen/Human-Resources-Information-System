package com.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ElasticMicroServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElasticMicroServiceApplication.class);
    }
}