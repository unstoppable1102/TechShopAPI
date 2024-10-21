package com.bkap.techshop.service;

import com.bkap.techshop.dto.response.CartResponse;
import com.bkap.techshop.entity.Cart;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<CartResponse> getAllCarts();



    Cart findByUserId(long userId);
    long countItemsInCart(long userId);
    Double calculateTotalPrice(long userId);
    void updateCartItems(long userId, Map<String, String> quantities);
    void clearCart(long userId);

}
