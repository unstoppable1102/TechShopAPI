package com.bkap.techshop.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest implements Serializable {

    @NotBlank(message = "Username must be not null")
    private String username;
    @NotBlank(message = "Password must be not null")
    private String password;

}
