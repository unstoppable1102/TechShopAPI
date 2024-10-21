package com.bkap.techshop.dto.response;

import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.entity.User;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private Long id;
    private User user;
    private List<CartItem> items;

}
