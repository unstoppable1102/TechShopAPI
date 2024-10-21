package com.bkap.techshop.service.impl;

import com.bkap.techshop.entity.Cart;
import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.CartItemRepository;
import com.bkap.techshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;


    @Override
    public List<CartItem> findByCart(Cart cart) {
        return cartItemRepository.findByCart(cart);
    }

    @Override
    public CartItem findByCartIdAndProductId(long cartId, long productId) {
        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
    }

    @Override
    public List<CartItem> findByCartId(long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public List<CartItem> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem findById(long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));
    }

    @Override
    public CartItem save(CartItem cartItem) {

        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem update(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(long id) {
        cartItemRepository.deleteById(id);
    }
}
