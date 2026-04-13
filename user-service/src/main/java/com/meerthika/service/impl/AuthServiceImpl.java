package com.meerthika.service.impl;

import com.meerthika.payload.dto.SignupDTO;
import com.meerthika.payload.response.AuthResponse;
import com.meerthika.respository.UserRepository;
import com.meerthika.service.AuthService;
import com.meerthika.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;


    @Override
    public AuthResponse login(String username, String password) {
        return null;
    }

    @Override
    public AuthResponse signup(SignupDTO req) {
        return null;
    }

    @Override
    public AuthResponse getAccessTokenFromRefreshToken(String refreshToken) {
        return null;
    }
}
