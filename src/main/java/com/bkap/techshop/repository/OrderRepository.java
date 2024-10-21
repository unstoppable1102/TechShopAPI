package com.bkap.techshop.repository;

import com.bkap.techshop.dto.response.OrderResponse;
import com.bkap.techshop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<OrderResponse> findByUserId(Long userId);
}