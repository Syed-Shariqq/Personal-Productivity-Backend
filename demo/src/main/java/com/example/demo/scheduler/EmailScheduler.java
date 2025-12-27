package com.example.demo.scheduler;

import com.example.demo.entity.EmailRequest;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 23 * * ?")
    public void dailyEmail(){

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setTo("syedshariq0824@gmail.com");
        emailRequest.setSubject("Reminder");
        emailRequest.setBody("Today's Note remaining");
        emailService.sendEmail(emailRequest);
    }
}
