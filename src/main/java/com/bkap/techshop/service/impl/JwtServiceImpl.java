package com.bkap.techshop.service.impl;

import com.bkap.techshop.service.JwtService;
import com.bkap.techshop.common.util.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static com.bkap.techshop.common.util.TokenType.ACCESS_TOKEN;
import static com.bkap.techshop.common.util.TokenType.REFRESH_TOKEN;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.expiryDate}")
    private Long expiryDate;

    @Value("${jwt.expiryTime}")
    private Long expiryTime;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.refreshKey}")
    private String refreshKey;

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return generateRefreshToken(new HashMap<>(), userDetails);
    }

    @Override
    public String extractUsername(String token, TokenType type) {
        return extractClaim(token, type, Claims::getSubject);
    }

    @Override
    public boolean isValid(String token, TokenType type, UserDetails userDetails) {
        final String username = extractUsername(token, type);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, type));
    }


    private String generateToken(Map<String, Objects> claims, UserDetails userDetails) {
        log.info(userDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*expiryTime))
                .signWith(getKey(ACCESS_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private String generateRefreshToken(Map<String, Objects> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*4*expiryDate))
                .signWith(getKey(REFRESH_TOKEN), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token, TokenType type) {
        return extractExpiration(token, type).before(new Date());
    }

    private Date extractExpiration(String token, TokenType type ) {
        return extractClaim(token, type, Claims::getExpiration);
    }

    private Key getKey(TokenType type){
        byte[] keyBytes;
        if (ACCESS_TOKEN.equals(type)){
            keyBytes = Decoders.BASE64.decode(secretKey);
        }else {
            keyBytes = Decoders.BASE64.decode(refreshKey);
        }

        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, TokenType type, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaim(token, type);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaim(String token, TokenType type){
        return Jwts.parserBuilder().setSigningKey(getKey(type)).build().parseClaimsJws(token).getBody();
    }
}
