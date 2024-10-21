package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.response.CartResponse;
import com.bkap.techshop.entity.Cart;
import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.CartItemRepository;
import com.bkap.techshop.repository.CartRepository;
import com.bkap.techshop.repository.ProductRepository;
import com.bkap.techshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    @Override
    public List<CartResponse> getAllCarts() {
        return cartRepository.findAll().stream()
                .map((element) -> modelMapper.map(element, CartResponse.class))
                .toList();
    }

    @Override
    public Cart findByUserId(long userId) {
        return cartRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    @Override
    public long countItemsInCart(long userId) {
        return cartItemRepository.countCartItemsByUserId(userId);
    }

    @Override
    public Double calculateTotalPrice(long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) return 0.0;
        List<CartItem> cartItems = cart.getItems();
        return cartItems.stream().mapToDouble(item ->{
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            return product.getPrice() * item.getQuantity();
        }).sum();
    }

    @Override
    public void updateCartItems(long userId, Map<String, String> quantities) {
        Cart cart = cartRepository.findByUserId(userId);
        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        for (CartItem cartItem : cartItems) {
            String quantityStr = quantities.get(String.valueOf(cartItem.getProduct().getId()));
            if(quantityStr != null) {
                cartItem.setQuantity(Integer.parseInt(quantityStr));
                cartItemRepository.save(cartItem);
            }
        }

    }

    @Override
    public void clearCart(long userId) {
        List<CartItem> cartItems = cartItemRepository
                .findByCartId(cartRepository.findByUserId(userId).getId());
        cartItemRepository.deleteAll(cartItems);
    }
}
