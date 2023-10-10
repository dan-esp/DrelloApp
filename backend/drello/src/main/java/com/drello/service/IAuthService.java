package com.drello.service;

import org.springframework.stereotype.Service;

import com.drello.controller.records.AuthResponse;
import com.drello.controller.records.LoginRequest;
import com.drello.controller.records.SignUpRequest;

@Service
public interface IAuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse register(SignUpRequest signUpRequest);

    AuthResponse loginByToken(String token);
}
