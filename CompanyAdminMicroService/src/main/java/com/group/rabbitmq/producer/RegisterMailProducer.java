package com.group.rabbitmq.producer;

import com.group.rabbitmq.model.ActivateStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMailProducer {

    private final RabbitTemplate rabbitTemplate;

    private String companyAdminDirectExchange = "company-admin-direct-exchange";
    private String registerMailBindingKey = "register-mail-binding-key";

    public void sendActivationCode(ActivateStatusModel model){
        rabbitTemplate.convertAndSend(companyAdminDirectExchange,registerMailBindingKey,model);
    }

}
