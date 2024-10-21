package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.PostRequestDto;
import com.bkap.techshop.dto.response.PostResponse;
import com.bkap.techshop.repository.PostRepository;
import com.bkap.techshop.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostResponse> findAll() {
        return List.of();
    }

    @Override
    public PostResponse findById(Long id) {
        return null;
    }

    @Override
    public PostResponse create(PostRequestDto request) {
        return null;
    }

    @Override
    public PostResponse update(long id, PostRequestDto request) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
