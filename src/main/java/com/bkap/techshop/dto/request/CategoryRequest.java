package com.bkap.techshop.dto.request;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.bkap.techshop.entity.Category}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest implements Serializable {
    String name;
    boolean status;
}