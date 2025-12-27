package com.example.demo.controller;

import com.example.demo.entity.EmailRequest;
import com.example.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;


    @PostMapping("/send-email")
    public ResponseEntity<?> sendingEmail(@RequestBody EmailRequest emailRequest){
    try{
        emailService.sendEmail(emailRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }catch(Exception e){
        log.error("Something went wrong while sending mail ");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }
    @PostMapping("/daily-email")
    public ResponseEntity<?> sendEmailDaily(){
        try{
            EmailRequest newRequest = new EmailRequest();
            emailService.sendEmail(newRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("Something went wrong", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
