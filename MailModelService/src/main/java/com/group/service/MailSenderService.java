package com.group.service;

import com.group.rabbitmq.model.AdminPasswordSenderModel;
import com.group.rabbitmq.model.CompanyAdminPasswordModel;
import com.group.rabbitmq.model.PersonalPasswordSenderModel;
import com.group.rabbitmq.model.ResetPasswordModel;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendPersonalPassword(PersonalPasswordSenderModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Password");
        mailMessage.setText("Your password : "+model.getPassword());
        javaMailSender.send(mailMessage);
    }
    public void sendAdminPassword(AdminPasswordSenderModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Password");
        mailMessage.setText("Your password : "+model.getPassword());
        javaMailSender.send(mailMessage);
    }
    public void sendCompanyAdminPassword(CompanyAdminPasswordModel model){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Password");
        mailMessage.setText("Your password : "+model.getPassword());
        javaMailSender.send(mailMessage);
    }

    public void sendNewPassword(ResetPasswordModel model) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${mailUsername}");
        mailMessage.setTo(model.getEmail());
        mailMessage.setSubject("Password");
        mailMessage.setText("Your password : "+model.getPassword());
        javaMailSender.send(mailMessage);
    }
}
