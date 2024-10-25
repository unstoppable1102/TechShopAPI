package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.PostRequestDto;
import com.bkap.techshop.dto.response.PostResponse;

import java.util.List;

public interface PostService {

    List<PostResponse> findAll();
    PostResponse findById(Long id);
    PostResponse create(PostRequestDto request);
    PostResponse update(long id, PostRequestDto request);
    void delete(long id);
    List<PostResponse> findByTitle(String title);
}
