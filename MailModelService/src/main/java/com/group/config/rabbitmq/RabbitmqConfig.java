package com.group.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
    private String registerMailQueue = "register-mail-queue";
    @Bean
    Queue mailQueue(){
        return new Queue(registerMailQueue);
    }
}
