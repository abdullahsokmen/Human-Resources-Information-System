package com.group.rabbitmq.producer;

import com.group.rabbitmq.model.AdminPasswordSenderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPasswordProducer {

    private final RabbitTemplate rabbitTemplate;
    private String adminDirectExchange = "admin-direct-exchange";
    private String adminPasswordBindingKey = "admin-password-binding-key";

    public void sendAdminPassword(AdminPasswordSenderModel model){
        rabbitTemplate.convertAndSend(adminDirectExchange,adminPasswordBindingKey,model);
    }
}
