package com.group.rabbitmq.consumer;

import com.group.rabbitmq.model.AdminPasswordSenderModel;
import com.group.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminPasswordConsumer {
    private final MailSenderService mailSenderService;


    @RabbitListener(queues = "admin-password-queue")
    public void sendAdminPassword(AdminPasswordSenderModel model){
        mailSenderService.sendAdminPassword(model);
    }
}
