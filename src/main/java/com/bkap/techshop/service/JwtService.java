package com.bkap.techshop.service;

import com.bkap.techshop.common.util.TokenType;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

     String generateToken(UserDetails userDetails);
     String generateRefreshToken(UserDetails userDetails);

     String extractUsername(String token, TokenType type);
     boolean isValid(String token, TokenType type, UserDetails userDetails);
}
