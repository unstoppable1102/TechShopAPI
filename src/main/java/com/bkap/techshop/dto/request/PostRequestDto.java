package com.bkap.techshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto implements Serializable {
    private  String title;
    private  String shortDescription;
    private  String content;
    private  String image;
    private boolean status;
}