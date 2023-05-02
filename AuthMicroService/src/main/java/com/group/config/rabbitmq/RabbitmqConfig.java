package com.group.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String authDirectExchange = "auth-direct-exchange";
    private String resetPasswordBindingKey = "reset-password-binding-key";
    private String resetPasswordQueue = "reset-password-queue";

    @Bean
    DirectExchange authDirectExchange(){
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue resetPasswordQueue(){
        return new Queue(resetPasswordQueue);
    }
    @Bean
    public Binding resetPasswordBindingKey(final Queue resetPasswordQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(resetPasswordQueue).to(authDirectExchange).with(resetPasswordBindingKey);
    }
}
