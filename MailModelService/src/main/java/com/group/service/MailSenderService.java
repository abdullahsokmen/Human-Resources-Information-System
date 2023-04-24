package com.group.service;

import com.group.rabbitmq.model.ActivateStatusModel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendMail(ActivateStatusModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Activation Code");
        mailMessage.setText("Your activation code : "+model.getActivationCode());
        javaMailSender.send(mailMessage);
    }
}
