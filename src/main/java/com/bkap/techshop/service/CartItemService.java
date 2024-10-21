package com.bkap.techshop.service;

import com.bkap.techshop.entity.Cart;
import com.bkap.techshop.entity.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> findByCart(Cart cart);
    CartItem findByCartIdAndProductId(long cartId, long productId);
    List<CartItem> findByCartId(long cartId);
    List<CartItem> findAll();
    CartItem findById(long id);
    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
    public void delete(long id);
}
