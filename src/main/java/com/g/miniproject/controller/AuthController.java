package com.g.miniproject.controller;

import com.g.miniproject.dto.JwtAuthResponse;
import com.g.miniproject.dto.LoginDto;
import com.g.miniproject.dto.RegisterDto;
import com.g.miniproject.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return new ResponseEntity<>(authService.registerUser(registerDto), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto logindto){
        return new ResponseEntity<>(authService.loginUser(logindto), HttpStatus.OK);
    }
}
