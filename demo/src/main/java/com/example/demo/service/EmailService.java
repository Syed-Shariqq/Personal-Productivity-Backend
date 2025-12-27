package com.example.demo.service;

import com.example.demo.entity.EmailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setTo(emailRequest.getTo());
            mail.setSubject(emailRequest.getSubject());
            mail.setText(emailRequest.getBody());

            javaMailSender.send(mail);
        } catch (Exception e) {
            log.error("Exception while sending mail", e);
        }
      }
    }


