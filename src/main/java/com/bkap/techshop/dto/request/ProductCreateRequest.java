package com.bkap.techshop.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * DTO for {@link com.bkap.techshop.entity.Product}
 */
@Setter
@Getter
public class ProductCreateRequest implements Serializable {

    private String productName;
    private boolean status;
    private double price;
    private double priceOld;
    private int quantity;
    private String description;
    private MultipartFile image;
    private Long categoryId;
}