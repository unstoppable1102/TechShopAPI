package com.bkap.techshop.controller;

import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.entity.Cart;
import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.repository.CartItemRepository;
import com.bkap.techshop.service.CartItemService;
import com.bkap.techshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartItemRepository cartItemRepository;

    private final CartService cartService;
    private final CartItemService cartItemService;

    @ModelAttribute("totalPrice")
    public Double getTotalPrice(@ModelAttribute("userId") long userId) {
        return cartService.calculateTotalPrice(userId);
    }

    @ModelAttribute("countCartItem")
    public long getCountCartItem(@ModelAttribute("userId") long userId) {
        return cartService.countItemsInCart(userId);
    }

    @GetMapping("/{userId}")
    public ApiResponse<Cart> showCart( @PathVariable long userId) {
        Cart cart = cartService.findByUserId(userId);
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        cart.setItems(cartItems);
        return ApiResponse.<Cart>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(cart)
                .build();
    }

    @PostMapping("/add/{productId}")
    public ApiResponse<String> addCartItem( @PathVariable long productId, @RequestParam int userId, @RequestBody CartItem cartItem) {
        Cart cart = cartService.findByUserId(userId);
        Product product = cartItem.getProduct();
        var findCart = cartItemService.findByCartIdAndProductId(cart.getId(), productId);

        if (findCart == null){
            // Nếu sản phẩm chưa có trong giỏ hàng, tạo một mục mới
            CartItem newCartItem = new CartItem();

            // Set Cart và Product thông qua đối tượng
            newCartItem.setCart(cart);         // Thiết lập Cart
            newCartItem.setProduct(product);   // Thiết lập Product

            // Set số lượng, lấy từ request hoặc mặc định là 1
            newCartItem.setQuantity(cartItem.getQuantity());

            // Lưu mục giỏ hàng mới vào cơ sở dữ liệu
            cartItemRepository.save(newCartItem);
        }else {
            findCart.setQuantity(findCart.getQuantity() + cartItem.getQuantity());
            cartItemService.update(findCart);
        }
        return ApiResponse.<String>builder()
                .code(HttpStatus.CREATED.value())
                .message("Cart item added successfully.")
                .build();
    }

    @PutMapping("/update/{proId}/{quantity}")
    public ApiResponse<String> updateCartItem(@PathVariable long proId, @RequestParam long userId, @PathVariable int quantity) {
        Cart cart = cartService.findByUserId(userId);
        var cartItem = cartItemService.findByCartIdAndProductId(cart.getId(), proId);

        if (cartItem == null) {
            return ApiResponse.<String>builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .message("Cart item not found.")
                    .build();
        }

        cartItem.setQuantity(quantity);
        cartItemService.update(cartItem);

        return ApiResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .message("Cart item updated successfully.")
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<String> deleteCartItem(@PathVariable long id) {
        cartItemService.delete(id);
        return ApiResponse.<String>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Cart item deleted successfully.")
                .build();
    }



}
