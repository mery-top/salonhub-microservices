package com.meerthika.service;

import com.meerthika.payload.dto.SignupDTO;
import com.meerthika.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse login(String username, String password) throws Exception;
    AuthResponse signup(SignupDTO req) throws Exception;
    AuthResponse getAccessTokenFromRefreshToken(String refreshToken);
}
