package com.bkap.techshop.controller;

import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.entity.Wishlist;
import com.bkap.techshop.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/wishlists")
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ApiResponse<Map<String, Object>> getWishlist(@RequestParam("userId") long userId) {

        Map<String, Object> response = new HashMap<>();

        // Lấy danh sách các wishlist
        List<Wishlist> wishlists = wishlistService.findWishlistsByUserId(userId);
        response.put("wishlists", wishlists);

        // Lấy danh sách các sản phẩm trong wishlist
        List<Long> wishlistProductIds = wishlistService.getWishlistProductIds(userId);
        response.put("wishlistProductIds", wishlistProductIds);

        return ApiResponse.<Map<String, Object>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(response)
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<String> addWishlist(@RequestBody Wishlist wishlist) {
        wishlistService.save(wishlist);
        return ApiResponse.<String>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteWishlist(@PathVariable int id) {
            wishlistService.delete(id);
        return ApiResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                .build();
    }

    @GetMapping("/items")
    public ApiResponse<List<Long>> getWishlistProductIds(@RequestParam("userId") long userId) {
        List<Long> wishlistProductIds = wishlistService.getWishlistProductIds(userId);
        return ApiResponse.<List<Long>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(wishlistProductIds)
                .build();
    }

//    @GetMapping("/total-price")
//    public ApiResponse<Double> getTotalPrice(@RequestParam("userId") Long userId) {
//        Double totalPrice = (userId != null) ? cartService.calculateTotalPrice(userId) : 0.0;
//        return ApiResponse.<Double>builder()
//                .code(HttpStatus.OK.value())
//                .message(HttpStatus.OK.getReasonPhrase())
//                .result(totalPrice)
//                .build();
//    }

//    @GetMapping("/cart-item-count")
//    public ApiResponse<Long> getCountCartItem(@RequestParam("userId") Long userId) {
//        Long countCartItem = (userId != null) ? cartService.countItemsInCart(userId) : 0;
//        return ApiResponse.<Long>builder()
//                .code(HttpStatus.OK.value())
//                .message(HttpStatus.OK.getReasonPhrase())
//                .result(countCartItem)
//                .build();
//
//    }
}
