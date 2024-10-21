package com.bkap.techshop.service;

import com.bkap.techshop.dto.request.OrderRequest;
import com.bkap.techshop.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(Long userId, OrderRequest orderRequest);
    List<OrderResponse> getOrdersByUserId(Long userId);
    OrderResponse getOrderById(Long orderId);
    OrderResponse updateOrderStatus(Long orderId, String status);
}
