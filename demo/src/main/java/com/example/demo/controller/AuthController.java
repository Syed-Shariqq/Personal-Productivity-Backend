package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

        @GetMapping("/api/auth/check")
        public ResponseEntity<String> checkAuth() {
            return new ResponseEntity<>(HttpStatus.OK);
        }

}
