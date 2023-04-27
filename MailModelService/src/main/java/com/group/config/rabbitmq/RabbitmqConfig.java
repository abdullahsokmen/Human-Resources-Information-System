package com.group.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    private String adminPasswordQueue = "admin-password-queue";
    private String personalPasswordQueue = "personal-password-queue";
    private String companyAdminMailQueue = "company-admin-mail-queue";

    @Bean
    Queue adminPasswordQueue(){
        return new Queue(adminPasswordQueue);
    }

    @Bean
    Queue personalPasswordQueue(){
        return new Queue(personalPasswordQueue);
    }
    @Bean
    Queue companyAdminMailQueue(){
        return new Queue(companyAdminMailQueue);
    }

}