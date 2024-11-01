package com.bkap.techshop.service.impl;

import com.bkap.techshop.common.util.UploadFileUtil;
import com.bkap.techshop.dto.request.PostRequestDto;
import com.bkap.techshop.dto.response.PostResponse;
import com.bkap.techshop.entity.Post;
import com.bkap.techshop.entity.PostCategory;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.PostCategoryRepository;
import com.bkap.techshop.repository.PostRepository;
import com.bkap.techshop.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UploadFileUtil uploadFileUtil;
    private final PostCategoryRepository postCategoryRepository;

    @Override
    public List<PostResponse> findAll() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((e) -> modelMapper.map(e, PostResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        return modelMapper.map(post, PostResponse.class);
    }

    @Override
    public PostResponse create(PostRequestDto request) {

        PostCategory postCategory =postCategoryRepository.findById(request.getPostCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_CATEGORY_NOT_FOUND));

        if (postRepository.existsByTitleIgnoreCase(request.getTitle())) {
            throw new AppException(ErrorCode.POST_EXISTED);
        }

        Post post = modelMapper.map(request, Post.class);
        post.setPostCategory(postCategory);
        post.setId(null);

        //Xu ly lưu file ảnh
        String imagePath = uploadFileUtil.saveImage(request.getImage());
        post.setImage(imagePath);
        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public PostResponse update(long id, PostRequestDto request) {
        PostCategory postCategory =postCategoryRepository.findById(request.getPostCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.POST_CATEGORY_NOT_FOUND));

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));

        modelMapper.map(request, post);
        post.setPostCategory(postCategory);

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            //save image into folder
            String imagePath = uploadFileUtil.saveImage(request.getImage());
            post.setImage(imagePath);
        }

        return modelMapper.map(postRepository.save(post), PostResponse.class);
    }

    @Override
    public void delete(long id) {
       postRepository.deleteById(id);

    }

    @Override
    public List<PostResponse> findByTitle(String title) {
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(title);

        return posts.stream()
                .map((element) -> modelMapper.map(element, PostResponse.class))
                .collect(Collectors.toList());
    }


}
