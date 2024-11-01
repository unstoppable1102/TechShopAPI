package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.UserRequestDto;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.UserResponse;
import com.bkap.techshop.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> findAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(userService.findAll())
                .build();
    }


    @GetMapping("/{id}")
    public ApiResponse<UserResponse> findById(@PathVariable Long id){
        try {
            return ApiResponse.<UserResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(userService.findById(id))
                    .build();
        }catch (Exception e){
            return ApiResponse.errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponse> update(@PathVariable Long id, @RequestBody UserRequestDto request){
        UserResponse updatedUser = userService.update(id, request);
        return ApiResponse.<UserResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(updatedUser)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> delete(@PathVariable Long id){
        try {
            userService.delete(id);
            return ApiResponse.<UserResponse>builder()
                    .code(HttpStatus.NO_CONTENT.value())
                    .message("User is deleted successfully!")
                    .build();
        }catch (Exception e){
            return ApiResponse.errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }
}
