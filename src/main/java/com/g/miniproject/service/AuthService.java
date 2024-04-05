package com.g.miniproject.service;

import com.g.miniproject.dto.JwtAuthResponse;
import com.g.miniproject.dto.LoginDto;
import com.g.miniproject.dto.RegisterDto;

public interface AuthService {
    String registerUser(RegisterDto registerDto);

    JwtAuthResponse loginUser(LoginDto loginDto);
}
