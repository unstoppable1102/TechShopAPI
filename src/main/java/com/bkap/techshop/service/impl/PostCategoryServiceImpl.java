package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.PostCategoryRequest;
import com.bkap.techshop.dto.response.PostCategoryResponse;
import com.bkap.techshop.entity.PostCategory;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.PostCategoryRepository;
import com.bkap.techshop.service.PostCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostCategoryServiceImpl implements PostCategoryService {

    private final PostCategoryRepository postCategoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostCategoryResponse> findAll() {
        return postCategoryRepository.findAll().stream()
                .map((element) -> modelMapper.map(element, PostCategoryResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostCategoryResponse findById(Long id) {
        PostCategory postCategory = postCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.POST_CATEGORY_NOT_FOUND));
        return modelMapper.map(postCategory, PostCategoryResponse.class);
    }

    @Override
    public PostCategoryResponse create(PostCategoryRequest request) {
        PostCategory postCategory = modelMapper.map(request, PostCategory.class);

        return modelMapper.map(postCategoryRepository.save(postCategory), PostCategoryResponse.class);
    }

    @Override
    public PostCategoryResponse update(long id, PostCategoryRequest request) {
        PostCategory existingPostCategory = postCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.POST_CATEGORY_NOT_FOUND));
        modelMapper.map(request, existingPostCategory);

        return modelMapper.map(postCategoryRepository.save(existingPostCategory), PostCategoryResponse.class);
    }

    @Override
    public void delete(long id) {
        postCategoryRepository.deleteById(id);
    }
}
