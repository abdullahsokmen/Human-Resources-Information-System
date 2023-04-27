package com.group.rabbitmq.consumer;

import com.group.rabbitmq.model.CompanyAdminPasswordModel;
import com.group.rabbitmq.model.PersonalPasswordSenderModel;
import com.group.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyAdminPasswordConsumer {
    private final MailSenderService mailSenderService;
    @RabbitListener(queues = "company-admin-mail-queue")
    public void sendCompanyAdminPassword(CompanyAdminPasswordModel model){
        mailSenderService.sendCompanyAdminPassword(model);
    }
}
