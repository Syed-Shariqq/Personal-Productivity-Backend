package com.example.demo.controller;

import com.example.demo.service.QRcodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class QRcodeController {

    @Autowired
    private QRcodeService qRcodeService;

    @GetMapping(value = "/{id}/QRcode",
            produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getQRcode(@PathVariable long id) throws Exception {
        try {
            String noteUrl = "http://localhost:8080/" + id;

            return qRcodeService.QRcodeGenerator(noteUrl, 300, 300);
        } catch (Exception e) {
            log.error("Exception while generating QRcode", e);
        }
        return new byte[0];
    }
}
