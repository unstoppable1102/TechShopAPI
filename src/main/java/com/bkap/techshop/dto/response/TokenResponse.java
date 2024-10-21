package com.bkap.techshop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse implements Serializable {

    private String accessToken;
    private String refreshToken;
}
