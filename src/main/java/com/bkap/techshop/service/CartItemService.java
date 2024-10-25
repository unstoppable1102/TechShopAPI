package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.CartItemRequest;
import com.bkap.techshop.dto.response.CartItemResponse;

import java.util.List;
import java.util.Map;


public interface CartItemService {
    List<CartItemResponse> findByUserId(long userId);
    CartItemResponse addCartItem(CartItemRequest request);
    long countItemsInCart(long userId);
    Double calculateTotalPrice(long userId);
    void updateCartItems(long userId, Map<String, String> quantities);
    void removeCartItem(long cartItemId);
    void clearCart(long userId);
}
