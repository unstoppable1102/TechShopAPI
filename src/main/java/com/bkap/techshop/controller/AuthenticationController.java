package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.SignInRequest;
import com.bkap.techshop.dto.request.UserRequestDto;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.TokenResponse;
import com.bkap.techshop.dto.response.UserResponse;
import com.bkap.techshop.service.AuthenticationService;
import com.bkap.techshop.service.UserService;
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
    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<TokenResponse> login(@RequestBody SignInRequest request) {
        try {
            return ApiResponse.<TokenResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(authenticationService.authenticate(request))
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody UserRequestDto request) {

        try {
            return ApiResponse.<UserResponse>builder()
                    .code(HttpStatus.CREATED.value())
                    .message(HttpStatus.CREATED.getReasonPhrase())
                    .result(userService.save(request))
                    .build();
        }catch (Exception e){
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }



    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refresh(HttpServletRequest request) {
        try {
            return ApiResponse.<TokenResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(authenticationService.refreshToken(request))
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ApiResponse<String> logout(HttpServletRequest request) {
        try {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(authenticationService.logout(request))
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
