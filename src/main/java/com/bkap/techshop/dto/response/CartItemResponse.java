package com.bkap.techshop.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private Long productId;
    private String productName;
    private int quantity;
    private double price;
}
