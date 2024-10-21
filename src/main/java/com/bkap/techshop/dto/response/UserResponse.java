package com.bkap.techshop.dto.response;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String phone;
}
