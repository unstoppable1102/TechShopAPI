package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.PostCategoryRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.PostCategoryResponse;
import com.bkap.techshop.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post-categories")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    @GetMapping
    public ApiResponse<List<PostCategoryResponse>> getAllPostCategories() {
        List<PostCategoryResponse> responses = postCategoryService.findAll();
        return ApiResponse.<List<PostCategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(responses)
                .build();
    }

    @PostMapping
    public ApiResponse<PostCategoryResponse> createPostCategory(@RequestBody PostCategoryRequest request) {
        PostCategoryResponse response = postCategoryService.create(request);
        return ApiResponse.<PostCategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(response)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<PostCategoryResponse> getPostCategoryById(@PathVariable Long id) {
        PostCategoryResponse response = postCategoryService.findById(id);
        return ApiResponse.<PostCategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(response)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<PostCategoryResponse> updatePostCategory(@PathVariable Long id, @RequestBody PostCategoryRequest request) {
        PostCategoryResponse response = postCategoryService.update(id, request);
        return ApiResponse.<PostCategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(response)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePostCategory(@PathVariable Long id) {
        postCategoryService.delete(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .build();
    }
}
