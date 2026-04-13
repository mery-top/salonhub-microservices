package com.meerthika.service.impl;

import com.meerthika.modal.User;
import com.meerthika.payload.dto.SignupDTO;
import com.meerthika.payload.response.AuthResponse;
import com.meerthika.payload.response.TokenResponse;
import com.meerthika.respository.UserRepository;
import com.meerthika.service.AuthService;
import com.meerthika.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;


    @Override
    public AuthResponse login(String username, String password) throws Exception {
        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                username,
                password,
                "password", null
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setMessage("login success");

        return authResponse;
    }

    @Override
    public AuthResponse signup(SignupDTO req) throws Exception {
        keycloakService.createUser(req);

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setEmail(req.getEmail());
        user.setRole(req.getRole());
        user.setFullName(req.getFirstName());
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                req.getUsername(),
                req.getPassword(),
                "password", null
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setRole(user.getRole());
        authResponse.setMessage("Register success");

        return authResponse;
    }

    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) throws Exception {
        TokenResponse tokenResponse = keycloakService.getAdminAccessToken(
                null,
                null,
                "refresh_token", refreshToken
        );

        AuthResponse authResponse = new AuthResponse();
        authResponse.setRefresh_token(tokenResponse.getRefreshToken());
        authResponse.setJwt(tokenResponse.getAccessToken());
        authResponse.setMessage("login success");

        return authResponse;
    }
}
