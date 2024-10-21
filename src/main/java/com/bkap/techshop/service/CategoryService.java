package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.CategoryRequest;
import com.bkap.techshop.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();
    CategoryResponse findById(long id);
    CategoryResponse save(CategoryRequest request);
    CategoryResponse update(long id, CategoryRequest request);
    void delete(long id);
    List<CategoryResponse> findByName(String name);
}
