package com.group.rabbitmq.producer;

import com.group.rabbitmq.model.ResetPasswordModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResetPasswordProducer {
    private final RabbitTemplate rabbitTemplate;
    private String authDirectExchange = "auth-direct-exchange";
    private String resetPasswordBindingKey = "reset-password-binding-key";

    public void sendNewPassword(ResetPasswordModel model){
        rabbitTemplate.convertAndSend(authDirectExchange,resetPasswordBindingKey,model);
    }
}
