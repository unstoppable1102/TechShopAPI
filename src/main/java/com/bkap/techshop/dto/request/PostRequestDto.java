package com.bkap.techshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto implements Serializable {

    private  String title;
    private  String shortDescription;
    private  String content;
    private MultipartFile image;
    private long postCategoryId;
    private boolean status;
}