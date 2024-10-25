package com.bkap.techshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phone;

    private Date birthday;
}
