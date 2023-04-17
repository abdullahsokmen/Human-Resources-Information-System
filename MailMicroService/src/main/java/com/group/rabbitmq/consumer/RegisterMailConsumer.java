package com.group.rabbitmq.consumer;

import com.group.rabbitmq.model.ActivateStatusModel;
import com.group.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterMailConsumer {

    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "register-mail-queue")
    public void sendActivationCode(ActivateStatusModel model){
        mailSenderService.sendMail(model);
    }
}
