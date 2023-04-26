package com.group.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    private String registerMailQueue = "register-mail-queue";
    private String personalPasswordQueue = "personal-password-queue";


    @Bean
    Queue personalPasswordQueue(){
        return new Queue(personalPasswordQueue);
    }
    @Bean
    Queue registerMailQueue(){
        return new Queue(registerMailQueue);
    }

}
