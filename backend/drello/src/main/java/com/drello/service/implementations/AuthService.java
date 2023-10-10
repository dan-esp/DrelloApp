package com.drello.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.drello.controller.records.AuthResponse;
import com.drello.controller.records.LoginRequest;
import com.drello.controller.records.SignUpRequest;
import com.drello.exceptions.EntityNotFoundException;
import com.drello.model.Member;
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
        UserEntity userDetails = userRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new EntityNotFoundException(loginRequest.username(), UserEntity.class.getName()));
        String token = jwtService.getTokenFromRequest(userDetails);
        Member user = new Member(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                userDetails.getProfileUrl());
        return new AuthResponse(token, user);
    }

    @Override
    public AuthResponse loginByToken(String token) {
        String username = jwtService.getUsernameFromToken(token);
        UserEntity userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(username, UserEntity.class.getName()));

        if (!jwtService.validateToken(token, userDetails)) {
            throw new EntityNotFoundException(username, UserEntity.class.getName());
        }
        Member user = new Member(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                userDetails.getProfileUrl());
        return new AuthResponse(token, user);
    }

    @Override
    public AuthResponse register(SignUpRequest signUpRequest) {
        UserEntity userEntity = new UserEntity(signUpRequest
                .username(), signUpRequest.email(), passwordEncoder.encode(signUpRequest.password()));
        userEntity = userRepository.save(userEntity);
        Member user = new Member(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
                userEntity.getProfileUrl());
        AuthResponse authResponse = new AuthResponse(jwtService.getTokenFromRequest(userEntity), user);
        return authResponse;
    }
}
