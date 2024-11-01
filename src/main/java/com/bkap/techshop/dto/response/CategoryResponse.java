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

    private Long id;
    private String name;
    private boolean status;
}