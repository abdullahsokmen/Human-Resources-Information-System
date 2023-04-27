package com.group.rabbitmq.consumer;

import com.group.rabbitmq.model.PersonalPasswordSenderModel;
import com.group.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalPasswordConsumer {
    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "personal-password-queue")
    public void sendPersonalPassword(PersonalPasswordSenderModel model){
        mailSenderService.sendPersonalPassword(model);
    }

}
