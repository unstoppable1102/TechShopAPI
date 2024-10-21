package com.bkap.techshop.service;

import com.bkap.techshop.entity.Token;
import com.bkap.techshop.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record TokenService(TokenRepository tokenRepository) {

    public void saveToken(Token token) {
        Optional<Token> optional = tokenRepository.findByUsername(token.getUsername());
        if (optional.isEmpty()){
            tokenRepository.save(token);
        }else {
            Token currentToken = optional.get();
            currentToken.setAccessToken(token.getAccessToken());
            currentToken.setRefreshToken(token.getRefreshToken());
            tokenRepository.save(currentToken);
        }
    }

    public Token findTokenByUsername(String username) {
        return tokenRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Token not found"));
    }

    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }
}
