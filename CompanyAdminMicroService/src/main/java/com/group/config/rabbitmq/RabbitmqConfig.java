package com.group.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    private String companyAdminDirectExchange = "company-admin-direct-exchange";
    private String companyAdminPasswordBindingKey = "company-admin-password-binding-key";
    private String companyAdminMailQueue = "company-admin-mail-queue";

    @Bean
    DirectExchange companyAdminDirectExchange(){
        return new DirectExchange(companyAdminDirectExchange);
    }
    @Bean
    Queue companyAdminMailQueue(){
        return new Queue(companyAdminMailQueue);
    }
    @Bean
    public Binding companyAdminPasswordBindingKey(final Queue companyAdminMailQueue, final DirectExchange companyAdminDirectExchange){
        return BindingBuilder.bind(companyAdminMailQueue).to(companyAdminDirectExchange).with(companyAdminPasswordBindingKey);
    }
}
