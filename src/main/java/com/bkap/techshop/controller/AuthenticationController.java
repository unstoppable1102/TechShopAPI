package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.SignInRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.TokenResponse;
import com.bkap.techshop.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody SignInRequest request) {
        return ApiResponse.<TokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(authenticationService.authenticate(request))
                .build();

    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refresh(HttpServletRequest request) {
        return ApiResponse.<TokenResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(authenticationService.refreshToken(request))
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(authenticationService.logout(request))
                .build();
    }


}
