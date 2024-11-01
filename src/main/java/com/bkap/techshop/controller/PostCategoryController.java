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
    public ApiResponse<PostCategoryResponse> create(@RequestBody PostCategoryRequest request) {
        try {
            PostCategoryResponse response = postCategoryService.create(request);
            return ApiResponse.<PostCategoryResponse>builder()
                    .code(HttpStatus.CREATED.value())
                    .message(HttpStatus.CREATED.getReasonPhrase())
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<PostCategoryResponse> getById(@PathVariable Long id) {
        try {
            PostCategoryResponse response = postCategoryService.findById(id);
            return ApiResponse.<PostCategoryResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(response)
                    .build();
        }catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @GetMapping("/search/{name}")
    public ApiResponse<List<PostCategoryResponse>> getByName(@PathVariable String name) {
        try {
            return ApiResponse.<List<PostCategoryResponse>>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(postCategoryService.findByName(name))
                    .build();
        }catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<PostCategoryResponse> updatePostCategory(@PathVariable Long id, @RequestBody PostCategoryRequest request) {
        try {
            PostCategoryResponse response = postCategoryService.update(id, request);
            return ApiResponse.<PostCategoryResponse>builder()
                    .code(HttpStatus.OK.value())
                    .message(HttpStatus.OK.getReasonPhrase())
                    .result(response)
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePostCategory(@PathVariable Long id) {
        try {
            postCategoryService.delete(id);
            return ApiResponse.<Void>builder()
                    .code(HttpStatus.NO_CONTENT.value())
                    .message("Post Category is deleted successfully!")
                    .build();
        } catch (Exception e) {
            return ApiResponse.errorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }
}
