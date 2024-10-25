package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.CartItemRequest;
import com.bkap.techshop.dto.response.CartResponse;

public interface CartService {
    CartResponse getCartByUserId(long userId);



}
