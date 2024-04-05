package com.g.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String username;
    private String email;
    private String password;
}
