package com.bkap.techshop.service.impl;

import com.bkap.techshop.dto.request.CartItemRequest;
import com.bkap.techshop.dto.response.CartItemResponse;
import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.entity.User;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.CartItemRepository;
import com.bkap.techshop.repository.ProductRepository;
import com.bkap.techshop.repository.UserRepository;
import com.bkap.techshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public List<CartItemResponse> findByUserId(long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        return cartItems.stream()
                .map((element) -> modelMapper.map(element, CartItemResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartItemResponse addCartItem(CartItemRequest request) {
        //Get user information
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        //get product information
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            cartItemRepository.save(existingCartItem);
            return modelMapper.map(existingCartItem, CartItemResponse.class);
        }else {
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItemRepository.save(cartItem);
            return modelMapper.map(cartItem, CartItemResponse.class);
        }

    }

    @Override
    public long countItemsInCart(long userId) {
        return cartItemRepository.countCartItemsByUserId(userId);
    }

    @Override
    public Double calculateTotalPrice(long userId) {
        List<CartItem> items = cartItemRepository.findByUserId(userId);
        return items.stream().mapToDouble(item ->{
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
            return product.getPrice() * item.getQuantity();
        }).sum();
    }

    @Override
    public void updateCartItems(long userId, Map<String, String> quantities) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        for (CartItem cartItem : cartItems) {
            String quantityStr = quantities.get(cartItem.getProduct().getId().toString());
            if (quantityStr != null) {
                cartItem.setQuantity(Integer.parseInt(quantityStr));
                cartItemRepository.save(cartItem);
            }
        }

    }

    @Override
    public void removeCartItem(long cartItemId) {
        if (!cartItemRepository.existsById(cartItemId)) {
            throw new AppException(ErrorCode.CART_ITEM_NOT_FOUND);
        }
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        cartItemRepository.deleteAll(cartItems);
    }

}
