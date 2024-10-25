package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.CommentRequestDto;
import com.bkap.techshop.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    List<CommentResponse> findAllComments();
    CommentResponse findCommentById(long id);
    CommentResponse create(CommentRequestDto request);
    CommentResponse update(long id, CommentRequestDto request);
    void delete(long id);

}
