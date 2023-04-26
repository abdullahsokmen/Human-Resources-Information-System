package com.group.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String personalDirectExchange = "personal-direct-exchange";
    private String personalPasswordBindingKey = "personal-password-binding-key";
    private String personalPasswordQueue = "personal-password-queue";

    @Bean
    DirectExchange personalDirectExchange(){
        return new DirectExchange(personalDirectExchange);
    }
    @Bean
    Queue personalPasswordQueue(){
        return new Queue(personalPasswordQueue);
    }
    @Bean
    public Binding registerMailBindingKey(final Queue personalPasswordQueue, final DirectExchange personalDirectExchange){
        return BindingBuilder.bind(personalPasswordQueue).to(personalDirectExchange).with(personalPasswordBindingKey);
    }
}
