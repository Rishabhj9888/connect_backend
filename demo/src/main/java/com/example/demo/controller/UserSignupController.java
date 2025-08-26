package com.example.demo.controller;

import com.example.demo.DTO.LoginRequest;
import com.example.demo.DTO.SignupRequest;
import com.example.demo.service.UserSignupService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserSignupController {

    @Autowired
    private UserSignupService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest request) {
        return service.registerUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        return service.loginUser(request);
    }
}
