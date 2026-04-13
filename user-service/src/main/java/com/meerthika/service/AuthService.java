package com.meerthika.service;

import com.meerthika.payload.dto.SignupDTO;
import com.meerthika.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password);
    AuthResponse signup(SignupDTO req);
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken);
}
