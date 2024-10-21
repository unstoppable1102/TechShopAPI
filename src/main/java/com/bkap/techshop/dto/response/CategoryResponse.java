package com.bkap.techshop.dto.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.bkap.techshop.entity.Category}
 */
@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CategoryResponse implements Serializable {
    private Long id;       // ID của danh mục
    private String name;      // Tên của danh mục
    private boolean status;      // Trạng thái của danh mục
    private List<ProductResponse> products;
}