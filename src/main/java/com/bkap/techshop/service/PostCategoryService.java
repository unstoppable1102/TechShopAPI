package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.PostCategoryRequest;
import com.bkap.techshop.dto.response.PostCategoryResponse;

import java.util.List;

public interface PostCategoryService {
    List<PostCategoryResponse> findAll();
    PostCategoryResponse findById(Long id);
    PostCategoryResponse create(PostCategoryRequest request);
    PostCategoryResponse update(long id, PostCategoryRequest request);
    void delete(long id);
}
