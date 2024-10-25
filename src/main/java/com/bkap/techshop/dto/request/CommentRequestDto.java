package com.bkap.techshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {

    private Long parentId; // ID của comment cha (nếu là comment con)

    private Long postId; // ID của bài viết mà comment thuộc về

    private Long userId; // ID của người dùng tạo comment

    private String content; // Nội dung của comment

}
