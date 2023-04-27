package com.group.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String adminDirectExchange = "admin-direct-exchange";
    private String adminPasswordBindingKey = "admin-password-binding-key";
    private String adminPasswordQueue = "admin-password-queue";

    @Bean
    DirectExchange adminDirectExchange(){
        return new DirectExchange(adminDirectExchange);
    }
    @Bean
    Queue adminPasswordQueue(){
        return new Queue(adminPasswordQueue);
    }
    @Bean
    public Binding adminPasswordBindingKey(final Queue adminPasswordQueue, final DirectExchange adminDirectExchange){
        return BindingBuilder.bind(adminPasswordQueue).to(adminDirectExchange).with(adminPasswordBindingKey);
    }
}
