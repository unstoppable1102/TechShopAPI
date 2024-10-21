package com.bkap.techshop.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCategoryResponse {

    private Long id;
    private String name;
    private boolean active;
    private String description;
    private List<PostResponse> posts;
}
