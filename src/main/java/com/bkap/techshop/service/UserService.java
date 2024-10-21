package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.UserRequestDto;
import com.bkap.techshop.dto.response.UserResponse;
import com.bkap.techshop.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    List<UserResponse> findAll();
    UserResponse findById(long id);
    UserResponse save(UserRequestDto request);
    UserResponse update(long id, UserRequestDto request);
    void delete(long id);
    User findByUsername(String username);


}
