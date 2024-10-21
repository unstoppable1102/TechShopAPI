package com.bkap.techshop.controller;

import com.bkap.techshop.dto.request.OrderRequest;
import com.bkap.techshop.dto.response.ApiResponse;
import com.bkap.techshop.dto.response.OrderResponse;
import com.bkap.techshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;


    @GetMapping
    public ApiResponse<List<OrderResponse>> getUserOrders(@RequestParam long userId) {
        List<OrderResponse> orders = orderService.getOrdersByUserId(userId);
        return ApiResponse.<List<OrderResponse>>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(orders)
                .build();
    }

    @PostMapping
    public ApiResponse<OrderResponse> createUserOrder(@RequestParam long userId, @RequestBody OrderRequest request) {
        OrderResponse orderResponse = orderService.createOrder(userId, request);
        return ApiResponse.<OrderResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.getReasonPhrase())
                .result(orderResponse)
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getUserOrder(@PathVariable long orderId) {
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        return ApiResponse.<OrderResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(orderResponse)
                .build();
    }

    @PatchMapping("/{orderId}/status")
    public ApiResponse<OrderResponse> updateUserOrder(@PathVariable long orderId, @RequestParam String status) {
        OrderResponse orderResponse = orderService.updateOrderStatus(orderId, status);

        return ApiResponse.<OrderResponse>builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.getReasonPhrase())
                .result(orderResponse)
                .build();
    }
}
