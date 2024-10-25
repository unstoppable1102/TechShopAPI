package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.CartItemRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.CartItemResponse;
import com.bkap.techshop.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartItemService cartItemService;

    @ModelAttribute("totalPrice")
    public Double getTotalPrice(@RequestParam("userId") long userId) {
        return cartItemService.calculateTotalPrice(userId);
    }

    @ModelAttribute("countCartItem")
    public long getCountCartItem(@RequestParam("userId") long userId) {
        return cartItemService.countItemsInCart(userId);
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<CartItemResponse>> getCartByUserId(@PathVariable long userId) {
        List<CartItemResponse> cartItemResponses = cartItemService.findByUserId(userId);

        return ApiResponse.<List<CartItemResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(cartItemResponses)
                .build();
    }

    @PostMapping
    public ApiResponse<CartItemResponse> addItemToCart(@RequestBody CartItemRequest request) {
        CartItemResponse addCartItem = cartItemService.addCartItem(request);

        return ApiResponse.<CartItemResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(addCartItem)
                .build();
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ApiResponse<Void> removeCartItem(@PathVariable long cartItemId) {
        cartItemService.removeCartItem(cartItemId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Cart item is removed successfully!")
                .build();
    }

    @DeleteMapping("/clear/{userId}")
    public ApiResponse<Void> clearCart(@PathVariable long userId) {
        cartItemService.clearCart(userId);
        return ApiResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Cart is deleted successfully!")
                .build();
    }

}
