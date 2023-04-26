package com.group.rabbitmq.producer;

import com.group.rabbitmq.model.ActivateStatusModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyAdminMailProducer {

    private final RabbitTemplate rabbitTemplate;

    private String companyAdminDirectExchange = "company-admin-direct-exchange";
    private String companyAdminPasswordBindingKey = "company-admin-password-binding-key";

    public void sendActivationCode(ActivateStatusModel model){
        rabbitTemplate.convertAndSend(companyAdminDirectExchange,companyAdminPasswordBindingKey,model);
    }

}
