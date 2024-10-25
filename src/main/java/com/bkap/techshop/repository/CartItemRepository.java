package com.bkap.techshop.repository;

import com.bkap.techshop.entity.CartItem;
import com.bkap.techshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(long userId);

    long countCartItemsByUserId(long userId);

    CartItem findByUserIdAndProductId(long userId, long productId);

}