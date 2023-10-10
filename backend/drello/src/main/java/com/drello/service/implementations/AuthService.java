package com.drello.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drello.controller.records.AuthResponse;
import com.drello.controller.records.LoginRequest;
import com.drello.controller.records.SignUpRequest;
import com.drello.model.UserEntity;
import com.drello.repository.UserRepository;
import com.drello.service.IAuthService;
import com.drello.service.jwt.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService implements IAuthService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        UserDetails userDetails = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new IllegalArgumentException("Username not found"));
        String token = jwtService.getTokenFromRequest(userDetails);
        return new AuthResponse(token);

    }

    @Override
    public AuthResponse register(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity(signUpRequest
                .username(), signUpRequest.email(), passwordEncoder.encode(signUpRequest.password()));
        userEntity = userRepository.save(userEntity);
        AuthResponse authResponse = new AuthResponse(jwtService.getTokenFromRequest(userEntity));
        return authResponse;
    }

}
