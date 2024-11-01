package com.bkap.techshop.repository;

import com.bkap.techshop.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//    List<OrderItem> findByUserId(long userId);
//
//    long countOrderItemsByUserId(long userId);
//
//    OrderItem findByUserIdAndProductId(long userId, long productId);
}