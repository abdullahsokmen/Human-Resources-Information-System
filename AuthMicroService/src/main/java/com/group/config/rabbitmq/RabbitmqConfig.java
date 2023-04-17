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
    private String registerMailBindingKey = "register-mail-binding-key";
    private String registerMailQueue = "register-mail-queue";

    @Bean
    DirectExchange authDirectExchange(){
        return new DirectExchange(authDirectExchange);
    }
    @Bean
    Queue registerMailQueue(){
        return new Queue(registerMailQueue);
    }
    @Bean
    public Binding registerMailBindingKey(final Queue registerMailQueue, final DirectExchange authDirectExchange){
        return BindingBuilder.bind(registerMailQueue).to(authDirectExchange).with(registerMailBindingKey);
    }
}
