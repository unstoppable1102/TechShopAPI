package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.CommentRequestDto;
import com.bkap.techshop.dto.response.CommentResponse;
import com.bkap.techshop.entity.Comment;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.CommentRepository;
import com.bkap.techshop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CommentResponse> findAllComments() {
        List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map((element) -> modelMapper.map(element, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse findCommentById(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));
        return modelMapper.map(comment, CommentResponse.class);
    }

    @Override
    public CommentResponse create(CommentRequestDto request) {
        Comment comment = modelMapper.map(request, Comment.class);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentResponse.class);
    }

    @Override
    public CommentResponse update(long id, CommentRequestDto request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        modelMapper.map(request, comment);
        return modelMapper.map(commentRepository.save(comment), CommentResponse.class);
    }

    @Override
    public void delete(long id) {
        commentRepository.deleteById(id);

    }
}
