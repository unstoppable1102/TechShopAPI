package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.CategoryRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.CategoryResponse;
import com.bkap.techshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll(){
        List<CategoryResponse> categoryResponses = categoryService.findAll();
        return ApiResponse.<List<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(categoryResponses)
                .build();
    }

    @PostMapping
    public ApiResponse<CategoryResponse> create(@RequestBody CategoryRequest request){
        CategoryResponse categoryResponse = categoryService.save(request);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(categoryResponse)
                .build();
    }


    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> findById(@PathVariable long id){
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(categoryResponse)
                .build();
    }

    @GetMapping("/search/{name}")
    public ApiResponse<List<CategoryResponse>> findByName(@PathVariable String name){
        List<CategoryResponse> categoryResponses = categoryService.findByName(name);
        return ApiResponse.<List<CategoryResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(categoryResponses)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CategoryResponse> update(@PathVariable long id, @RequestBody CategoryRequest request){
        CategoryResponse categoryResponse = categoryService.update(id, request);
        return ApiResponse.<CategoryResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(categoryResponse)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete( @PathVariable long id){
        categoryService.delete(id);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Category is deleted successfully!")
                .build();
    }
}
