package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.CommentRequestDto;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.CommentResponse;
import com.bkap.techshop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ApiResponse<List<CommentResponse>> getAllComments() {
        List<CommentResponse> responses = commentService.findAllComments();
        return ApiResponse.<List<CommentResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(responses)
                .build();
    }

    @PostMapping
    public ApiResponse<CommentResponse> createComment(@RequestBody CommentRequestDto request){
        CommentResponse response = commentService.create(request);
        return ApiResponse.<CommentResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(response)
                .build();
    }
}
