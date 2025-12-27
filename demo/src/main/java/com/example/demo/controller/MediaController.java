package com.example.demo.controller;

import com.example.demo.service.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable long id, @RequestParam MultipartFile file){
        try{
            mediaService.storeFile(id,file);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("Something went wrong",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
