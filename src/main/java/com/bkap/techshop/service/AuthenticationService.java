package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.SignInRequest;
import com.bkap.techshop.dto.response.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    TokenResponse authenticate(SignInRequest request);
    TokenResponse refreshToken(HttpServletRequest request);
    String logout(HttpServletRequest request);

}
