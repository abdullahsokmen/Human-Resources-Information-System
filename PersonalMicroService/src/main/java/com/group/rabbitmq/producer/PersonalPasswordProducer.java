package com.group.rabbitmq.producer;

import com.group.rabbitmq.model.PersonalPasswordSenderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalPasswordProducer {

    private final RabbitTemplate rabbitTemplate;
    private String personalDirectExchange = "personal-direct-exchange";
    private String personalPasswordBindingKey = "personal-password-binding-key";

    public void sendPersonalPassword(PersonalPasswordSenderModel model){
        rabbitTemplate.convertAndSend(personalDirectExchange,personalPasswordBindingKey,model);
    }
}
