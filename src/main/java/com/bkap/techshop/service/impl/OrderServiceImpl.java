package com.bkap.techshop.service.impl;

import com.bkap.techshop.common.util.EOrderStatus;
import com.bkap.techshop.dto.request.OrderItemRequest;
import com.bkap.techshop.dto.request.OrderRequest;
import com.bkap.techshop.dto.response.OrderResponse;
import com.bkap.techshop.entity.Order;
import com.bkap.techshop.entity.OrderItem;
import com.bkap.techshop.entity.Product;
import com.bkap.techshop.entity.User;
import com.bkap.techshop.exception.AppException;
import com.bkap.techshop.exception.ErrorCode;
import com.bkap.techshop.repository.OrderRepository;
import com.bkap.techshop.repository.ProductRepository;
import com.bkap.techshop.repository.UserRepository;
import com.bkap.techshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(Long userId, OrderRequest orderRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() ->new AppException(ErrorCode.USER_NOT_FOUND));

        Order order = new Order();
        order.setUser(user);
        order.setOrderStatus(EOrderStatus.NEW);

        List<OrderItem> orderItems = new ArrayList<>();
        double totalPrice = 0.0;

        for(OrderItemRequest itemRequest : orderRequest.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->new AppException(ErrorCode.PRODUCT_NOT_FOUND));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice() * orderItem.getQuantity());
            totalPrice += orderItem.getPrice();
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {
        List<OrderResponse> orderResponses = orderRepository.findByUserId(userId);
        return orderResponses.stream()
                .map(orderResponse -> modelMapper.map(orderResponse, OrderResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        var orderStatus = EOrderStatus.fromString(status);
        order.setOrderStatus(orderStatus);
        return modelMapper.map(orderRepository.save(order), OrderResponse.class);
    }
}
