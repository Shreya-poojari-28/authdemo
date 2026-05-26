package com.example.authdemo.service;

import com.example.authdemo.dto.*;
import com.example.authdemo.entity.User;
import com.example.authdemo.repository.UserRepository;
import com.example.authdemo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refresh(RefreshTokenRequest request) {

        if (!jwtUtil.validate(request.getRefreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }

        String email = jwtUtil.extractEmail(request.getRefreshToken());

        String accessToken = jwtUtil.generateAccessToken(email);
        String refreshToken = jwtUtil.generateRefreshToken(email);

        return new AuthResponse(accessToken, refreshToken);
    }
}