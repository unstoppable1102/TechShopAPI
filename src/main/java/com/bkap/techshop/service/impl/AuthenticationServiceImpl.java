package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.SignInRequest;
import com.bkap.techshop.dto.response.TokenResponse;

import com.bkap.techshop.entity.Token;
import com.bkap.techshop.entity.User;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.service.AuthenticationService;
import com.bkap.techshop.service.JwtService;
import com.bkap.techshop.service.TokenService;
import com.bkap.techshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.bkap.techshop.common.util.TokenType.ACCESS_TOKEN;
import static com.bkap.techshop.common.util.TokenType.REFRESH_TOKEN;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @Override
    public TokenResponse authenticate(SignInRequest request) {
        User user = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            user = userService.findByUsername(request.getUsername());
        } catch (AuthenticationException e) {
            throw  new AppException(ErrorCode.LOGIN_FAIL);
        }
        log.info(user.getUsername());
//
        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        //save token to db
        tokenService.saveToken(Token.builder()
                        .username(user.getUsername())
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build());
//
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponse refreshToken(HttpServletRequest request) {

        //validate
        String refreshToken = request.getHeader("x-token");
        if (StringUtils.isBlank(refreshToken)) {
            throw new AppException(ErrorCode.TOKEN_REQUIRED);
        }

        //extract user from token
        final String username = jwtService.extractUsername(refreshToken, REFRESH_TOKEN);

        //check in db
        var user = userService.findByUsername(username);

        if (!jwtService.isValid(refreshToken,REFRESH_TOKEN, user)) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }

        String accessToken = jwtService.generateToken(user);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public String logout(HttpServletRequest request) {
        //validate
        String refreshToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(refreshToken)) {
            throw new AppException(ErrorCode.TOKEN_REQUIRED);
        }

        //extract user from token
        final String username = jwtService.extractUsername(refreshToken, ACCESS_TOKEN);
        Token currentToken = tokenService.findTokenByUsername(username);
        tokenService.deleteToken(currentToken);

        return "Logout successful";
    }

}
